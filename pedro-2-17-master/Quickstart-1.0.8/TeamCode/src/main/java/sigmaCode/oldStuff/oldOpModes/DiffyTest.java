package sigmaCode.oldStuff.oldOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Diffy tester")
public class DiffyTest extends LinearOpMode {

    // Declare OpMode members.
    private Servo left = null;
    private Servo right = null;
    private int goal = 90;//goal degrees for diffy
    private double turn;
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        left  = hardwareMap.get(Servo.class, "diffyLeft");
        right = hardwareMap.get(Servo.class, "diffyRight");
        turn = (((goal/2)*1.5)/355);
        waitForStart();
        left.setPosition(0.5);
        right.setPosition(0.5);
        while (opModeIsActive()) {
            if (gamepad1.a){
                left.setPosition(0.5);
                right.setPosition(0.5);
            }
            if (gamepad1.b){
                left.setPosition(0.5+turn);
                right.setPosition(0.5+turn);
            }
            if (gamepad1.x){
                left.setPosition(0.5-turn);
                right.setPosition(0.5-turn);
            }
        }
    }
}