package sigmaCode.currentStuff.feinCommands;

import com.arcrobotics.ftclib.command.InstantCommand;

import sigmaCode.currentStuff.freakySubsystems.Izzy;

public class ActivateLiftCommand extends InstantCommand {
    private Izzy izzy;
    public ActivateLiftCommand(Izzy izzy){
        this.izzy = izzy;
    }
    public void initialize(){
        izzy.lift.setOn();
    }
}
