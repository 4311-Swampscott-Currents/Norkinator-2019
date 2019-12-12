package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public final class RobotMap {

    public static Joystick MainJoystick = new Joystick(0);

    public static ADXRS450_Gyro gyroscope = new ADXRS450_Gyro();

    public static Spark frontLeftFrontMotor = new Spark(0);
    public static Spark frontLeftBackMotor = new Spark(1);
    public static Spark backLeftFrontMotor = new Spark(2);
    public static Spark backLeftBackMotor = new Spark(3);
    public static VictorSP frontRightFrontMotor = new VictorSP(4);
    public static VictorSP frontRightBackMotor = new VictorSP(5);
    public static VictorSP backRightFrontMotor = new VictorSP(6);
    public static VictorSP backRightBackMotor = new VictorSP(7);

    public static SpeedControllerGroup frontLeftMotors = new SpeedControllerGroup(frontLeftFrontMotor, frontLeftBackMotor);
    public static SpeedControllerGroup backLeftMotors = new SpeedControllerGroup(backLeftFrontMotor, backLeftBackMotor);
    public static SpeedControllerGroup frontRightMotors = new SpeedControllerGroup(frontRightFrontMotor, frontRightBackMotor);
    public static SpeedControllerGroup backRightMotors = new SpeedControllerGroup(backRightFrontMotor, backRightBackMotor);

    public static MecanumDrive driveController = new MecanumDrive(frontLeftMotors, backLeftMotors, frontRightMotors, backRightMotors);
}