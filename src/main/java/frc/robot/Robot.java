package frc.robot;

import org.swampscottcurrents.serpentframework.*;

import edu.wpi.first.wpilibj.*;

public class Robot extends FastRobot {
    
    private double heldAngle;
    private boolean isInTurnMode = false;

    public void robotStart() {
        System.out.println("boib");
        RobotMap.gyroscope.reset();
    }

    public void teleopStart() {
        System.out.println("sta");
        heldAngle = RobotMap.gyroscope.getAngle();
    }

    public void teleopUpdate() {
        if(isInTurnMode) {
            //RobotMap.driveController.driveCartesian(RobotMap.MainJoystick.getY(), 0, RobotMap.MainJoystick.getX());
        }
        else {
            if(RobotMap.MainJoystick.getRawButton(6)) {
                RobotMap.driveController.driveCartesian(RobotMap.MainJoystick.getX(), RobotMap.MainJoystick.getY(), 0.4, RobotMap.gyroscope.getAngle());
                heldAngle = RobotMap.gyroscope.getAngle();
            } else if(RobotMap.MainJoystick.getRawButton(7)) {
                RobotMap.driveController.driveCartesian(RobotMap.MainJoystick.getX(), RobotMap.MainJoystick.getY(), -0.4, RobotMap.gyroscope.getAngle());
                heldAngle = RobotMap.gyroscope.getAngle();
            }
            else {
                double error = 1.75 * (heldAngle - RobotMap.gyroscope.getAngle()) / 180;
                RobotMap.driveController.driveCartesian(RobotMap.MainJoystick.getX(), RobotMap.MainJoystick.getY(), clamp(12 * Math.signum(error) * (error * error), -0.3, 0.3), RobotMap.gyroscope.getAngle());
            }
        }
        if(RobotMap.MainJoystick.getRawButtonPressed(1)) {
            isInTurnMode = !isInTurnMode;
            heldAngle = RobotMap.gyroscope.getAngle();
        }
        if(RobotMap.MainJoystick.getRawButton(2)) {
            double error = (heldAngle - RobotMap.gyroscope.getAngle()) / 180;
            System.out.println("held " + String.valueOf(clamp(12 * Math.signum(error) * (error * error), -0.3, 0.3)) + " " + String.valueOf(RobotMap.gyroscope.getAngle()));
        }
    }

    private double clamp(double val, double min, double max) {
        if(val > min) {
            if(val < max) {
                return val;
            }
            return max;
        }
        return min;
    }
}
