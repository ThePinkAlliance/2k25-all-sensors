// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.CollectAlgae;
import frc.robot.commands.ElevateToPosition;
import frc.robot.commands.IntakeCoral;
import frc.robot.commands.ManualAlgae;
import frc.robot.commands.ManualElevator;
import frc.robot.commands.ScoreAlgae;
import frc.robot.commands.ScoreCoral;
import frc.robot.commands.StowAlgae;
import frc.robot.subsystems.AlgaeIntake;
import frc.robot.subsystems.AllSensors;
import frc.robot.subsystems.CoralShooterSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final AllSensors m_AllSensors = new AllSensors();
  private final AlgaeIntake m_AlgaeIntake = new AlgaeIntake();
  private final ElevatorSubsystem m_Elevator = new ElevatorSubsystem();
  private final CoralShooterSubsystem m_Coral = new CoralShooterSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {
   
    //Elevator
    //m_driverController.b().onTrue(m_Elevator.resetEE());
    m_driverController.b().onTrue(new IntakeCoral(m_Coral, 0.40));
    m_driverController.back().whileTrue(new ScoreCoral(m_Coral, 0.60));
    m_driverController.a().whileTrue(new ElevateToPosition(m_Elevator, Constants.ElevatorConstants.LEVEL1_POSITION));
    m_driverController.x().whileTrue(new ElevateToPosition(m_Elevator, Constants.ElevatorConstants.LEVEL2_POSITION));
    m_driverController.y().whileTrue(new ElevateToPosition(m_Elevator, Constants.ElevatorConstants.LEVEL3_POSITION));

    //Algae
    m_driverController.leftBumper().whileTrue(new ScoreAlgae(m_AlgaeIntake, 0.50));
    m_driverController.rightBumper().whileTrue(
      new CollectAlgae(m_AlgaeIntake, -0.15, 5.0));
    
   
    //m_AlgaeIntake.setDefaultCommand(new ManualAlgae(m_AlgaeIntake, 
    //() -> m_driverController.getLeftY()*-1));
    m_AlgaeIntake.setDefaultCommand(new StowAlgae(m_AlgaeIntake));
    m_Elevator.setDefaultCommand(new ManualElevator(m_Elevator, () -> m_driverController.getLeftY()*-1));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return new Command() {
    };
  }
}
