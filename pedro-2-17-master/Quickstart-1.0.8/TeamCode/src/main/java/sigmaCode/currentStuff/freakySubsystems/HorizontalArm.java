package sigmaCode.currentStuff.freakySubsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class HorizontalArm extends SubsystemBase {
    private Servo lhArm, rhArm;
    private armState as;
    public enum armState{
        UP, DOWN, RETRACTED;
    }

    public HorizontalArm(Servo lhArm, Servo rhArm){
        this.lhArm = lhArm;
        this.rhArm = rhArm;
    }

    public void go(armState state){
        as = state;
        switch(as){
            case DOWN:
                rhArm.setPosition(.805);
                lhArm.setPosition(.805);
                break;
            case UP:
                rhArm.setPosition(.7275);
                lhArm.setPosition(.7275);
                break;
            case RETRACTED:
                rhArm.setPosition(.64);
                lhArm.setPosition(.64);
                break;
        }
    }
}
