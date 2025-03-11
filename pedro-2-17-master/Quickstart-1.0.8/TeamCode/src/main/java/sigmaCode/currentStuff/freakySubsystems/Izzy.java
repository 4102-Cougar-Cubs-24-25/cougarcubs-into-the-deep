package sigmaCode.currentStuff.freakySubsystems;

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

public class Izzy {
    public Servo vClaw, lvWrist, rvWrist;
    public DcMotorEx vSlide;
    public Follower follower;
    public Lift lift;
    public LiftClaw liftClaw;
    public LiftWrist liftWrist;
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
        lift = new Lift(vSlide);
        liftClaw = new LiftClaw(vClaw);
        liftWrist = new LiftWrist(rvWrist, lvWrist);
        follower = new Follower(hardwareMap);
        follower.setStartingPose(startPose);
        CommandScheduler.getInstance().registerSubsystem(lift, liftClaw, liftWrist);
    }
    public void loop(){
        CommandScheduler.getInstance().run();
        TelemetryUtil.addData("lift target", lift.getTarget());
        TelemetryUtil.addData("lift pos", lift.getPos());
        TelemetryUtil.addData("lift on", lift.getOn());
        TelemetryUtil.update();
        follower.update();
    }
}