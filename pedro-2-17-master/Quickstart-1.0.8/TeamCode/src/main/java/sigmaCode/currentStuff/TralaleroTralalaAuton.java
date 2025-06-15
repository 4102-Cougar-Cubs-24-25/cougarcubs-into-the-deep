package sigmaCode.currentStuff;

import static sigmaCode.currentStuff.freakySubsystems.Diffy.diffyState.HANDOFF;
import static sigmaCode.currentStuff.freakySubsystems.Diffy.diffyState.SAMPLE;
import static sigmaCode.currentStuff.freakySubsystems.Diffy.diffyState.START;
import static sigmaCode.currentStuff.freakySubsystems.Diffy.diffyState.THIRD;
import static sigmaCode.currentStuff.freakySubsystems.Diffy.diffyState.TURN;
import static sigmaCode.currentStuff.freakySubsystems.HorizontalArm.armState.RETRACTED;
import static sigmaCode.currentStuff.freakySubsystems.HorizontalArm.armState.UP;
import static sigmaCode.currentStuff.freakySubsystems.HorizontalClaw.clawState.CLOSED;
import static sigmaCode.currentStuff.freakySubsystems.HorizontalClaw.clawState.LOOSE;
import static sigmaCode.currentStuff.freakySubsystems.HorizontalClaw.clawState.OPENED;
import static sigmaCode.currentStuff.freakySubsystems.VerticalArm.armState.MIDTRANSFER;
import static sigmaCode.currentStuff.freakySubsystems.VerticalArm.armState.SAMPSCORE;
import static sigmaCode.currentStuff.freakySubsystems.VerticalArm.armState.SPECSCORE;
import static sigmaCode.currentStuff.freakySubsystems.VerticalArm.armState.TRANSFER;
import static sigmaCode.currentStuff.freakySubsystems.VerticalClaw.clawState.CLOSE;
import static sigmaCode.currentStuff.freakySubsystems.VerticalClaw.clawState.OPEN;
import static sigmaCode.currentStuff.freakySubsystems.VerticalSlides.liftState.DOWN;
import static sigmaCode.currentStuff.freakySubsystems.VerticalSlides.liftState.MIDDLE;
import static sigmaCode.currentStuff.freakySubsystems.VerticalSlides.liftState.SAMPUP;
import static sigmaCode.currentStuff.freakySubsystems.VerticalWrist.wristState.AUTOSPEC;
import static sigmaCode.currentStuff.freakySubsystems.VerticalWrist.wristState.NORMAL;
import static sigmaCode.currentStuff.freakySubsystems.VerticalWrist.wristState.SAMPDROP;
import static sigmaCode.currentStuff.freakySubsystems.VerticalWrist.wristState.SPECPLACE;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;
import sigmaCode.currentStuff.feinCommands.ActivateLiftCommand;
import sigmaCode.currentStuff.feinCommands.DiffyCommand;
import sigmaCode.currentStuff.feinCommands.FollowPathCommand;
import sigmaCode.currentStuff.feinCommands.HorizontalArmCommand;
import sigmaCode.currentStuff.feinCommands.HorizontalClawCommand;
import sigmaCode.currentStuff.feinCommands.VerticalArmCommand;
import sigmaCode.currentStuff.feinCommands.VerticalClawCommand;
import sigmaCode.currentStuff.feinCommands.VerticalSlidesCommand;
import sigmaCode.currentStuff.feinCommands.VerticalWristCommand;
import sigmaCode.currentStuff.freakySubsystems.Diffy;
import sigmaCode.currentStuff.freakySubsystems.HorizontalArm;
import sigmaCode.currentStuff.freakySubsystems.IzzyV2;
import sigmaCode.currentStuff.freakySubsystems.TelemetryUtil;

@Autonomous(name = "tralalero tralala!")

public class TralaleroTralalaAuton extends LinearOpMode {
    private ElapsedTime timer;
    private final Pose startPose = new Pose(7.665, 112.413, Math.toRadians(0));
    IzzyV2 izzy;
    private PathChain line1, line2, line3, line4, line5, line6, line7, line8;
    public void buildPaths(){
        line1 = izzy.follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(7.665, 112.413, Point.CARTESIAN),
                                new Point(29.961, 113.342, Point.CARTESIAN),
                                new Point(12.006, 132, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(-45))
                .setZeroPowerAccelerationMultiplier(3.75)
                .build();

        line2 = izzy.follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(13.006, 132, Point.CARTESIAN),
                                new Point(32.752, 121.403, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(-45), Math.toRadians(0))
                .setZeroPowerAccelerationMultiplier(3.5)
                .build();

        line3 = izzy.follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(32.052, 121.703, Point.CARTESIAN),
                                new Point(12.006, 132, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(-45))
                .setZeroPowerAccelerationMultiplier(3.75)
                .build();

        line4 = izzy.follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(12.006, 132, Point.CARTESIAN),
                                new Point(32.748, 131.226, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(-45), Math.toRadians(0))
                .setZeroPowerAccelerationMultiplier(3.75)
                .build();

        line5 = izzy.follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(32.748, 131.226, Point.CARTESIAN),
                                new Point(12.006, 132, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(-45))
                .setZeroPowerAccelerationMultiplier(3.75)
                .build();

        line6 = izzy.follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(13.006, 132, Point.CARTESIAN),
                                new Point(35.535, 120.774, Point.CARTESIAN),
                                new Point(45.755, 128.1, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(-45), Math.toRadians(90))
                .setZeroPowerAccelerationMultiplier(3.75)
                .build();

        line7 = izzy.follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(45.755, 128.1, Point.CARTESIAN),
                                new Point(35.768, 120.542, Point.CARTESIAN),
                                new Point(13.006, 132, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(-45))
                .setZeroPowerAccelerationMultiplier(3.75)
                .build();

        line8 = izzy.follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(13.006, 132, Point.CARTESIAN),
                                new Point(64.103, 113.342, Point.CARTESIAN),
                                new Point(60.852, 95.690, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(-45), Math.toRadians(-90))
                .setZeroPowerAccelerationMultiplier(2)
                .build();
    }
    public void scheduleAuto(){
        CommandScheduler.getInstance().schedule(
                new RunCommand(() -> izzy.follower.update()),
                new SequentialCommandGroup(
                        new HorizontalArmCommand(izzy.horiArm, UP),
                        new DiffyCommand(izzy.diffy, HANDOFF),
                        new ParallelCommandGroup(
                                new ActivateLiftCommand(izzy),
                                new VerticalSlidesCommand(izzy, SAMPUP),
                                new VerticalWristCommand(izzy.verticalWrist, SAMPDROP),
                                new SequentialCommandGroup(
                                        new WaitCommand(1300),
                                        new VerticalArmCommand(izzy.vertArm, SAMPSCORE)
                                ),
                                new FollowPathCommand(izzy.follower, line1)
                        ),
                        //at bucket
                        new WaitCommand(500),
                        new VerticalClawCommand(izzy.verticalClaw, OPEN),
                        //1st sample scored
                        new WaitCommand(150),
                        new ParallelCommandGroup(
                                new FollowPathCommand(izzy.follower, line2),
                                new VerticalArmCommand(izzy.vertArm, MIDTRANSFER),
                                new VerticalSlidesCommand(izzy, DOWN)
                        ),
                        new WaitCommand(350),
                        new ActivateLiftCommand(izzy),
                        new DiffyCommand(izzy.diffy, SAMPLE),
                        new HorizontalArmCommand(izzy.horiArm, HorizontalArm.armState.DOWN),
                        new WaitCommand(200),
                        new HorizontalClawCommand(izzy.horiClaw, CLOSED),
                        //2nd sample picked up
                        new WaitCommand(300),
                        new HorizontalArmCommand(izzy.horiArm, UP),
                        new VerticalClawCommand(izzy.verticalClaw, OPEN),
                        new WaitCommand(300),
                        new DiffyCommand(izzy.diffy, HANDOFF),
                        new HorizontalClawCommand(izzy.horiClaw, LOOSE),
                        new WaitCommand(300),
                        new VerticalArmCommand(izzy.vertArm, TRANSFER),
                        new VerticalWristCommand(izzy.verticalWrist, NORMAL),
                        new WaitCommand(500),
                        new VerticalClawCommand(izzy.verticalClaw, CLOSE),
                        new WaitCommand(150),
                        new HorizontalClawCommand(izzy.horiClaw, OPENED),
                        //2nd sample transferred
                        new ParallelCommandGroup(
                                new ActivateLiftCommand(izzy),
                                new VerticalSlidesCommand(izzy, SAMPUP),
                                new VerticalWristCommand(izzy.verticalWrist, SAMPDROP),
                                new SequentialCommandGroup(
                                        new WaitCommand(1300),
                                        new VerticalArmCommand(izzy.vertArm, SAMPSCORE)
                                ),
                                new FollowPathCommand(izzy.follower, line3)
                        ),
                        //at bucket
                        new WaitCommand(500),
                        new VerticalClawCommand(izzy.verticalClaw, OPEN),
                        //2nd sample scored
                        new WaitCommand(150),
                        new ParallelCommandGroup(
                                new FollowPathCommand(izzy.follower, line4),
                                new VerticalArmCommand(izzy.vertArm, MIDTRANSFER),
                                new VerticalSlidesCommand(izzy, DOWN)
                        ),
                        new WaitCommand(350),
                        new ActivateLiftCommand(izzy),
                        new DiffyCommand(izzy.diffy, SAMPLE),
                        new HorizontalArmCommand(izzy.horiArm, HorizontalArm.armState.DOWN),
                        new WaitCommand(200),
                        new HorizontalClawCommand(izzy.horiClaw, CLOSED),
                        //3rd sample picked up
                        new WaitCommand(300),
                        new HorizontalArmCommand(izzy.horiArm, UP),
                        new VerticalClawCommand(izzy.verticalClaw, OPEN),
                        new WaitCommand(300),
                        new DiffyCommand(izzy.diffy, HANDOFF),
                        new HorizontalClawCommand(izzy.horiClaw, LOOSE),
                        new WaitCommand(300),
                        new VerticalArmCommand(izzy.vertArm, TRANSFER),
                        new VerticalWristCommand(izzy.verticalWrist, NORMAL),
                        new WaitCommand(500),
                        new VerticalClawCommand(izzy.verticalClaw, CLOSE),
                        new WaitCommand(150),
                        new HorizontalClawCommand(izzy.horiClaw, OPENED),
                        //3rd sample transferred
                        new ParallelCommandGroup(
                                new ActivateLiftCommand(izzy),
                                new VerticalSlidesCommand(izzy, SAMPUP),
                                new VerticalWristCommand(izzy.verticalWrist, SAMPDROP),
                                new SequentialCommandGroup(
                                        new WaitCommand(1300),
                                        new VerticalArmCommand(izzy.vertArm, SAMPSCORE)
                                ),
                                new FollowPathCommand(izzy.follower, line5)
                        ),
                        //at bucket
                        new WaitCommand(500),
                        new VerticalClawCommand(izzy.verticalClaw, OPEN),
                        //3rd sample scored
                        new WaitCommand(150),
                        new ParallelCommandGroup(
                                new FollowPathCommand(izzy.follower, line6),
                                new VerticalArmCommand(izzy.vertArm, MIDTRANSFER),
                                new VerticalSlidesCommand(izzy, DOWN)
                        ),
                        new WaitCommand(350),
                        new ActivateLiftCommand(izzy),
                        new DiffyCommand(izzy.diffy, THIRD),
                        new WaitCommand(350),
                        new HorizontalArmCommand(izzy.horiArm, HorizontalArm.armState.DOWN),
                        new WaitCommand(500),
                        new HorizontalClawCommand(izzy.horiClaw, CLOSED),
                        //4th sample picked up
                        new WaitCommand(300),
                        new ParallelCommandGroup(
                                new SequentialCommandGroup(
                                        new HorizontalArmCommand(izzy.horiArm, UP),
                                        new VerticalClawCommand(izzy.verticalClaw, OPEN),
                                        new WaitCommand(300),
                                        new DiffyCommand(izzy.diffy, HANDOFF),
                                        new HorizontalClawCommand(izzy.horiClaw, LOOSE),
                                        new WaitCommand(300),
                                        new VerticalArmCommand(izzy.vertArm, TRANSFER),
                                        new VerticalWristCommand(izzy.verticalWrist, NORMAL),
                                        new WaitCommand(750),
                                        new VerticalClawCommand(izzy.verticalClaw, CLOSE),
                                        new WaitCommand(150),
                                        new HorizontalClawCommand(izzy.horiClaw, OPENED),
                                        //4th sample transferred
                                        new ActivateLiftCommand(izzy),
                                        new VerticalSlidesCommand(izzy, SAMPUP),
                                        new WaitCommand(1300),
                                        new VerticalWristCommand(izzy.verticalWrist, SAMPDROP),
                                        new VerticalArmCommand(izzy.vertArm, SAMPSCORE)
                                ),
                                new FollowPathCommand(izzy.follower, line7)
                        ),
                        //at bucket
                        new WaitCommand(500),
                        new VerticalClawCommand(izzy.verticalClaw, OPEN),
                        //4th sample scored
                        new WaitCommand(150),
                        new ParallelCommandGroup(
                                new FollowPathCommand(izzy.follower, line8),
                                new VerticalArmCommand(izzy.vertArm, SPECSCORE),
                                new VerticalSlidesCommand(izzy, MIDDLE)
                        )
                        //at bar
                ));
    }
    public void scheduleInit(){
        CommandScheduler.getInstance().schedule(
                new RunCommand(() -> izzy.follower.update()),
                new SequentialCommandGroup(
                        new ParallelCommandGroup(
                                new HorizontalArmCommand(izzy.horiArm, RETRACTED),
                                new DiffyCommand(izzy.diffy, START),
                                new VerticalArmCommand(izzy.vertArm, SPECSCORE),
                                new VerticalWristCommand(izzy.verticalWrist, SPECPLACE),
                                new VerticalClawCommand(izzy.verticalClaw, CLOSE),
                                new HorizontalClawCommand(izzy.horiClaw, OPENED)
                        )
                )
        );
    }
    public void runOpMode() {
        TelemetryUtil.setup(telemetry);
        timer = new ElapsedTime();
        timer.reset();
        CommandScheduler.getInstance().reset();
        Constants.setConstants(FConstants.class, LConstants.class);
        izzy = new IzzyV2(hardwareMap, startPose, false);
        buildPaths();
        scheduleInit();
        while(opModeInInit()){
            izzy.loop();
        }
        scheduleAuto();
        waitForStart();
        while(!isStopRequested() && opModeIsActive()){
            izzy.loop();
        }
        CommandScheduler.getInstance().reset();
    }
}
