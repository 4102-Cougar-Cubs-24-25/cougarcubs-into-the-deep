package sigmaCode.currentStuff.freakySubsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class VerticalWrist extends SubsystemBase {
    private Servo vWrist;
    private wristState ws;
    public enum wristState{
        NORMAL, SPECPLACE, AUTOSPEC;
    }
    public VerticalWrist(Servo vWrist){
        this.vWrist = vWrist;
    }
    public void go(wristState state){
        ws = state;
        switch(ws){
            case SPECPLACE:
                vWrist.setPosition(.45);
                break;
            case NORMAL:
                vWrist.setPosition(.5);
                break;
            case AUTOSPEC:
                vWrist.setPosition(.6);
                break;
        }
    }
}
