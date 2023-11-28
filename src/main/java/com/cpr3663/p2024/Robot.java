package com.cpr3663.p2024;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;
import org.littletonrobotics.junction.wpilog.WPILOGWriter;

public class Robot extends LoggedRobot {

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private RobotContainer container;

    @Override
    public void robotInit() {
        // Initialize AdvantageKit
        // Always publish data to NetworkTables
        Logger.getInstance().addDataReceiver(new NT4Publisher());
        Logger.getInstance().disableDeterministicTimestamps();
        if (isReal()) {
            // When on robot, log to a USB stick
            Logger.getInstance().addDataReceiver(new WPILOGWriter("/media/sda1/"));
        }
        // Start AdvantageKit
        Logger.getInstance().start();

        container = new RobotContainer();
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }
}