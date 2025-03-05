package sigmaCode.currentStuff.feinCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.InstantCommand;

import sigmaCode.currentStuff.freakySubsystems.Lift;
import sigmaCode.currentStuff.freakySubsystems.TelemetryUtil;

public class LiftCommand extends CommandBase {
    private Lift lift;
    private Lift.liftState state;
    public static Lift.liftState s;
    public static int runCount = 0;

    public LiftCommand(Lift lift, Lift.liftState state){
        this.lift = lift;
        this.state = state;
        s = state;
        addRequirements(lift);
        runCount++;
    }

    public void execute(){
        lift.setTarget(state);
    }
}
