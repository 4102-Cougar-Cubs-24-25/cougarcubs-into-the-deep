package sigmaCode.currentStuff.feinCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import sigmaCode.currentStuff.freakySubsystems.Diffy;

public class DiffyCommand extends CommandBase {
    private Diffy diffy;
    private Diffy.diffyState state;
    public DiffyCommand(Diffy diffy, Diffy.diffyState state){
        this.diffy = diffy;
        this.state = state;
        addRequirements(diffy);
    }
    public void execute(){
        diffy.go(state);
    }
    public boolean isFinished(){
        return true;
    }
}
