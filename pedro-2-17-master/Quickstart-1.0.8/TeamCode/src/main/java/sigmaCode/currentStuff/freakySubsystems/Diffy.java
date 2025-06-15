package sigmaCode.currentStuff.freakySubsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Diffy extends SubsystemBase {
    private Servo lDiffy, rDiffy;
    private Limelight3A limelight;
    private diffyState ds;
    private double sampleAngle;
    public enum diffyState{
        START, TURN, HANDOFF, SAMPLE, THIRD;
    }
    public Diffy(Servo lDiffy, Servo rDiffy, Limelight3A limelight){
        this.lDiffy = lDiffy;
        this.rDiffy = rDiffy;
        this.limelight = limelight;
    }

    public void go(diffyState state){
        ds = state;
        switch(ds){
            case START:
                rDiffy.setPosition(.55);
                lDiffy.setPosition(.45);
                break;
            case TURN:
                double clawAngle = sampleAngle - 90;
                double diffyTurn = (((clawAngle / 2) * 1.5) / 355);
                lDiffy.setPosition(0.3 + diffyTurn);
                rDiffy.setPosition(0.7 + diffyTurn);
                break;
            case HANDOFF:
                rDiffy.setPosition(0.2);
                lDiffy.setPosition(0.8);
                break;
            case SAMPLE:
                lDiffy.setPosition(0.3);
                rDiffy.setPosition(0.7);
                break;
            case THIRD:
                double turn = (((90 / 2) * 1.5) / 355);
                lDiffy.setPosition(0.3 + turn);
                rDiffy.setPosition(0.7 + turn);
                break;
        }
    }
    public double getAngle(){
        return sampleAngle;
    }
    public void periodic(){
        LLResult result = limelight.getLatestResult();
        double[] outputs = result.getPythonOutput();
        sampleAngle = outputs[5];
    }
}
