// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ManualElevator;

import frc.robot.subsystems.AlgaeIntake;
import frc.robot.subsystems.CoralShooterSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final AllSensors m_AllSensors = new AllSensors();
  private final AlgaeIntake m_AlgaeIntake = new AlgaeIntake();
  private final ElevatorSubsystem m_Elevator = new ElevatorSubsystem();
  private final CoralShooterSubsystem m_Coral = new CoralShooterSubsystem();
  private final ReefscapeCommandFactory m_CommandFactory = new ReefscapeCommandFactory(m_Elevator, m_Coral, m_AlgaeIntake);

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final CommandXboxController m_towerController =
      new CommandXboxController(OperatorConstants.kTowerControllerPort);

  //Triggers that are not associated with the joystick directly but count on the joystick
  Trigger m_TriggerTowerController_RightTrigger = new Trigger(()-> (m_towerController.getRightTriggerAxis() >= Constants.CoralConstants.CORAL_EJECT_TRIGGER_MINIMUM));

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {
   
    //TOWER TOWER TOWER TOWER TOWER TOWER TOWER TOWER TOWER TOWER TOWER TOWER TOWER TOWER TOWER TOWER
    //TOWER TOWER TOWER TOWER TOWER TOWER TOWER TOWER TOWER TOWER TOWER TOWER TOWER TOWER TOWER TOWER
    //Elevator
    m_towerController.b().onTrue(m_CommandFactory.reefLevelOne());
    m_towerController.a().onTrue(m_CommandFactory.reefLevelTwo());
    m_towerController.x().onTrue(m_CommandFactory.reefLevelThree());
    m_towerController.y().onTrue(m_CommandFactory.reefLevelFour());
    m_Elevator.setDefaultCommand(new ManualElevator(m_Elevator, () -> m_towerController.getLeftY()*-1));
    //Coral
    m_TriggerTowerController_RightTrigger.whileTrue(m_CommandFactory.coralEject(Constants.CoralConstants.CORAL_EJECT_SPEED));
    //Algae
    m_towerController.rightBumper().whileTrue(m_CommandFactory.reefEjectAlgaeLevelTwo(0.60));
    m_towerController.leftBumper().whileTrue(m_CommandFactory.reefEjectAlgaeLevelThree(0.60));
    
    //END TOWER SECTION

    //DRIVER DRIVER DRIVER DRIVER DRIVER DRIVER DRIVER DRIVER DRIVER DRIVER DRIVER DRIVER DRIVER DRIVER
    //DRIVER DRIVER DRIVER DRIVER DRIVER DRIVER DRIVER DRIVER DRIVER DRIVER DRIVER DRIVER DRIVER DRIVER
    //Algae
    m_driverController.leftBumper().whileTrue(m_CommandFactory.algaeScore(0.50));
    m_driverController.rightBumper().whileTrue(m_CommandFactory.algaeCollect(-0.15));
    //Coral
    m_driverController.y().whileTrue(m_CommandFactory.coralIntake(0.40));
    //END DRIVER SECTION

    //DEFAULT SUBSYSTEM COMMANDS THAT DO NOT DEPEND ON ANY JOYSTICK
    m_AlgaeIntake.setDefaultCommand(m_CommandFactory.algaeStow());
    //END DEFAULT SECTION

    //TEST ONLY BINDINGS - REMOVE BEFORE TOURNAMENT
    m_driverController.back().onTrue(m_CommandFactory.algaeResetEncoder());
    m_towerController.back().onTrue(m_CommandFactory.elevatorResetEncoder());

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
