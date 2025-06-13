package sigmaCode.currentStuff.feinCommands;

import com.arcrobotics.ftclib.command.InstantCommand;

import sigmaCode.currentStuff.freakySubsystems.IzzyV2;

public class ActivateExtendCommand extends InstantCommand {
    private IzzyV2 izzy;
    public ActivateExtendCommand(IzzyV2 izzy){
        this.izzy = izzy;
    }
    public void initialize(){
        izzy.horizontalSlides.setOn();
    }
}
