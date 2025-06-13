package sigmaCode.currentStuff.freakySubsystems;
import static sigmaCode.currentStuff.freakySubsystems.Diffy.diffyState.START;
import static sigmaCode.currentStuff.freakySubsystems.HorizontalArm.armState.UP;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.pedropathing.localization.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import sigmaCode.currentStuff.feinCommands.DiffyCommand;
import sigmaCode.currentStuff.feinCommands.HorizontalArmCommand;

@Config
@TeleOp
public class PIDF extends OpMode {
    private Servo rDiffy, lDiffy, lvArm, rvArm, vClaw, hClaw, vWrist, lhArm, rhArm;

    private PIDController controller;
    public static double p = 0, i = 0, d = 0;
    public static double f = 0;
    public static int target = 0;
    private final double ticks_in_degrees = 700 / 180.0;
    private DcMotorEx hSlide;
    @Override
    public void init() {
        lvArm = hardwareMap.servo.get("lvArm");
        rvArm = hardwareMap.servo.get("rvArm");
        hClaw = hardwareMap.servo.get("hClaw");
        vClaw = hardwareMap.servo.get("vClaw");
        lhArm = hardwareMap.servo.get("lhArm");
        rhArm = hardwareMap.servo.get("rhArm");
        rDiffy = hardwareMap.servo.get("rDiffy");
        lDiffy = hardwareMap.servo.get("lDiffy");
        controller = new PIDController(p, i, d);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hSlide = hardwareMap.get(DcMotorEx.class,"hSlide");
        rhArm.setPosition(.7275);
        lhArm.setPosition(.7275);
        rDiffy.setPosition(.55);
        lDiffy.setPosition(.45);
    }
    @Override
    public void loop() {
        controller.setPID(p, i, d);
        int pos = hSlide.getCurrentPosition();
        double pid = controller.calculate(pos, target);
        double power = pid + f;
        hSlide.setPower(power);
        telemetry.addData("pos", pos);
        telemetry.addData("target", target);
        telemetry.update();
    }
}