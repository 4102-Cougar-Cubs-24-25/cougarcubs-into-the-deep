package sigmaCode.currentStuff.freakySubsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class VerticalClaw extends SubsystemBase {
    private Servo vClaw;
    private clawState cs;
    public enum clawState{
        OPEN, CLOSE;
    }
    public VerticalClaw(Servo vClaw){
        this.vClaw = vClaw;
    }
    public void go(clawState state){
        cs = state;
        switch(cs){
            case OPEN:
                vClaw.setPosition(0);
                break;
            case CLOSE:
                vClaw.setPosition(0.5);
                break;
        }
    }
}
