package sigmaCode.currentStuff.freakySubsystems;
import static java.lang.Math.abs;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
@Config
public class HorizontalSlides extends SubsystemBase{
    private final DcMotorEx hSlide;
    private final PIDController controller;
    private int target, pos;
    private boolean on = false;
    private double power;
    private final double p = .0233, i = 0, d = .0004, f = 0;
    private final double ticks_in_degrees = 700 / 180.0;
    private boolean teleOp;
    public enum extendState {
        UP, MIDDLE, DOWN, FIFTH;
    }
    public int getTarget(){
        return target;
    }
    public HorizontalSlides(DcMotorEx hSlide, boolean teleOp){
        this.hSlide = hSlide;
        this.teleOp = teleOp;
        controller = new PIDController(p, i, d);
        pos = hSlide.getCurrentPosition();
        controller.setTolerance(10);
        hSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void setOn(){
        on = !on;
    }
    public boolean getOn(){
        return on;
    }
    public void setTarget(extendState state){
        switch (state){
            case UP:
                target = 2200;
                break;
            case MIDDLE:
                target = 1110;
                break;
            case DOWN:
                target = 0;
                break;
        }
    }
    public boolean busy(){
        return hSlide.isBusy();
    }
    public int getPos(){
        return pos;
    }
    public void periodic(){
        pos = hSlide.getCurrentPosition();
        if(!teleOp) {
            if (!on) {
                hSlide.setPower(0.1);
                return;
            } else if (target == 0) {
                if (pos <= 0) {
                    hSlide.setPower(.65);
                    return;
                }
                hSlide.setPower(0);
                return;
            } else {
                controller.setPID(p, i, d);
                power = controller.calculate(pos, target) + f;
                hSlide.setPower(power);
            }
        }
    }
}