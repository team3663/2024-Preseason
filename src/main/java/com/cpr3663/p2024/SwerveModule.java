package com.cpr3663.p2024;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;

public class SwerveModule {
    private TalonFX driveMotor;
    private TalonFX steerMotor;
    private CANcoder steerEncoder;

    public SwerveModule(TalonFX steerMotor) {
        this.steerMotor = steerMotor;

        TalonFXConfiguration steerConfig = new TalonFXConfiguration();
        steerConfig.Feedback.SensorToMechanismRatio = 12.8 / (2.0 * Math.PI); // 12.8:1

        steerConfig.Slot0.kP = 2;

        steerConfig.Voltage.PeakForwardVoltage = 6.0;
        steerConfig.Voltage.PeakReverseVoltage = 6.0;

        steerMotor.getConfigurator().apply(steerConfig);
    }

    public void setSteerVoltage(double volts) {
        steerMotor.setControl(new VoltageOut(volts));
    }

    public void setSteerAngle(double angle) {
        steerMotor.setControl(new PositionVoltage(angle));
    }

    public double getSteerAngle() {
        StatusSignal<Double> signal = steerMotor.getPosition();

        signal.refresh();

        return signal.getValue();
    }
}
