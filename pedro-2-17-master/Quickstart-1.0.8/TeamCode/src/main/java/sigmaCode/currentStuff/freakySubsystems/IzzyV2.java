package sigmaCode.currentStuff.freakySubsystems;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.util.Constants;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;

public class IzzyV2 {
    public DcMotorEx rightFront, leftFront, rightBack, leftBack, vSlide, hSlide;
    public Servo rDiffy, lDiffy, lvArm, rvArm, vClaw, hClaw, vWrist, lhArm, rhArm;
    public IMU imu;
    public Limelight3A limelight;
    public Follower follower;
    public VerticalSlides verticalSlides;
    public VerticalClaw verticalClaw;
    public VerticalWrist verticalWrist;
    public Diffy diffy;
    public VerticalArm vertArm;
    public HorizontalClaw horiClaw;
    public HorizontalArm horiArm;
    public HorizontalSlides horizontalSlides;
    public IzzyV2(HardwareMap hardwareMap, Pose startPose, boolean teleOp){
        Constants.setConstants(FConstants.class, LConstants.class);
        rightFront = hardwareMap.get(DcMotorEx.class,"rightFront");
        leftFront = hardwareMap.get(DcMotorEx.class,"leftFront");
        rightBack = hardwareMap.get(DcMotorEx.class,"rightBack");
        leftBack = hardwareMap.get(DcMotorEx.class,"leftBack");
        vSlide = hardwareMap.get(DcMotorEx.class,"vSlide");
        hSlide = hardwareMap.get(DcMotorEx.class,"hSlide");
        imu = hardwareMap.get(IMU.class, "imu");
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        rDiffy = hardwareMap.servo.get("rDiffy");
        lDiffy = hardwareMap.servo.get("lDiffy");
        lvArm = hardwareMap.servo.get("lvArm");
        rvArm = hardwareMap.servo.get("rvArm");
        hClaw = hardwareMap.servo.get("hClaw");
        vClaw = hardwareMap.servo.get("vClaw");
        lhArm = hardwareMap.servo.get("lhArm");
        rhArm = hardwareMap.servo.get("rhArm");
        vWrist = hardwareMap.servo.get("vWrist");
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        vSlide.setDirection(DcMotor.Direction.REVERSE);
        hSlide.setDirection(DcMotor.Direction.FORWARD);
        hSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        limelight.setPollRateHz(100);
        limelight.pipelineSwitch(0);
        follower = new Follower(hardwareMap);
        follower.setStartingPose(startPose);
        diffy = new Diffy(lDiffy, rDiffy, limelight);
        horiClaw = new HorizontalClaw(hClaw);
        horiArm = new HorizontalArm(lhArm, rhArm);
        horizontalSlides = new HorizontalSlides(hSlide, teleOp);
        verticalSlides = new VerticalSlides(vSlide, teleOp);
        verticalClaw = new VerticalClaw(vClaw);
        verticalWrist = new VerticalWrist(vWrist);
        vertArm = new VerticalArm(lvArm, rvArm);
        CommandScheduler.getInstance().registerSubsystem(diffy, horiClaw, horiArm, horizontalSlides, verticalSlides, verticalClaw, verticalWrist, vertArm);
        limelight.start();
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
