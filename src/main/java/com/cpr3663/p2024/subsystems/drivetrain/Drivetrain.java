package com.cpr3663.p2024.subsystems.drivetrain;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

public class Drivetrain extends SubsystemBase {
    private static final String[] MODULE_NAMES = {
            "Front Left", "Front Right", "Back Left", "Back Right"
    };

    private final SwerveModuleIO[] modules;

    private final SwerveModuleIO.Inputs[] moduleInputs;

    private double targetAngle = 0.0;

    public Drivetrain(
            SwerveModuleIO frontLeftModule,
            SwerveModuleIO frontRightModule,
            SwerveModuleIO backLeftModule,
            SwerveModuleIO backRightModule
    ) {
        this.modules = new SwerveModuleIO[]{frontLeftModule, frontRightModule, backLeftModule, backRightModule};
        this.moduleInputs = new SwerveModuleIO.Inputs[modules.length];
        for (int i = 0; i < modules.length; ++i)
            moduleInputs[i] = new SwerveModuleIO.Inputs();
    }

    @Override
    public void periodic() {
        for (int i = 0; i < modules.length; ++i) {
            modules[i].updateInputs(moduleInputs[i]);

            Logger.getInstance()
                    .processInputs("Drivetrain/" + MODULE_NAMES[i], moduleInputs[i]);
        }

        for (SwerveModuleIO module : modules)
            module.setTargetState(new SwerveModuleState(0.0, Rotation2d.fromRadians(targetAngle)));
    }
}
