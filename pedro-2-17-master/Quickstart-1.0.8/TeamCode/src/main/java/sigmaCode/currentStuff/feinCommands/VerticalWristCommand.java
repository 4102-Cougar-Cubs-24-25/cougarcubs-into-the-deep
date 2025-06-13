package sigmaCode.currentStuff.feinCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import sigmaCode.currentStuff.freakySubsystems.VerticalWrist;

public class VerticalWristCommand extends CommandBase {
    private VerticalWrist verticalWrist;
    private VerticalWrist.wristState state;
    public VerticalWristCommand(VerticalWrist verticalWrist, VerticalWrist.wristState state){
        this.verticalWrist = verticalWrist;
        this.state = state;
        addRequirements(verticalWrist);
    }
    public void execute(){
        verticalWrist.go(state);
    }
    public boolean isFinished(){
        return true;
    }
}