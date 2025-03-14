package sigmaCode.currentStuff.feinCommands;
import com.arcrobotics.ftclib.command.InstantCommand;

import sigmaCode.currentStuff.freakySubsystems.Izzy;
import sigmaCode.currentStuff.freakySubsystems.Lift;

public class LiftCommand extends InstantCommand {
    public static int runCount = 0;
    private Izzy izzy;
    private Lift.liftState state;
    public LiftCommand(Izzy izzy, Lift.liftState state){
        super(() -> izzy.lift.setTarget(state));
        this.izzy = izzy;
        this.state = state;
    }
    public void initialize(){
        izzy.lift.setTarget(state);
    }
}
