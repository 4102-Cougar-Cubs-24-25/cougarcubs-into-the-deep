package sigmaCode.currentStuff.feinCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.InstantCommand;

import sigmaCode.currentStuff.freakySubsystems.Lift;
import sigmaCode.currentStuff.freakySubsystems.TelemetryUtil;

public class LiftCommand extends CommandBase {
    private Lift lift;
    private Lift.liftState state;
    public LiftCommand(Lift lift, Lift.liftState state){
        this.lift = lift;
        this.state = state;
        addRequirements(lift);
    }
    public void execute(){
        lift.setTarget(state);
        //lift.periodic();
    }
    public boolean isFinished(){
        if(!lift.busy()){
            return true;
        }
        return false;
    }
}
