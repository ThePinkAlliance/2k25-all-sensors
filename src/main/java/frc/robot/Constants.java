// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kTowerControllerPort = 1;
  }
  public static class AlgaeIntakeConstants {
    public static final double ALGAE_PIVOT_PID_TOLERANCE = 5.0;
    public static final double ALGAE_PIVOT_PID_CLAMP_MAX = 0.20;
    public static final double ALGAE_PIVOT_PID_CLAMP_MIN = -0.20;
    public static final double STARTING_POSITION = 0;
    public static final double STOW_POSITION = 100;
    public static final double EXTENDED_POSITION = 550; 
  }

  public static class ElevatorConstants {
    public static final double ELEVATOR_PIVOT_PID_TOLERANCE = 5.0;
    public static final double ELEVATOR_PIVOT_PID_CLAMP_MAX = 0.30;
    public static final double ELEVATOR_PIVOT_PID_CLAMP_MIN = -0.30;
    public static final double STARTING_POSITION = 0;
    public static final double LEVEL1_POSITION = 1600;
    public static final double LEVEL2_POSITION = 3500;
    public static final double LEVEL3_POSITION = 7000;
    public static final double LEVEL4_POSITION = 7000; //Update when robot can reach these heights
    public static final double LIMIT_TOP_POSITION = 11000;
  }

  public static class CoralConstants {
    public static final double CORAL_ALGAE_EJECT_SPEED = 0.50;
    public static final double CORAL_EJECT_TRIGGER_MINIMUM = 0.20;
    public static final double CORAL_EJECT_SPEED = 0.60;
  }
}
