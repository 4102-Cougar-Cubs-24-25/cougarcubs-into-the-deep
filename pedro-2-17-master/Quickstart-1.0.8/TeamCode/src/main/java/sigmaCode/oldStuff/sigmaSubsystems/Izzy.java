package sigmaCode.oldStuff.sigmaSubsystems;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;
import sigmaCode.currentStuff.freakySubsystems.TelemetryUtil;
import sigmaCode.currentStuff.freakySubsystems.VerticalClaw;
import sigmaCode.currentStuff.freakySubsystems.VerticalSlides;
import sigmaCode.currentStuff.freakySubsystems.VerticalWrist;

public class Izzy {
    public Servo vClaw, lvWrist, rvWrist;
    public DcMotorEx vSlide;
    public Follower follower;
    public VerticalSlides verticalSlides;
    public sigmaCode.currentStuff.freakySubsystems.VerticalClaw verticalClaw;
    public VerticalWrist verticalWrist;
    public Izzy(HardwareMap hardwareMap, Pose startPose){
        Constants.setConstants(FConstants.class, LConstants.class);
        vSlide = hardwareMap.get(DcMotorEx.class, "vSlide");
        vSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        vSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        vSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        vSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rvWrist = hardwareMap.get(Servo.class, "rvWrist");
        lvWrist = hardwareMap.get(Servo.class, "lvWrist");
        vClaw = hardwareMap.get(Servo.class, "vClaw");
        //verticalSlides = new VerticalSlides(vSlide);
        verticalClaw = new VerticalClaw(vClaw);
        //verticalWrist = new VerticalWrist(rvWrist, lvWrist);
        follower = new Follower(hardwareMap);
        follower.setStartingPose(startPose);
        CommandScheduler.getInstance().registerSubsystem(verticalSlides, verticalClaw, verticalWrist);
    }
    public void loop(){
        CommandScheduler.getInstance().run();
        TelemetryUtil.addData("verticalSlides target", verticalSlides.getTarget());
        TelemetryUtil.addData("verticalSlides pos", verticalSlides.getPos());
        TelemetryUtil.addData("verticalSlides on", verticalSlides.getOn());
        TelemetryUtil.update();
        follower.update();
    }
}