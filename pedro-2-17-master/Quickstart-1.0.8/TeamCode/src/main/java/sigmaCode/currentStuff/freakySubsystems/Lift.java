package sigmaCode.currentStuff.freakySubsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static sigmaCode.currentStuff.freakySubsystems.Lift.liftState.DOWN;
import static sigmaCode.currentStuff.freakySubsystems.Lift.liftState.MIDDLE;
import static sigmaCode.currentStuff.freakySubsystems.Lift.liftState.UP;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import sigmaCode.currentStuff.SigmaRamenAuton;

@Config
public class Lift extends SubsystemBase {
    private final DcMotorEx vSlide;
    private final PIDController controller;
    private int target, pos;
    private boolean on = true;
    private double power;
    private final double p = .0233, i = 0, d = .0004, f = 0;
    private final double ticks_in_degrees = 700 / 180.0;
    public enum liftState{
        UP, MIDDLE, DOWN
    }
    public int getTarget(){
        return target;
    }
    public Lift(DcMotorEx vSlide){
        this.vSlide = vSlide;
        controller = new PIDController(p, i, d);
        pos = vSlide.getCurrentPosition();
        controller.setTolerance(10);
        vSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void setOn(){
        on = !on;
    }
    public boolean getOn(){
        return on;
    }
    public void setTarget(liftState state){
        switch (state){
            case UP:
                target = 1700;
                break;
            case MIDDLE:
                target = 1150;
                break;
            case DOWN:
                target = -2;
                break;
            default:
                target = -5;
                break;
        }
    }
    public boolean busy(){
        return vSlide.isBusy();
    }
    public int getPos(){
        return pos;
    }
    public void periodic(){
        if(!on){
            vSlide.setPower(0);
            return;
        } else if(target == -5){
            if(vSlide.getCurrentPosition() >= target){
                vSlide.setPower(-.4);
                return;
            }
            vSlide.setPower(0);
            return;
        } else {
            controller.setPID(p, i, d);
            pos = vSlide.getCurrentPosition();
            power = controller.calculate(pos, target) + f;
            vSlide.setPower(power);
        }
    }
}