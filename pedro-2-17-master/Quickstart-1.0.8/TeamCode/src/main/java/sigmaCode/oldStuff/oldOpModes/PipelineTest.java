package sigmaCode.oldStuff.oldOpModes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;

@TeleOp(name="Pipeline Test")
public class PipelineTest extends LinearOpMode {

    private double goal;
    private Limelight3A limelight;
    private int pipeline;
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.setPollRateHz(100);
        waitForStart();
        limelight.start();
        limelight.pipelineSwitch(0);
        while (opModeIsActive()) {
            LLResult result = limelight.getLatestResult();
            telemetry.addData("Pipeline: ", pipeline);
            double[] outputs = result.getPythonOutput();
            String test = java.util.Arrays.toString(result.getPythonOutput());
            telemetry.addData("array ", outputs);
            telemetry.addData("string, ", test);

            if (gamepad1.a) {
                pipeline = 0;
                limelight.pipelineSwitch(pipeline);
            }
            if (gamepad1.b) {
                pipeline = 1;
                limelight.pipelineSwitch(pipeline);
            }
            if (result != null) {
                if (pipeline == 0) {
                    telemetry.addData("Angle: ", outputs[5]);
                    //uses output 5 bc andys pipeline uses that for angle
                }
                if (pipeline == 1) {
                    telemetry.addData("Angle: ", outputs[3]);
                    //uses output 3 bc tex pipeline uses that for angle
                }
                telemetry.addData("Valid", result.isValid());
            }
            telemetry.update();
        }
    }
}