package sigmaCode.currentStuff.feinCommands;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.InstantCommand;
import sigmaCode.currentStuff.freakySubsystems.HorizontalSlides;
import sigmaCode.currentStuff.freakySubsystems.IzzyV2;
public class HorizontalSlidesCommand extends InstantCommand {
    public static int runCount = 0;
    private IzzyV2 izzyV2;
    private HorizontalSlides.extendState state;
    public HorizontalSlidesCommand(IzzyV2 izzyV2, HorizontalSlides.extendState state){
        super(() -> izzyV2.horizontalSlides.setTarget(state));
        this.izzyV2 = izzyV2;
        this.state = state;
    }
    public void initialize(){
        if(state != null) izzyV2.horizontalSlides.setTarget(state);
    }
}