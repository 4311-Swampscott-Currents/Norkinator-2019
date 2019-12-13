package frc.robot;

import org.swampscottcurrents.serpentframework.*;

import edu.wpi.first.wpilibj.*;

public class Robot extends FastRobot {
    
    private double heldAngle;
    private boolean isInTurnMode = false;

    public void robotStart() {
        RobotMap.gyroscope.reset();
    }

    public void teleopStart() {
        heldAngle = RobotMap.gyroscope.getAngle();
    }

    public void teleopUpdate() {
        if(isInTurnMode) {
            RobotMap.driveController.driveCartesian(RobotMap.MainJoystick.getY(), 0, RobotMap.MainJoystick.getX());
        }
        else {
            double error = (heldAngle - RobotMap.gyroscope.getAngle()) / 180;
            RobotMap.driveController.driveCartesian(RobotMap.MainJoystick.getY(), RobotMap.MainJoystick.getX(), clamp(0.05 * Math.signum(error) * (error * error), -0.3, 0.3), RobotMap.gyroscope.getAngle());
        }
        if(RobotMap.MainJoystick.getRawButtonPressed(1)) {
            isInTurnMode = !isInTurnMode;
            heldAngle = RobotMap.gyroscope.getAngle();
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
