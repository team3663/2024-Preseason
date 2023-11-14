package com.cpr3663.p2024;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class Robot extends TimedRobot {
    private SwerveModule backLeftModule =
            new SwerveModule(new TalonFX(7, "3663"),
                    new CANcoder(11, "3663"),
                    Units.degreesToRadians(-202.0));

    private double targetAngle = 0.0;

    @Override
    public void robotInit() {
        var tab = Shuffleboard.getTab("TEST");

        tab.addDouble("EncoderAngle", () ->
                backLeftModule.getAbsoluteAngle());
        tab.addDouble("MotorAngle", () -> backLeftModule.getSteerAngle());
        tab.addDouble("TargetAngle", () -> targetAngle);
    }

    @Override
    public void teleopPeriodic() {
//        targetAngle = Units.degreesToRadians(90.0) * Math.sin(Timer.getFPGATimestamp());
        targetAngle = 0;

        backLeftModule.setSteerAngle(targetAngle);
    }
}
