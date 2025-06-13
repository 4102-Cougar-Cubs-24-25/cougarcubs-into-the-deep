package sigmaCode.oldStuff.oldOpModes;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

@TeleOp(name="hawk tuah teleop")
public class PremierTeleOp extends LinearOpMode{
    //define motors and variables here
    private DcMotor rightFront, leftFront, rightBack, leftBack, vSlide, hSlide;
    private Servo rDiffy, lDiffy, lvArm, rvArm, vClaw, hClaw, vWrist, lhArm, rhArm;
    private IMU imu;
    private Limelight3A limelight;
    private boolean hClawOpen = false;
    private boolean vClawOpen = false;
    private boolean sampleMode = false;
    private double sampleAngle, diffyTurn, clawAngle;

    public void runOpMode() throws InterruptedException {

        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftFront = hardwareMap.dcMotor.get("leftFront");
        rightBack = hardwareMap.dcMotor.get("rightBack");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        vSlide = hardwareMap.dcMotor.get("vSlide");
        hSlide = hardwareMap.dcMotor.get("hSlide");
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
        telemetry.setMsTransmissionInterval(11);
        limelight.setPollRateHz(100);
        limelight.pipelineSwitch(0);
        limelight.start();

        Gamepad karel = new Gamepad();
        Gamepad karelNow = new Gamepad();

        IMU.Parameters imuParameters;
        imuParameters = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.RIGHT, RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
        imu.initialize(imuParameters);

        waitForStart();
        lDiffy.setPosition(0.5);
        rDiffy.setPosition(0.5);
        imu.resetYaw();
        while (opModeIsActive()) {
            LLResult result = limelight.getLatestResult();
            double[] outputs = result.getPythonOutput();
            telemetry.addData("Angle ", outputs[5]);
            if (result != null) {
                Pose3D botpose = result.getBotpose();
                sampleAngle = outputs[5];
                clawAngle = sampleAngle - 90;
                diffyTurn = (((clawAngle / 2) * 1.5) / 355);
                telemetry.addData("Angle: ", sampleAngle);
                telemetry.addData("Turn", diffyTurn);
                telemetry.addData("Valid", result.isValid());
            }
            telemetry.update();

            if(gamepad1.y) {
                lDiffy.setPosition(0.7 + diffyTurn);
                rDiffy.setPosition(0.3 + diffyTurn);
            }

            if(gamepad1.a){
                lDiffy.setPosition(0.7);
                rDiffy.setPosition(0.3);
            }

            if(gamepad1.b){
                lDiffy.setPosition(0.5 + diffyTurn);
                rDiffy.setPosition(0.5 + diffyTurn);
            }

            karel.copy(karelNow);
            karelNow.copy(gamepad2);

            double y = -gamepad1.left_stick_x;
            double x = gamepad1.left_stick_y;
            double rx = gamepad1.right_stick_x;
            double div = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

            leftFront.setPower(Math.pow((y + x - (rx*.85)),5)/div);
            leftBack.setPower(Math.pow((y - x + (rx*.85)),5)/div);
            rightFront.setPower(Math.pow((y - x - (rx*.85)),5)/div);
            rightBack.setPower(Math.pow((y + x + (rx*.85)),5)/div);

            if(gamepad1.b){
                sampleMode = !sampleMode;
            }

            if(gamepad2.right_trigger > 0){
                vSlide.setPower(-0.9);
            } else if(gamepad2.right_bumper){
                vSlide.setPower(0.7);
            } else {
                vSlide.setPower(-0.1);
            }

            if(gamepad2.left_bumper){
                hSlide.setPower(0.9);
            } else if(gamepad2.left_trigger > 0){
                hSlide.setPower(-0.6);
            } else {
                hSlide.setPower(0);
            }

            if(gamepad2.dpad_down){
                if(!sampleMode) {
                    lvArm.setPosition(1);
                    rvArm.setPosition(.35);
                } else {
                    lvArm.setPosition(.6);
                    rvArm.setPosition(.1);
                }
            }

            if(gamepad2.dpad_up){
                if(!sampleMode) {
                    lvArm.setPosition(.35);
                    rvArm.setPosition(1);
                } else {
                    lvArm.setPosition(.1);
                    rvArm.setPosition(.6);
                }
            }

            if(gamepad2.dpad_right){
                rhArm.setPosition(0);
                lhArm.setPosition(.197);
            }

            if(gamepad2.dpad_left){
                rhArm.setPosition(.197);
                lhArm.setPosition(0);
            }

            if(karelNow.x && !karel.x){
                hClawOpen = !hClawOpen;
            }
            if(gamepad2.x) {
                if (hClawOpen) {
                    hClaw.setPosition(0);
                } else {
                    hClaw.setPosition(.8);
                }
            }

            if(karelNow.b && !karel.b){
                vClawOpen = !vClawOpen;
            }
            if(gamepad2.b) {
                if (vClawOpen) {
                    vClaw.setPosition(.8);
                } else {
                    vClaw.setPosition(0);
                }
            }

            if(gamepad2.a){
                vWrist.setPosition(1);
            }

            if(gamepad2.y){
                vWrist.setPosition(.8);
            }
        }
        limelight.stop();
    }
}
