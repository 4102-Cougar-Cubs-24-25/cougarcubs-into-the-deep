package sigmaCode.currentStuff.feinCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import sigmaCode.currentStuff.freakySubsystems.LiftWrist;

public class LiftWristCommand extends CommandBase {
    private LiftWrist liftWrist;
    private LiftWrist.wristState state;
    public LiftWristCommand(LiftWrist liftWrist, LiftWrist.wristState state){
        this.liftWrist = liftWrist;
        this.state = state;
        addRequirements(liftWrist);
    }
    public void execute(){
        liftWrist.go(state);
    }
    public boolean isFinished(){
        return true;
    }
}