// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralShooterSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class ScoreCoral extends Command {
  CoralShooterSubsystem m_coralShooterSubsystem;
  double m_speed;
  /** Creates a new scoreCoral. */
  public ScoreCoral(CoralShooterSubsystem coralShooterSubsystem, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_speed = speed;
    
    m_coralShooterSubsystem = coralShooterSubsystem;
    addRequirements(m_coralShooterSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_coralShooterSubsystem.setCoralInputSpeed(m_speed);
    Logger.recordOutput("AllSensors/Coral/CoralEject Speed", m_speed);
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_coralShooterSubsystem.setCoralInputSpeed(0);
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
