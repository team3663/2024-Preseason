package com.cpr3663.p2024.subsystems.drivetrain;

import edu.wpi.first.math.kinematics.SwerveModuleState;
import org.littletonrobotics.junction.LogTable;
import org.littletonrobotics.junction.inputs.LoggableInputs;

public interface SwerveModuleIO {

    void updateInputs(Inputs inputs);

    void setTargetState(SwerveModuleState state);

    class Inputs implements LoggableInputs {
        public double steerAngleRad;
        public double encoderAngleRad;

        @Override
        public void toLog(LogTable table) {
            table.put("SteerMotorAngle", steerAngleRad);
            table.put("SteerEncoderAngle", encoderAngleRad);
        }

        @Override
        public void fromLog(LogTable table) {
            steerAngleRad = table.getDouble("SteerMotorAngle", 0.0);
            encoderAngleRad = table.getDouble("SteerEncoderAngle", 0.0);
        }
    }
}
