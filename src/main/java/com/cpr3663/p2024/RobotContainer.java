package com.cpr3663.p2024;

import com.cpr3663.p2024.subsystems.drivetrain.Drivetrain;
import com.cpr3663.p2024.subsystems.drivetrain.SwerveModule;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.util.Units;

import static com.cpr3663.p2024.Constants.*;

public class RobotContainer {
    private final Drivetrain drivetrain;

    public RobotContainer() {
        drivetrain = new Drivetrain(
                new SwerveModule(new TalonFX(DRIVETRAIN_FRONT_LEFT_STEER_MOTOR_ID, DRIVETRAIN_CANBUS_NAME),
                        new CANcoder(DRIVETRAIN_FRONT_LEFT_STEER_ENCODER_ID, DRIVETRAIN_CANBUS_NAME),
                        DRIVETRAIN_FRONT_LEFT_ENCODER_OFFSET),
                new SwerveModule(new TalonFX(DRIVETRAIN_FRONT_RIGHT_STEER_MOTOR_ID, DRIVETRAIN_CANBUS_NAME),
                        new CANcoder(DRIVETRAIN_FRONT_RIGHT_STEER_ENCODER_ID, DRIVETRAIN_CANBUS_NAME),
                        DRIVETRAIN_FRONT_RIGHT_ENCODER_OFFSET),
                new SwerveModule(new TalonFX(DRIVETRAIN_BACK_LEFT_STEER_MOTOR_ID, DRIVETRAIN_CANBUS_NAME),
                        new CANcoder(DRIVETRAIN_BACK_LEFT_STEER_ENCODER_ID, DRIVETRAIN_CANBUS_NAME),
                        DRIVETRAIN_BACK_LEFT_ENCODER_OFFSET),
                new SwerveModule(new TalonFX(DRIVETRAIN_BACK_RIGHT_STEER_MOTOR_ID, DRIVETRAIN_CANBUS_NAME),
                        new CANcoder(DRIVETRAIN_BACK_RIGHT_STEER_ENCODER_ID, DRIVETRAIN_CANBUS_NAME),
                        DRIVETRAIN_BACK_RIGHT_ENCODER_OFFSET));
    }
}
