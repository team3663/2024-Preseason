package com.cpr3663.p2024;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class Robot extends TimedRobot {
    private SwerveModule frontLeftModule =
            new SwerveModule(new TalonFX(5, "3663"),
                    new CANcoder(9, "3663"),
                    Units.degreesToRadians(116.63));

    private SwerveModule frontRightModule =
            new SwerveModule(new TalonFX(6, "3663"),
                    new CANcoder(10, "3663"),
                    Units.degreesToRadians(-86.04));
    private SwerveModule backLeftModule =
            new SwerveModule(new TalonFX(7, "3663"),
                    new CANcoder(11, "3663"),
                    Units.degreesToRadians(-202.0));

    private SwerveModule backRightModule =
            new SwerveModule(new TalonFX(4, "3663"),
                new CANcoder(12, "3663"),
                Units.degreesToRadians(-279.67));

    private double targetAngle = 0.0;

    @Override
    public void robotInit() {
        var tab = Shuffleboard.getTab("TEST");
        tab.addDouble("TargetAngle", () -> targetAngle);

        tab.addDouble("FrontLeftEncoderAngle", () ->
                frontLeftModule.getAbsoluteAngle());
        tab.addDouble("FrontLeftMotorAngle", () -> frontLeftModule.getSteerAngle());

        tab.addDouble("FrontRightEncoderAngle", () ->
                frontRightModule.getAbsoluteAngle());
        tab.addDouble("FrontRightMotorAngle", () -> frontRightModule.getSteerAngle());

        tab.addDouble("BackLeftEncoderAngle", () ->
                backLeftModule.getAbsoluteAngle());
        tab.addDouble("BackLeftMotorAngle", () -> backLeftModule.getSteerAngle());

        tab.addDouble("BackRightEncoderAngle", () ->
                backRightModule.getAbsoluteAngle());
        tab.addDouble("BackRightMotorAngle", () -> backRightModule.getSteerAngle());
    }

    @Override
    public void teleopPeriodic() {
//        targetAngle = Units.degreesToRadians(90.0) * Math.sin(Timer.getFPGATimestamp());
        targetAngle = 0;

        frontLeftModule.setSteerAngle(targetAngle);
        frontRightModule.setSteerAngle(targetAngle);
        backLeftModule.setSteerAngle(targetAngle);
        backRightModule.setSteerAngle(targetAngle);
    }
}