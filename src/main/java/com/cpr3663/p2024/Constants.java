package com.cpr3663.p2024;

import static edu.wpi.first.math.util.Units.degreesToRadians;

public final class Constants {
    public static final String DRIVETRAIN_CANBUS_NAME = "3663";

    public static final int DRIVETRAIN_FRONT_LEFT_DRIVE_MOTOR_ID = 1;
    public static final int DRIVETRAIN_FRONT_LEFT_STEER_MOTOR_ID = 5;
    public static final int DRIVETRAIN_FRONT_LEFT_STEER_ENCODER_ID = 9;
    public static final int DRIVETRAIN_FRONT_RIGHT_DRIVE_MOTOR_ID = 2;
    public static final int DRIVETRAIN_FRONT_RIGHT_STEER_MOTOR_ID = 6;
    public static final int DRIVETRAIN_FRONT_RIGHT_STEER_ENCODER_ID = 10;
    public static final int DRIVETRAIN_BACK_LEFT_DRIVE_MOTOR_ID = 3;
    public static final int DRIVETRAIN_BACK_LEFT_STEER_MOTOR_ID = 7;
    public static final int DRIVETRAIN_BACK_LEFT_STEER_ENCODER_ID = 11;
    public static final int DRIVETRAIN_BACK_RIGHT_DRIVE_MOTOR_ID = 3;
    public static final int DRIVETRAIN_BACK_RIGHT_STEER_MOTOR_ID = 8;
    public static final int DRIVETRAIN_BACK_RIGHT_STEER_ENCODER_ID = 12;


    public static final double DRIVETRAIN_FRONT_LEFT_ENCODER_OFFSET = degreesToRadians(116.63);
    public static final double DRIVETRAIN_FRONT_RIGHT_ENCODER_OFFSET = degreesToRadians(-86.04);
    public static final double DRIVETRAIN_BACK_LEFT_ENCODER_OFFSET = degreesToRadians(-202.0);
    public static final double DRIVETRAIN_BACK_RIGHT_ENCODER_OFFSET = degreesToRadians(-279.67);

}
