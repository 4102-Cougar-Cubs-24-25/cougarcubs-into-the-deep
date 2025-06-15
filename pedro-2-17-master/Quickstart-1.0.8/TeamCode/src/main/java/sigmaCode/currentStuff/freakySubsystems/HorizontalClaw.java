package sigmaCode.currentStuff.freakySubsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class HorizontalClaw extends SubsystemBase {
    private Servo hClaw;
    private clawState cs;
    public enum clawState{
        OPENED, CLOSED, LOOSE;
    }
    public HorizontalClaw(Servo hClaw){
        this.hClaw = hClaw;
    }
    public void go(clawState state){
        cs = state;
        switch(cs){
            case OPENED:
                hClaw.setPosition(0);
                break;
            case CLOSED:
                hClaw.setPosition(0.5);
                break;
            case LOOSE:
                hClaw.setPosition(0.25);
                break;
        }
    }
}
