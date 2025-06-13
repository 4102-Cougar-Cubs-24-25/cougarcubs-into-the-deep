package sigmaCode.currentStuff.feinCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import sigmaCode.currentStuff.freakySubsystems.HorizontalArm;
import sigmaCode.currentStuff.freakySubsystems.VerticalArm;

public class HorizontalArmCommand extends CommandBase {
    private HorizontalArm horizontalArm;
    private HorizontalArm.armState state;
    public HorizontalArmCommand(HorizontalArm horizontalArm, HorizontalArm.armState state){
        this.horizontalArm = horizontalArm;
        this.state = state;
        addRequirements(horizontalArm);
    }

    @Override
    public void execute() {
        horizontalArm.go(state);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
