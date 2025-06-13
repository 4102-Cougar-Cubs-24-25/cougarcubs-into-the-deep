package sigmaCode.currentStuff.feinCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.InstantCommand;

import sigmaCode.currentStuff.freakySubsystems.HorizontalClaw;
import sigmaCode.currentStuff.freakySubsystems.VerticalClaw;

public class HorizontalClawCommand extends InstantCommand {
    private HorizontalClaw horizontalClaw;
    private HorizontalClaw.clawState state;
    public HorizontalClawCommand(HorizontalClaw horizontalClaw, HorizontalClaw.clawState state){
        this.horizontalClaw = horizontalClaw;
        this.state = state;
        addRequirements(horizontalClaw);
    }
    public void initialize(){
        horizontalClaw.go(state);
    }
}
