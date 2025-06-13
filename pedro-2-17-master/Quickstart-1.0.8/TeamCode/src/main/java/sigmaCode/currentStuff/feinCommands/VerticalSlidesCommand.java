package sigmaCode.currentStuff.feinCommands;
import com.arcrobotics.ftclib.command.InstantCommand;

import sigmaCode.currentStuff.freakySubsystems.IzzyV2;
import sigmaCode.currentStuff.freakySubsystems.VerticalSlides;
public class VerticalSlidesCommand extends InstantCommand {
    public static int runCount = 0;
    private IzzyV2 izzy;
    private VerticalSlides.liftState state;
    public VerticalSlidesCommand(IzzyV2 izzy, VerticalSlides.liftState state){
        super(() -> izzy.verticalSlides.setTarget(state));
        this.izzy = izzy;
        this.state = state;
    }
    public void initialize(){
        izzy.verticalSlides.setTarget(state);
    }
}