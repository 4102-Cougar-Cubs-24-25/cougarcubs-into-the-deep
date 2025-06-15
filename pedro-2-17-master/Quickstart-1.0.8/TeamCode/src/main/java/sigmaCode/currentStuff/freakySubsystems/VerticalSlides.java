package sigmaCode.currentStuff.freakySubsystems;
import static java.lang.Math.abs;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
@Config
public class VerticalSlides extends SubsystemBase {
    private final DcMotorEx vSlide;
    private final PIDController controller;
    private int target, pos;
    private boolean on = false;
    private double power;
    private final double p = .0233, i = 0, d = .0004, f = .105;
    private final double ticks_in_degrees = 700 / 180.0;
    private boolean teleOp;
    public enum liftState{
        SPECUP, DOWN, SAMPUP, RESET, MIDDLE;
    }
    public int getTarget(){
        return target;
    }
    public VerticalSlides(DcMotorEx vSlide, boolean teleOp){
        this.vSlide = vSlide;
        this.teleOp = teleOp;
        controller = new PIDController(p, i, d);
        pos = vSlide.getCurrentPosition();
        controller.setTolerance(10);
        vSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
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
            case SPECUP:
                target = -900;
                break;
            case MIDDLE:
                target = -430;
                break;
            case DOWN:
                target = 0;
                break;
            case SAMPUP:
                target = -1850;
                break;
            case RESET:
                vSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                vSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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
        pos = vSlide.getCurrentPosition();
        if(!teleOp) {
            if (!on) {
                vSlide.setPower(0);
                return;
            } else if (target == 0) {
                if (pos <= 0) {
                    vSlide.setPower(.5);
                    return;
                }
                vSlide.setPower(0);
            } else if (abs(pos-target) >= 5) {
                controller.setPID(p, i, d);
                power = controller.calculate(pos, target) + f;
                vSlide.setPower(power);
            }

        }
    }
}