package sigmaCode.currentStuff;

import static sigmaCode.currentStuff.freakySubsystems.Diffy.diffyState.START;
import static sigmaCode.currentStuff.freakySubsystems.HorizontalArm.armState.RETRACTED;
import static sigmaCode.currentStuff.freakySubsystems.HorizontalArm.armState.UP;
import static sigmaCode.currentStuff.freakySubsystems.VerticalArm.armState.SPECSCORE;
import static sigmaCode.currentStuff.freakySubsystems.VerticalArm.armState.SPECWALL;
import static sigmaCode.currentStuff.freakySubsystems.VerticalClaw.clawState.CLOSE;
import static sigmaCode.currentStuff.freakySubsystems.VerticalClaw.clawState.OPEN;
import static sigmaCode.currentStuff.freakySubsystems.VerticalSlides.liftState.DOWN;
import static sigmaCode.currentStuff.freakySubsystems.VerticalSlides.liftState.SPECUP;
import static sigmaCode.currentStuff.freakySubsystems.VerticalWrist.wristState.NORMAL;
import static sigmaCode.currentStuff.freakySubsystems.VerticalWrist.wristState.SPECPLACE;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.RunCommand;
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
import com.arcrobotics.ftclib.command.WaitCommand;
import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;
import sigmaCode.currentStuff.feinCommands.DiffyCommand;
import sigmaCode.currentStuff.feinCommands.FollowPathCommand;
import sigmaCode.currentStuff.feinCommands.HorizontalArmCommand;
import sigmaCode.currentStuff.feinCommands.VerticalArmCommand;
import sigmaCode.currentStuff.feinCommands.VerticalClawCommand;
import sigmaCode.currentStuff.feinCommands.VerticalSlidesCommand;
import sigmaCode.currentStuff.feinCommands.VerticalWristCommand;
import sigmaCode.currentStuff.freakySubsystems.IzzyV2;
import sigmaCode.currentStuff.freakySubsystems.TelemetryUtil;
import sigmaCode.currentStuff.feinCommands.ActivateLiftCommand;
import sigmaCode.currentStuff.freakySubsystems.VerticalArm;
import sigmaCode.currentStuff.freakySubsystems.VerticalClaw;
import sigmaCode.currentStuff.freakySubsystems.VerticalSlides;
import sigmaCode.currentStuff.freakySubsystems.VerticalWrist;

@Autonomous(name = "idk")
public class Auton extends LinearOpMode {

    private ElapsedTime timer;
    private final Pose startPose = new Pose(7.2, 63, Math.toRadians(0));
    IzzyV2 izzy;
    private PathChain line1, line2, line3, line4, line5, line6, line7, line8, line9, line10, line11, line12, line13, line14, line15, line16;
    public void buildPaths() {
        line1 = izzy.follower.pathBuilder()
                .addPath(
                        // Line 1
                        new BezierLine(
                                new Point(7.200, 65.000, Point.CARTESIAN),
                                new Point(42.00, 65.000, Point.CARTESIAN)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .setZeroPowerAccelerationMultiplier(6)
                .build();
        line2 = izzy.follower.pathBuilder()
                .addPath(
                        // Line 2
                        new BezierCurve(
                                new Point(42.00, 65.000, Point.CARTESIAN),
                                new Point(-5, 36, Point.CARTESIAN),
                                new Point(65.0322, 38.3225, Point.CARTESIAN),
                                new Point(80.1161, 15.529, Point.CARTESIAN),
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
                                new Point(8, 33.000, Point.CARTESIAN)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .setPathEndVelocityConstraint(1)
                .setZeroPowerAccelerationMultiplier(3.3)
                .build();
        line9 = izzy.follower.pathBuilder()
                .addPath(
                        // Line 9
                        new BezierCurve(
                                new Point(8, 33.000, Point.CARTESIAN),
                                new Point(13.801, 66.000, Point.CARTESIAN),
                                new Point(42, 67.000, Point.CARTESIAN)
                        )
                )
                .setZeroPowerAccelerationMultiplier(4)
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        line10 = izzy.follower.pathBuilder()
                .addPath(
                        // Line 10
                        new BezierLine(
                                new Point(42, 70.000, Point.CARTESIAN),
                                new Point(8, 33.000, Point.CARTESIAN)
                        )
                )
                .setPathEndVelocityConstraint(1)
                .setZeroPowerAccelerationMultiplier(3.3)
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        line11 = izzy.follower.pathBuilder()
                .addPath(
                        // Line 11
                        new BezierCurve(
                                new Point(8, 33.000, Point.CARTESIAN),
                                new Point(13.801, 66.000, Point.CARTESIAN),
                                new Point(42, 68.500, Point.CARTESIAN)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        line12 = izzy.follower.pathBuilder()
                .addPath(
                        // Line 12
                        new BezierLine(
                                new Point(42, 68.500, Point.CARTESIAN),
                                new Point(8, 33.000, Point.CARTESIAN)
                        )
                )
                .setPathEndVelocityConstraint(1)
                .setZeroPowerAccelerationMultiplier(3.3)
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        line13 = izzy.follower.pathBuilder()
                .addPath(
                        // Line 13
                        new BezierCurve(
                                new Point(8, 33.000, Point.CARTESIAN),
                                new Point(13.801, 66.000, Point.CARTESIAN),
                                new Point(42, 67.000, Point.CARTESIAN)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        line14 = izzy.follower.pathBuilder()
                .addPath(
                        // Line 14
                        new BezierLine(
                                new Point(42, 67.000, Point.CARTESIAN),
                                new Point(8, 33.000, Point.CARTESIAN)
                        )
                )
                .setPathEndVelocityConstraint(1)
                .setZeroPowerAccelerationMultiplier(3.3)
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        line15 = izzy.follower.pathBuilder()
                .addPath(
                        // Line 15
                        new BezierCurve(
                                new Point(8, 33.000, Point.CARTESIAN),
                                new Point(13.801, 66.000, Point.CARTESIAN),
                                new Point(42, 69.00, Point.CARTESIAN)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        line16 = izzy.follower.pathBuilder()
                .addPath(
                        // Line 16
                        new BezierLine(
                                new Point(42, 69.000, Point.CARTESIAN),
                                new Point(9.000, 33.000, Point.CARTESIAN)
                        )
                )
                .setPathEndVelocityConstraint(1)
                .setZeroPowerAccelerationMultiplier(3.3)
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
    }

    public void scheduleAuto(){
        CommandScheduler.getInstance().schedule(
                new RunCommand(() -> izzy.follower.update()),
                new SequentialCommandGroup(
                        new ActivateLiftCommand(izzy),
                        new ParallelCommandGroup(
                                new VerticalSlidesCommand(izzy, SPECUP),
                                new FollowPathCommand(izzy.follower, line1)
                        ),
                        new WaitCommand(350),
                        //1st spec scored
                        new VerticalClawCommand(izzy.verticalClaw, OPEN),
                        new ParallelCommandGroup(
                                new SequentialCommandGroup(
                                        new WaitCommand(150),
                                        new VerticalSlidesCommand(izzy, DOWN),
                                        new ActivateLiftCommand(izzy)
                                ),
                                new VerticalWristCommand(izzy.verticalWrist, NORMAL),
                                new VerticalArmCommand(izzy.vertArm, SPECWALL),
                                new SequentialCommandGroup(
                                        new WaitCommand(250),
                                        new FollowPathCommand(izzy.follower, line2),
                                        new FollowPathCommand(izzy.follower, line3),
                                        new FollowPathCommand(izzy.follower, line4),
                                        new FollowPathCommand(izzy.follower, line5),
                                        new FollowPathCommand(izzy.follower, line6),
                                        new FollowPathCommand(izzy.follower, line7)
                                )
                        ),
                        new FollowPathCommand(izzy.follower, line8),
                        //at human zone
                        new VerticalClawCommand(izzy.verticalClaw, CLOSE),
                        new WaitCommand(290),
                        new ActivateLiftCommand(izzy),
                        //2nd spec picked up
                        new ParallelCommandGroup(
                                new SequentialCommandGroup(
                                        new WaitCommand(150),
                                        new VerticalArmCommand(izzy.vertArm, SPECSCORE),
                                        new VerticalWristCommand(izzy.verticalWrist, SPECPLACE)
                                ),
                                new SequentialCommandGroup(
                                        new WaitCommand(50),
                                        new ParallelCommandGroup(
                                                new VerticalSlidesCommand(izzy, SPECUP),
                                                new FollowPathCommand(izzy.follower, line9)
                                        )
                                )
                        ),
                        new WaitCommand(350),
                        //2nd spec scored
                        new VerticalClawCommand(izzy.verticalClaw, OPEN),
                        new ParallelCommandGroup(
                                new SequentialCommandGroup(
                                        new WaitCommand(150),
                                        new FollowPathCommand(izzy.follower, line10)
                                ),
                                new SequentialCommandGroup(
                                        new WaitCommand(150),
                                        new VerticalSlidesCommand(izzy, DOWN)
                                ),
                                new VerticalWristCommand(izzy.verticalWrist, NORMAL),
                                new VerticalArmCommand(izzy.vertArm, SPECWALL)
                        ),
                        //at human zone
                        new VerticalClawCommand(izzy.verticalClaw, CLOSE),
                        new WaitCommand(290),
                        //3rd spec picked up
                        new ParallelCommandGroup(
                                new SequentialCommandGroup(
                                        new WaitCommand(150),
                                        new VerticalArmCommand(izzy.vertArm, SPECSCORE),
                                        new VerticalWristCommand(izzy.verticalWrist, SPECPLACE)
                                ),
                                new SequentialCommandGroup(
                                        new WaitCommand(50),
                                        new ParallelCommandGroup(
                                                new VerticalSlidesCommand(izzy, SPECUP),
                                                new FollowPathCommand(izzy.follower, line11)
                                        )
                                )
                        ),
                        new WaitCommand(350),
                        //3rd spec scored
                        new VerticalClawCommand(izzy.verticalClaw, OPEN),
                        new ParallelCommandGroup(
                                new SequentialCommandGroup(
                                        new WaitCommand(150),
                                        new FollowPathCommand(izzy.follower, line12)
                                ),
                                new SequentialCommandGroup(
                                        new WaitCommand(150),
                                        new VerticalSlidesCommand(izzy, DOWN)
                                ),
                                new VerticalWristCommand(izzy.verticalWrist, NORMAL),
                                new VerticalArmCommand(izzy.vertArm, SPECWALL)
                        ),
                        //at human zone
                        new VerticalClawCommand(izzy.verticalClaw, CLOSE),
                        new WaitCommand(290),
                        //4th spec picked up
                        new ParallelCommandGroup(
                                new SequentialCommandGroup(
                                        new WaitCommand(150),
                                        new VerticalArmCommand(izzy.vertArm, SPECSCORE),
                                        new VerticalWristCommand(izzy.verticalWrist, SPECPLACE)
                                ),
                                new SequentialCommandGroup(
                                        new WaitCommand(50),
                                        new ParallelCommandGroup(
                                                new VerticalSlidesCommand(izzy, SPECUP),
                                                new FollowPathCommand(izzy.follower, line13)
                                        )
                                )
                        ),
                        new WaitCommand(350),
                        //4th spec scored
                        new VerticalClawCommand(izzy.verticalClaw, OPEN),
                        new ParallelCommandGroup(
                                new SequentialCommandGroup(
                                        new WaitCommand(150),
                                        new FollowPathCommand(izzy.follower, line14)
                                ),
                                new SequentialCommandGroup(
                                        new WaitCommand(150),
                                        new VerticalSlidesCommand(izzy, DOWN)
                                ),
                                new VerticalWristCommand(izzy.verticalWrist, NORMAL),
                                new VerticalArmCommand(izzy.vertArm, SPECWALL)
                        ),
                        //at human zone
                        new VerticalClawCommand(izzy.verticalClaw, CLOSE),
                        new WaitCommand(290),
                        //5th spec picked up
                        new ParallelCommandGroup(
                                new SequentialCommandGroup(
                                        new WaitCommand(150),
                                        new VerticalArmCommand(izzy.vertArm, SPECSCORE),
                                        new VerticalWristCommand(izzy.verticalWrist, SPECPLACE)
                                ),
                                new SequentialCommandGroup(
                                        new WaitCommand(50),
                                        new ParallelCommandGroup(
                                                new VerticalSlidesCommand(izzy, SPECUP),
                                                new FollowPathCommand(izzy.follower, line15)
                                        )
                                )
                        ),
                        new WaitCommand(350),
                        //5th spec scored
                        new VerticalClawCommand(izzy.verticalClaw, OPEN),
                        new ParallelCommandGroup(
                                new SequentialCommandGroup(
                                        new WaitCommand(150),
                                        new FollowPathCommand(izzy.follower, line16)
                                ),
                                new SequentialCommandGroup(
                                        new WaitCommand(150),
                                        new VerticalSlidesCommand(izzy, DOWN)
                                ),
                                new VerticalWristCommand(izzy.verticalWrist, NORMAL),
                                new VerticalArmCommand(izzy.vertArm, SPECWALL)
                        )
                )
        );
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
                                new VerticalClawCommand(izzy.verticalClaw, CLOSE)
                        )
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
