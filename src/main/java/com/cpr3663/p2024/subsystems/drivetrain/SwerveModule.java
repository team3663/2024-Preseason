package com.cpr3663.p2024.subsystems.drivetrain;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;

public class SwerveModule implements SwerveModuleIO {
    private TalonFX driveMotor;
    private TalonFX steerMotor;
    private CANcoder steerEncoder;

    public SwerveModule(TalonFX steerMotor, CANcoder steerEncoder, double steerAngleOffset) {
        this.steerMotor = steerMotor;
        this.steerEncoder = steerEncoder;

        CANcoderConfiguration encoderConfig = new CANcoderConfiguration();
        encoderConfig.MagnetSensor.MagnetOffset = Units.radiansToRotations(steerAngleOffset);

        steerEncoder.getConfigurator().apply(encoderConfig);

        TalonFXConfiguration steerConfig = new TalonFXConfiguration();
        steerConfig.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.FusedCANcoder;
        steerConfig.Feedback.FeedbackRemoteSensorID = steerEncoder.getDeviceID();

        steerConfig.Feedback.SensorToMechanismRatio = 1.0; // 1:1
        steerConfig.Feedback.RotorToSensorRatio = 12.8;

        steerConfig.Slot0.kP = 100;
        steerConfig.Slot0.kD = 1;

        steerMotor.getConfigurator().apply(steerConfig);
    }

    @Override
    public void updateInputs(Inputs inputs) {
        StatusSignal<Double> steerAngleSignal = steerMotor.getPosition();

        steerAngleSignal.refresh();

        StatusSignal<Double> encoderAngleSignal = steerEncoder.getPosition();

        encoderAngleSignal.refresh();

        inputs.steerAngleRad = Units.rotationsToRadians(steerAngleSignal.getValue());
        inputs.encoderAngleRad = Units.rotationsToRadians(encoderAngleSignal.getValue());
    }

    @Override
    public void setTargetState(SwerveModuleState state) {
        steerMotor.setControl(new PositionVoltage(state.angle.getRotations()));
    }

    public double getSteerAngle() {
        StatusSignal<Double> signal = steerMotor.getPosition();

        signal.refresh();

        return Units.rotationsToRadians(signal.getValue());
    }

    public double getAbsoluteAngle() {
        StatusSignal<Double> signal = steerEncoder.getPosition();

        signal.refresh();

        return Units.rotationsToRadians(signal.getValue());
    }
}