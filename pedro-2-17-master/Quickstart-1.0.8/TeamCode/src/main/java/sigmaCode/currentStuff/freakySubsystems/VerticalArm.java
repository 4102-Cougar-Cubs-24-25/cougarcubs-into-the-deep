package sigmaCode.currentStuff.freakySubsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class VerticalArm extends SubsystemBase {
    private Servo lvArm, rvArm;
    private armState as;
    public enum armState{
        SPECWALL, TRANSFER, SPECSCORE, SAMPSCORE, MIDTRANSFER, RAMSPEC;
    }

    public VerticalArm(Servo lvArm, Servo rvArm){
        this.lvArm = lvArm;
        this.rvArm = rvArm;
    }

    public void go(armState state){
        as = state;
        switch(as){
            case SPECWALL:
                lvArm.setPosition(0.06);
                rvArm.setPosition(0.06);
                break;
            case TRANSFER:
                lvArm.setPosition(.71);
                rvArm.setPosition(.71);
                break;
            case SAMPSCORE:
                lvArm.setPosition(.18);
                rvArm.setPosition(.18);
                break;
            case SPECSCORE:
                lvArm.setPosition(.62);
                rvArm.setPosition(.62);
                break;
            case MIDTRANSFER:
                lvArm.setPosition(.6);
                rvArm.setPosition(.6);
                break;
            case RAMSPEC:
                lvArm.setPosition(.56);
                rvArm.setPosition(.56);
                break;
        }
    }
}
