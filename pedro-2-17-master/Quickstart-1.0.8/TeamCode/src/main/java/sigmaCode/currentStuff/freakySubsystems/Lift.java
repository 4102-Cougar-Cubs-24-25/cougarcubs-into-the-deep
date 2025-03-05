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
    private DcMotorEx vSlide;
    public liftState ls;
    private PIDController controller;
    public int target;
    public int pos;
    private double p = .029, i = 0, d = .00035, f = .035;
    private final double ticks_in_degrees = 700 / 180.0;
    public enum liftState{
        UP, MIDDLE, DOWN;
    }
    public Lift(DcMotorEx vSlide){
        this.vSlide = vSlide;
        controller = new PIDController(p, i, d);
        pos = vSlide.getCurrentPosition();
        controller.setTolerance(10);
        vSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void setTarget(liftState state){
        ls = state;
        switch (ls){
            case UP:
                target = 1500;
                break;
            case MIDDLE:
                target = 1200;
                break;
            case DOWN:
                target = 0;
                break;
        }
    }
    public boolean busy(){
        return vSlide.isBusy();
    }
    public void periodic(){
        controller.setPID(p, i, d);
        pos = vSlide.getCurrentPosition();
        double pid = controller.calculate(pos, target);
        double power = pid + f;
        vSlide.setPower(power);
    }
}