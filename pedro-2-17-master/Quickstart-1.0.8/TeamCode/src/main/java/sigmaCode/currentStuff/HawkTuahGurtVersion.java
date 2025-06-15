package sigmaCode.currentStuff;
import static sigmaCode.currentStuff.freakySubsystems.Diffy.diffyState.HANDOFF;
import static sigmaCode.currentStuff.freakySubsystems.Diffy.diffyState.START;
import static sigmaCode.currentStuff.freakySubsystems.Diffy.diffyState.TURN;
import static sigmaCode.currentStuff.freakySubsystems.HorizontalArm.armState.DOWN;
import static sigmaCode.currentStuff.freakySubsystems.HorizontalArm.armState.RETRACTED;
import static sigmaCode.currentStuff.freakySubsystems.HorizontalArm.armState.UP;
import static sigmaCode.currentStuff.freakySubsystems.HorizontalClaw.clawState.CLOSED;
import static sigmaCode.currentStuff.freakySubsystems.HorizontalClaw.clawState.LOOSE;
import static sigmaCode.currentStuff.freakySubsystems.HorizontalClaw.clawState.OPENED;
import static sigmaCode.currentStuff.freakySubsystems.VerticalArm.armState.MIDTRANSFER;
import static sigmaCode.currentStuff.freakySubsystems.VerticalArm.armState.RAMSPEC;
import static sigmaCode.currentStuff.freakySubsystems.VerticalArm.armState.SAMPSCORE;
import static sigmaCode.currentStuff.freakySubsystems.VerticalArm.armState.SPECSCORE;
import static sigmaCode.currentStuff.freakySubsystems.VerticalArm.armState.SPECWALL;
import static sigmaCode.currentStuff.freakySubsystems.VerticalArm.armState.TRANSFER;
import static sigmaCode.currentStuff.freakySubsystems.VerticalClaw.clawState.CLOSE;
import static sigmaCode.currentStuff.freakySubsystems.VerticalClaw.clawState.OPEN;
import static sigmaCode.currentStuff.freakySubsystems.VerticalSlides.liftState.SPECUP;
import static sigmaCode.currentStuff.freakySubsystems.VerticalWrist.wristState.AUTOSPEC;
import static sigmaCode.currentStuff.freakySubsystems.VerticalWrist.wristState.NORMAL;
import static sigmaCode.currentStuff.freakySubsystems.VerticalWrist.wristState.SAMPDROP;
import static sigmaCode.currentStuff.freakySubsystems.VerticalWrist.wristState.SPECPLACE;
import static sigmaCode.currentStuff.freakySubsystems.VerticalSlides.liftState.SAMPUP;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.pedropathing.localization.Pose;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import sigmaCode.currentStuff.feinCommands.ActivateLiftCommand;
import sigmaCode.currentStuff.feinCommands.DiffyCommand;
import sigmaCode.currentStuff.feinCommands.HorizontalArmCommand;
import sigmaCode.currentStuff.feinCommands.HorizontalClawCommand;
import sigmaCode.currentStuff.feinCommands.HorizontalSlidesCommand;
import sigmaCode.currentStuff.feinCommands.VerticalArmCommand;
import sigmaCode.currentStuff.feinCommands.VerticalClawCommand;
import sigmaCode.currentStuff.feinCommands.VerticalSlidesCommand;
import sigmaCode.currentStuff.feinCommands.VerticalWristCommand;
import sigmaCode.currentStuff.freakySubsystems.IzzyV2;
import sigmaCode.currentStuff.freakySubsystems.TelemetryUtil;
import sigmaCode.currentStuff.freakySubsystems.VerticalSlides;
@TeleOp(name = "RED hawk tuah (gurt's version)")
public class HawkTuahGurtVersion extends LinearOpMode {
    Pose startPose = new Pose(0, 0, 0);
    public boolean sampleMode = false;
    private boolean hClawOpen = true;
    private boolean vClawOpen = true;
    @Override
    public void runOpMode() throws InterruptedException {
        TelemetryUtil.setup(telemetry);
        IzzyV2 izzy = new IzzyV2(hardwareMap, startPose, true);
        GamepadEx gp1 = new GamepadEx(gamepad1);
        GamepadEx gp2 = new GamepadEx(gamepad2);
        izzy.rightFront.setDirection(DcMotor.Direction.FORWARD);
        izzy.leftFront.setDirection(DcMotor.Direction.FORWARD);
        izzy.rightBack.setDirection(DcMotor.Direction.REVERSE);
        izzy.leftBack.setDirection(DcMotor.Direction.REVERSE);

        gp2.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(() -> {
            if(!sampleMode){
                new VerticalArmCommand(izzy.vertArm, SPECWALL).schedule();
                new VerticalWristCommand(izzy.verticalWrist, NORMAL).schedule();
                new VerticalClawCommand(izzy.verticalClaw, OPEN).schedule();
                vClawOpen = true;
            } else {
                new VerticalArmCommand(izzy.vertArm, MIDTRANSFER).schedule();
                new VerticalWristCommand(izzy.verticalWrist, NORMAL).schedule();
            }
        });

        gp2.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(() -> {
           if(!sampleMode){
               new VerticalArmCommand(izzy.vertArm, RAMSPEC).schedule();
               new VerticalWristCommand(izzy.verticalWrist, SPECPLACE).schedule();
               new VerticalClawCommand(izzy.verticalClaw, CLOSE).schedule();
               new HorizontalArmCommand(izzy.horiArm, RETRACTED).schedule();
               vClawOpen = false;
           } else {
               new VerticalArmCommand(izzy.vertArm, SAMPSCORE).schedule();
               new VerticalWristCommand(izzy.verticalWrist, SAMPDROP).schedule();
           }
        });

        gp1.getGamepadButton(GamepadKeys.Button.B).whenPressed(() -> {
            sampleMode = !sampleMode;
            if(sampleMode) {
                izzy.limelight.pipelineSwitch(2);
            } else {
                izzy.limelight.pipelineSwitch(0);
            }
        });

        gp2.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenPressed(() -> {
            new HorizontalArmCommand(izzy.horiArm, UP).schedule();
            new DiffyCommand(izzy.diffy, START).schedule();
        });

        gp2.getGamepadButton(GamepadKeys.Button.X).whenPressed(() -> {
            hClawOpen = !hClawOpen;
            if(hClawOpen){
                new HorizontalClawCommand(izzy.horiClaw, OPENED).schedule();
            } else {
               new HorizontalClawCommand(izzy.horiClaw, CLOSED).schedule();
           }
        });

        gp2.getGamepadButton(GamepadKeys.Button.B).whenPressed(() -> {
            vClawOpen = !vClawOpen;
            if(vClawOpen){
                new VerticalClawCommand(izzy.verticalClaw, OPEN).schedule();
            } else {
                new VerticalClawCommand(izzy.verticalClaw, CLOSE).schedule();
            }
        });

        gp2.getGamepadButton(GamepadKeys.Button.A).whenPressed(new SequentialCommandGroup(
                new VerticalArmCommand(izzy.vertArm,SPECSCORE),
                new VerticalWristCommand(izzy.verticalWrist, AUTOSPEC)
                ));

        gp2.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(new SequentialCommandGroup(
                new DiffyCommand(izzy.diffy, TURN),
                new WaitCommand(75),
                new HorizontalArmCommand(izzy.horiArm, DOWN)
        ));

        gp2.getGamepadButton(GamepadKeys.Button.BACK).whenPressed(() -> {
            new SequentialCommandGroup(
                    new VerticalArmCommand(izzy.vertArm, MIDTRANSFER),
                    new HorizontalArmCommand(izzy.horiArm, UP),
                    new VerticalClawCommand(izzy.verticalClaw, OPEN),
                    new DiffyCommand(izzy.diffy, HANDOFF),
                    new HorizontalClawCommand(izzy.horiClaw, LOOSE),
                    new WaitCommand(300),
                    new VerticalArmCommand(izzy.vertArm, TRANSFER),
                    new VerticalWristCommand(izzy.verticalWrist, NORMAL),
                    new WaitCommand(300),
                    new VerticalClawCommand(izzy.verticalClaw, CLOSE),
                    new WaitCommand(150),
                    new HorizontalClawCommand(izzy.horiClaw, OPENED)
            ).schedule();
            vClawOpen = false;
            hClawOpen = true;
        });

        while(opModeInInit()){
            izzy.loop();
        }

        waitForStart();
        new HorizontalArmCommand(izzy.horiArm, UP).schedule();
        new DiffyCommand(izzy.diffy, START).schedule();

        if(isStarted()){
            izzy.follower.startTeleopDrive();
        }

        while(!isStopRequested() && opModeIsActive()){
            TelemetryUtil.addData("sample mode", sampleMode);
            TelemetryUtil.addData("sample angle", izzy.diffy.getAngle());
            double y = -gamepad1.left_stick_x;
            double x = gamepad1.left_stick_y;
            double rx = gamepad1.right_stick_x;
            double div = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

            izzy.leftFront.setPower(Math.pow((y + x - (rx*.85)),5)/div);
            izzy.leftBack.setPower(Math.pow((y - x + (rx*.85)),5)/div);
            izzy.rightFront.setPower(Math.pow((y - x - (rx*.85)),5)/div);
            izzy.rightBack.setPower(Math.pow((y + x + (rx*.85)),5)/div);

            if(gamepad2.right_trigger > 0){
                izzy.vSlide.setPower(-1);
            } else if(gamepad2.right_bumper){
                izzy.vSlide.setPower(0.5);
            } else {
                izzy.vSlide.setPower(-0.1);
            }

            if(gamepad2.left_bumper){
                izzy.hSlide.setPower(0.9);
            } else if(gamepad2.left_trigger > 0){
                izzy.hSlide.setPower(-0.6);
            } else {
                izzy.hSlide.setPower(0);
            }
            izzy.loop();
        }
        izzy.limelight.stop();
    }
}