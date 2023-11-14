package com.cpr3663.p2024;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends TimedRobot {
    private SwerveModule backLeftModule =
            new SwerveModule(new TalonFX(7, "3663"));

    @Override
    public void teleopPeriodic() {
        backLeftModule.setSteerAngle(0.0);
    }






    double lastUpdateTime = 0.0;

    @Override
    public void robotPeriodic() {
        double now = Timer.getFPGATimestamp();

        if (now - lastUpdateTime > 1.0) {
            System.out.printf("%.3f: %f%n", now,
                    Units.radiansToDegrees(backLeftModule.getSteerAngle())
            );

            lastUpdateTime = now;
        }
    }
}
