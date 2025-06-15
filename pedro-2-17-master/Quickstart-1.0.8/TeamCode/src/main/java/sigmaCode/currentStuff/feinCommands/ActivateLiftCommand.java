package sigmaCode.currentStuff.feinCommands;

import static sigmaCode.currentStuff.freakySubsystems.VerticalSlides.liftState.RESET;

import com.arcrobotics.ftclib.command.InstantCommand;

import sigmaCode.currentStuff.freakySubsystems.IzzyV2;

public class ActivateLiftCommand extends InstantCommand {
    private IzzyV2 izzy;
    public ActivateLiftCommand(IzzyV2 izzy){
        this.izzy = izzy;
    }
    public void initialize(){
        izzy.verticalSlides.setOn();
    }
}
