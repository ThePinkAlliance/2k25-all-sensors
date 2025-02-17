// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import org.littletonrobotics.junction.Logger;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.AlgaeIntake;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class CollectAlgae extends Command {
  /** Creates a new DetectAlgae. */
  private final AlgaeIntake m_algaeIntake;
  private double m_speed;
  private boolean m_detected = false;
  

  public CollectAlgae(AlgaeIntake subsystem, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    
    m_speed = speed;
    //Amount of time wheels should spin for once Algae is detected
    m_algaeIntake = subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_algaeIntake.spinWheels(m_speed);
    m_detected = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {   
    Logger.recordOutput("AlgaeInkate/AlgaeIntake Get Algae", m_algaeIntake.getAlgaeDetected()); 
    m_algaeIntake.movePivotWithPID(m_algaeIntake.getPivotEncoderPos(), Constants.AlgaeIntakeConstants.EXTENDED_POSITION);
    
    if (m_algaeIntake.getAlgaeDetected()) {
      m_detected = true;
    }

    if (m_detected == true) {
      m_algaeIntake.spinWheels(0);
      m_algaeIntake.movePivotWithPID(m_algaeIntake.getPivotEncoderPos(), Constants.AlgaeIntakeConstants.STOW_POSITION);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_algaeIntake.spinWheels(0);
    m_algaeIntake.movePivot(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
