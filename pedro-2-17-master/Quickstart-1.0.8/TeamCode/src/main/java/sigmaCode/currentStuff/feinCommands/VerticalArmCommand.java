package sigmaCode.currentStuff.feinCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import sigmaCode.currentStuff.freakySubsystems.VerticalArm;

public class VerticalArmCommand extends CommandBase {
    private VerticalArm verticalArm;
    private VerticalArm.armState state;
    public VerticalArmCommand(VerticalArm verticalArm, VerticalArm.armState state){
        this.verticalArm = verticalArm;
        this.state = state;
        addRequirements(verticalArm);
    }

    @Override
    public void execute() {
        verticalArm.go(state);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
