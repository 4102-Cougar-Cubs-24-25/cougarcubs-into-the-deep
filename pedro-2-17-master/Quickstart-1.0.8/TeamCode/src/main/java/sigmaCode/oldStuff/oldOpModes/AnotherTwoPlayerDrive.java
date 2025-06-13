package sigmaCode.oldStuff.oldOpModes;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
@TeleOp(name="sigma sigma 0.1")
public class AnotherTwoPlayerDrive extends LinearOpMode{
    //define motors and variables here
    private DcMotor rightFront, leftFront, rightBack, leftBack, vSlide, hSlide;
    private Servo rhWrist, lhWrist, lvWrist, rvWrist, vClaw, hClaw, clawSpinner, sampleclaw;
    private IMU imu;
    private boolean hClawOpen = false;
    private boolean vClawOpen = false;
    private boolean sampleclawOpen = false;
    private boolean spin = false;

    public void runOpMode() throws InterruptedException {

        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftFront = hardwareMap.dcMotor.get("leftFront");
        rightBack = hardwareMap.dcMotor.get("rightBack");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        vSlide = hardwareMap.dcMotor.get("vSlide");
        hSlide = hardwareMap.dcMotor.get("hSlide");
        lvWrist = hardwareMap.servo.get("lvWrist");
        rvWrist = hardwareMap.servo.get("rvWrist");
        lhWrist = hardwareMap.servo.get("lhWrist");
        rhWrist = hardwareMap.servo.get("rhWrist");
        hClaw = hardwareMap.servo.get("hClaw");
        vClaw = hardwareMap.servo.get("vClaw");
        sampleclaw = hardwareMap.servo.get("sClaw");
        clawSpinner = hardwareMap.servo.get("clawSpinner");
        imu = hardwareMap.get(IMU.class, "imu");

        rightFront.setDirection(DcMotor.Direction.FORWARD);
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);

        vSlide.setDirection(DcMotor.Direction.REVERSE);
        hSlide.setDirection(DcMotor.Direction.FORWARD);

        hSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Gamepad karel = new Gamepad();
        Gamepad karelNow = new Gamepad();
        Gamepad izzy = new Gamepad();
        Gamepad izzyNow = new Gamepad();

        IMU.Parameters imuParameters;
        imuParameters = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.RIGHT, RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
        imu.initialize(imuParameters);
        rhWrist.setPosition(.35);
        lhWrist.setPosition(0);

        waitForStart();
        imu.resetYaw();
        while (opModeIsActive()) {

            telemetry.addData("sigma (imu)", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
            telemetry.addData("rizz (lf)", leftFront.getCurrentPosition());
            telemetry.addData("aura (h)", hClawOpen);
            telemetry.addData("fein (v)", vClaw.getPosition());
            telemetry.addData("huzz (vs)", vSlide.getCurrentPosition());

            telemetry.update();

            karel.copy(karelNow);
            karelNow.copy(gamepad2);
            izzy.copy(izzyNow);
            izzyNow.copy(gamepad1);

            double y = -gamepad1.left_stick_x;
            double x = gamepad1.left_stick_y;
            double rx = gamepad1.right_stick_x;
            double div = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

            leftFront.setPower(Math.pow((y + x - (rx*.85)),5)/div);
            leftBack.setPower(Math.pow((y - x + (rx*.85)),5)/div);
            rightFront.setPower(Math.pow((y - x - (rx*.85)),5)/div);
            rightBack.setPower(Math.pow((y + x + (rx*.85)),5)/div);

            if(gamepad2.right_trigger > 0){
                vSlide.setPower(-0.9);
            } else if(gamepad2.right_bumper){
                vSlide.setPower(0.7);
            } else {
                vSlide.setPower(-0.1);
            }

            if(gamepad1.left_bumper){
                hSlide.setPower(0.9);
            } else if(gamepad1.left_trigger > 0){
                hSlide.setPower(-0.6);
            } else {
                hSlide.setPower(0);
            }

            //all wrists and claws are normal (positional) servos
            if(gamepad2.dpad_down){
                lvWrist.setPosition(0);
                rvWrist.setPosition(.66);
            }

            if(gamepad2.dpad_up){
                lvWrist.setPosition(.66);
                rvWrist.setPosition(0);
            }

            if(gamepad1.dpad_right){
                rhWrist.setPosition(0);
                lhWrist.setPosition(.35);
            }

            if(gamepad1.dpad_left){
                rhWrist.setPosition(.35);
                lhWrist.setPosition(0);
                clawSpinner.setPosition(.59);
                spin = false;
            }

            if(izzyNow.x && !izzy.x){
                hClawOpen = !hClawOpen;

                if(hClawOpen){
                    hClaw.setPosition(0);
                } else if(!hClawOpen){
                    hClaw.setPosition(.8);
                }
            }

            if(karelNow.b && !karel.b){
                vClawOpen = !vClawOpen;
            }
            if(gamepad2.b) {
                if (vClawOpen) {
                    vClaw.setPosition(.8);
                } else if (!vClawOpen) {
                    vClaw.setPosition(0);
                }
            }

            if(karelNow.a && !karel.a){
                sampleclawOpen = !sampleclawOpen;
            }
            if(gamepad2.a) {
                if (sampleclawOpen) {
                    sampleclaw.setPosition(.8);
                } else if (!sampleclawOpen) {
                    sampleclaw.setPosition(0);
                }
            }
            if(izzyNow.y && !izzy.y){
                spin = !spin;
            }
            if(gamepad1.y){
                if(spin){
                    clawSpinner.setPosition(.92);
                } else if(!spin){
                    clawSpinner.setPosition(.59);
                }
            }
        }
    }
}