package sigmaCode.currentStuff.freakySubsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class VerticalArm extends SubsystemBase {
    private Servo lvArm, rvArm;
    private armState as;
    public enum armState{
        SPECWALL, TRANSFER, SPECSCORE, SAMPSCORE, MIDTRANSFER;
    }

    public VerticalArm(Servo lvArm, Servo rvArm){
        this.lvArm = lvArm;
        this.rvArm = rvArm;
    }

    public void go(armState state){
        as = state;
        switch(as){
            case SPECWALL:
                lvArm.setPosition(0.03);
                rvArm.setPosition(0.03);
                break;
            case TRANSFER:
                lvArm.setPosition(.7);
                rvArm.setPosition(.7);
                break;
            case SAMPSCORE:
                lvArm.setPosition(.1);
                rvArm.setPosition(.1);
                break;
            case SPECSCORE:
                lvArm.setPosition(.62);
                rvArm.setPosition(.62);
                break;
            case MIDTRANSFER:
                lvArm.setPosition(.6);
                rvArm.setPosition(.6);
                break;
        }
    }
}
