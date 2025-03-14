package sigmaCode.currentStuff;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.pedropathing.util.Constants;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;
import sigmaCode.currentStuff.feinCommands.ActivateLiftCommand;
import sigmaCode.currentStuff.feinCommands.FollowPathCommand;
import sigmaCode.currentStuff.freakySubsystems.Izzy;
import sigmaCode.currentStuff.feinCommands.LiftClawCommand;
import sigmaCode.currentStuff.feinCommands.LiftCommand;
import sigmaCode.currentStuff.feinCommands.LiftWristCommand;
import sigmaCode.currentStuff.freakySubsystems.Lift;
import sigmaCode.currentStuff.freakySubsystems.LiftClaw;
import sigmaCode.currentStuff.freakySubsystems.LiftWrist;
import sigmaCode.currentStuff.freakySubsystems.TelemetryUtil;
@Autonomous(name = "5 thousand specs?")
public class SigmaRamenAuton extends LinearOpMode {
    private ElapsedTime timer;
    private final Pose startPose = new Pose(7.2, 63, Math.toRadians(0));
    Izzy izzy;
    private PathChain line1, line2, line3, line4, line5, line6, line7, line8, line9, line10, line11, line12, line13, line14, line15, line16;
    public void buildPaths() {
        line1 = izzy.follower.pathBuilder()
                .addPath(
                        // Line 1
                        new BezierLine(
                                new Point(7.200, 65.000, Point.CARTESIAN),
                                new Point(41.000, 65.000, Point.CARTESIAN)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .setZeroPowerAccelerationMultiplier(5)
                        .build();
        line2 = izzy.follower.pathBuilder()
                .addPath(
                        // Line 2
                        new BezierCurve(
                                new Point(39.000, 65.000, Point.CARTESIAN),
                                new Point(5.547, 33.746, Point.CARTESIAN),
                                new Point(88.526, 23.114, Point.CARTESIAN),
                                new Point(51.082, 22.883, Point.CARTESIAN)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        line3 = izzy.follower.pathBuilder()
                .addPath(
                        // Line 3
                        new BezierLine(
                                new Point(51.082, 22.883, Point.CARTESIAN),
                                new Point(18.029, 22.652, Point.CARTESIAN)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        line4 = izzy.follower.pathBuilder()
                .addPath(
                        // Line 4
                        new BezierCurve(
                                new Point(18.029, 22.652, Point.CARTESIAN),
                                new Point(68.904, 24.039, Point.CARTESIAN),
                                new Point(51.082, 15.024, Point.CARTESIAN)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        line5 = izzy.follower.pathBuilder()
                .addPath(
                        // Line 5
                        new BezierLine(
                                new Point(51.082, 15.024, Point.CARTESIAN),
                                new Point(18.260, 15.486, Point.CARTESIAN)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        line6 = izzy.follower.pathBuilder()
                .addPath(
                        // Line 6
                        new BezierCurve(
                                new Point(18.260, 15.486, Point.CARTESIAN),
                                new Point(70.446, 12.482, Point.CARTESIAN),
                                new Point(51.082, 8, Point.CARTESIAN)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        line7 = izzy.follower.pathBuilder()
                .addPath(
                        // Line 7
                        new BezierLine(
                                new Point(51.082, 8, Point.CARTESIAN),
                                new Point(16.873, 9.246, Point.CARTESIAN)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        line8 = izzy.follower.pathBuilder()
                .addPath(
                        // Line 8
                        new BezierCurve(
                                new Point(16.873, 9.246, Point.CARTESIAN),
                                new Point(33.746, 27.506, Point.CARTESIAN),
                                new Point(6.95, 33.000, Point.CARTESIAN)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .setPathEndVelocityConstraint(1)
                .setZeroPowerAccelerationMultiplier(3)
                .build();
        line9 = izzy.follower.pathBuilder()
                .addPath(
                        // Line 9
                        new BezierLine(
                                new Point(6.95, 33.000, Point.CARTESIAN),
                                new Point(40.000, 67.000, Point.CARTESIAN)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        line10 = izzy.follower.pathBuilder()
                .addPath(
                        // Line 10
                        new BezierLine(
                                new Point(40.000, 67.000, Point.CARTESIAN),
                                new Point(6.95, 33.000, Point.CARTESIAN)
                        )
                )
                .setPathEndVelocityConstraint(1)
                .setZeroPowerAccelerationMultiplier(3)
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        line11 = izzy.follower.pathBuilder()
                .addPath(
                        // Line 11
                        new BezierLine(
                                new Point(6.95, 33.000, Point.CARTESIAN),
                                new Point(40, 67.000, Point.CARTESIAN)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        line12 = izzy.follower.pathBuilder()
                .addPath(
                        // Line 12
                        new BezierLine(
                                new Point(40, 67.000, Point.CARTESIAN),
                                new Point(6.95, 33.000, Point.CARTESIAN)
                        )
                )
                .setPathEndVelocityConstraint(1)
                .setZeroPowerAccelerationMultiplier(3)
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        line13 = izzy.follower.pathBuilder()
                .addPath(
                        // Line 13
                        new BezierLine(
                                new Point(6.95, 33.000, Point.CARTESIAN),
                                new Point(40.000, 67.000, Point.CARTESIAN)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        line14 = izzy.follower.pathBuilder()
                .addPath(
                        // Line 14
                        new BezierLine(
                                new Point(40.000, 67.000, Point.CARTESIAN),
                                new Point(6.95, 33.000, Point.CARTESIAN)
                        )
                )
                .setPathEndVelocityConstraint(1)
                .setZeroPowerAccelerationMultiplier(3)
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        line15 = izzy.follower.pathBuilder()
                .addPath(
                        // Line 15
                        new BezierLine(
                                new Point(6.95, 33.000, Point.CARTESIAN),
                                new Point(40.000, 67.000, Point.CARTESIAN)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        line16 = izzy.follower.pathBuilder()
                .addPath(
                        // Line 16
                        new BezierLine(
                                new Point(40.000, 67.000, Point.CARTESIAN),
                                new Point(9.000, 33.000, Point.CARTESIAN)
                        )
                )
                .setPathEndVelocityConstraint(1)
                .setZeroPowerAccelerationMultiplier(3)
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
    }
    public void scheduleLiftTest(){
        CommandScheduler.getInstance().schedule(
                new RunCommand(() -> izzy.follower.update()),
                new SequentialCommandGroup(
                        new LiftCommand(izzy, Lift.liftState.MIDDLE),
                        new WaitCommand(2000),
                        new LiftCommand(izzy, Lift.liftState.UP),
                        new WaitCommand(2000),
                        new LiftCommand(izzy, Lift.liftState.DOWN),
                        new WaitCommand(2000)
                )
        );
    }
    public void scheduleAuto(){
        CommandScheduler.getInstance().schedule(
                new RunCommand(() -> izzy.follower.update()),
                new SequentialCommandGroup(
                        new ParallelCommandGroup(
                                new LiftCommand(izzy, Lift.liftState.MIDDLE),
                                new FollowPathCommand(izzy.follower, line1)
                        ),
                        new LiftCommand(izzy, Lift.liftState.UP),
                        new WaitCommand(285),
                        //1st spec scored
                        new ParallelCommandGroup(
                                new SequentialCommandGroup(
                                        new LiftCommand(izzy, Lift.liftState.DOWN),
                                        new WaitCommand(1700),
                                        new ActivateLiftCommand(izzy)
                                ),
                                new LiftClawCommand(izzy.liftClaw, LiftClaw.clawState.OPEN),
                                new LiftWristCommand(izzy.liftWrist, LiftWrist.wristState.BACK),
                                new SequentialCommandGroup(
                                        new FollowPathCommand(izzy.follower, line2),
                                        new FollowPathCommand(izzy.follower, line3),
                                        new FollowPathCommand(izzy.follower, line4),
                                        new FollowPathCommand(izzy.follower, line5),
                                        new FollowPathCommand(izzy.follower, line6),
                                        new FollowPathCommand(izzy.follower, line7)
                                )
                        ),
                        new ParallelCommandGroup(
                                new FollowPathCommand(izzy.follower, line8),
                                new ActivateLiftCommand(izzy)
                        ),
                        //at human zone
                        new LiftClawCommand(izzy.liftClaw, LiftClaw.clawState.CLOSE),
                        new WaitCommand(290),
                        //2nd spec picked up
                        new ParallelCommandGroup(
                                new SequentialCommandGroup(
                                        new WaitCommand(150),
                                        new LiftWristCommand(izzy.liftWrist, LiftWrist.wristState.FORWARD)
                                ),
                                new LiftCommand(izzy, Lift.liftState.MIDDLE),
                                new SequentialCommandGroup(
                                        new WaitCommand(50),
                                        new FollowPathCommand(izzy.follower, line9)
                                )
                        ),
                        new LiftCommand(izzy, Lift.liftState.UP),
                        new WaitCommand(285),
                        //2nd spec scored
                        new ParallelCommandGroup(
                                new FollowPathCommand(izzy.follower, line10),
                                new LiftCommand(izzy, Lift.liftState.DOWN),
                                new LiftClawCommand(izzy.liftClaw, LiftClaw.clawState.OPEN),
                                new LiftWristCommand(izzy.liftWrist, LiftWrist.wristState.BACK)
                        ),
                        //at human zone
                        new LiftClawCommand(izzy.liftClaw, LiftClaw.clawState.CLOSE),
                        new WaitCommand(290),
                        //3rd spec picked up
                        new ParallelCommandGroup(
                                new SequentialCommandGroup(
                                        new WaitCommand(150),
                                        new LiftWristCommand(izzy.liftWrist, LiftWrist.wristState.FORWARD)
                                ),
                                new LiftCommand(izzy, Lift.liftState.MIDDLE),
                                new SequentialCommandGroup(
                                        new WaitCommand(50),
                                        new FollowPathCommand(izzy.follower, line11)
                                )
                        ),
                        new LiftCommand(izzy, Lift.liftState.UP),
                        new WaitCommand(285),
                        //3rd spec scored
                        new ParallelCommandGroup(
                                new FollowPathCommand(izzy.follower, line12),
                                new LiftCommand(izzy, Lift.liftState.DOWN),
                                new LiftClawCommand(izzy.liftClaw, LiftClaw.clawState.OPEN),
                                new LiftWristCommand(izzy.liftWrist, LiftWrist.wristState.BACK)
                        ),
                        //at human zone
                        new LiftClawCommand(izzy.liftClaw, LiftClaw.clawState.CLOSE),
                        new WaitCommand(290),
                        //4th spec picked up
                        new ParallelCommandGroup(
                                new SequentialCommandGroup(
                                        new WaitCommand(150),
                                        new LiftWristCommand(izzy.liftWrist, LiftWrist.wristState.FORWARD)
                                ),
                                new LiftCommand(izzy, Lift.liftState.MIDDLE),
                                new SequentialCommandGroup(
                                        new WaitCommand(50),
                                        new FollowPathCommand(izzy.follower, line13)
                                )
                        ),
                        new LiftCommand(izzy, Lift.liftState.UP),
                        new WaitCommand(285),
                        //4th spec scored
                        new ParallelCommandGroup(
                                new FollowPathCommand(izzy.follower, line14),
                                new LiftCommand(izzy, Lift.liftState.DOWN),
                                new LiftClawCommand(izzy.liftClaw, LiftClaw.clawState.OPEN),
                                new LiftWristCommand(izzy.liftWrist, LiftWrist.wristState.BACK)
                        ),
                        //at human zone
                        new LiftClawCommand(izzy.liftClaw, LiftClaw.clawState.CLOSE),
                        new WaitCommand(190),
                        //5th spec picked up
                        new ParallelCommandGroup(
                                new SequentialCommandGroup(
                                        new WaitCommand(150),
                                        new LiftWristCommand(izzy.liftWrist, LiftWrist.wristState.FORWARD)
                                ),
                                new LiftCommand(izzy, Lift.liftState.MIDDLE),
                                new SequentialCommandGroup(
                                        new WaitCommand(50),
                                        new FollowPathCommand(izzy.follower, line15)
                                )
                        ),
                        new LiftCommand(izzy, Lift.liftState.UP),
                        new WaitCommand(285),
                        //5th spec scored
                        new ParallelCommandGroup(
                                new FollowPathCommand(izzy.follower, line16),
                                new LiftCommand(izzy, Lift.liftState.DOWN),
                                new LiftClawCommand(izzy.liftClaw, LiftClaw.clawState.OPEN),
                                new LiftWristCommand(izzy.liftWrist, LiftWrist.wristState.BACK)
                        )
                        //parked (at human zone)
                )
        );
    }
    public void schedulePathOnly(){
        CommandScheduler.getInstance().schedule(
                new RunCommand(() -> izzy.follower.update()),
                new SequentialCommandGroup(
                        new FollowPathCommand(izzy.follower, line1),
                        new FollowPathCommand(izzy.follower, line2),
                        new FollowPathCommand(izzy.follower, line3),
                        new FollowPathCommand(izzy.follower, line4),
                        new FollowPathCommand(izzy.follower, line5),
                        new FollowPathCommand(izzy.follower, line6),
                        new FollowPathCommand(izzy.follower, line7),
                        new FollowPathCommand(izzy.follower, line8),
                        new FollowPathCommand(izzy.follower, line9),
                        new FollowPathCommand(izzy.follower, line10),
                        new FollowPathCommand(izzy.follower, line11),
                        new FollowPathCommand(izzy.follower, line12),
                        new FollowPathCommand(izzy.follower, line13),
                        new FollowPathCommand(izzy.follower, line14),
                        new FollowPathCommand(izzy.follower, line15),
                        new FollowPathCommand(izzy.follower, line16)
                )
        );
    }
    public void runOpMode() {
        TelemetryUtil.setup(telemetry);
        timer = new ElapsedTime();
        timer.reset();
        CommandScheduler.getInstance().reset();
        Constants.setConstants(FConstants.class, LConstants.class);
        izzy = new Izzy(hardwareMap, startPose);
        buildPaths();
        scheduleAuto();
        waitForStart();
        while(!isStopRequested() && opModeIsActive()){
            izzy.loop();
        }
        CommandScheduler.getInstance().reset();
    }
}
