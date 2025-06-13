package sigmaCode.currentStuff.feinCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.InstantCommand;

import sigmaCode.currentStuff.freakySubsystems.VerticalClaw;

public class VerticalClawCommand extends InstantCommand {
    private VerticalClaw verticalClaw;
    private VerticalClaw.clawState state;
    public VerticalClawCommand(VerticalClaw verticalClaw, VerticalClaw.clawState state){
        this.verticalClaw = verticalClaw;
        this.state = state;
        addRequirements(verticalClaw);
    }
    public void initialize(){
        verticalClaw.go(state);
    }
}
