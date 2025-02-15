// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class ElevateToPosition extends Command {
  ElevatorSubsystem m_ElevatorSubsystem = null;
  double m_Power = 0.0;
  double m_Position = 0.0;

  /** Creates a new ElevateToPosition. */
  public ElevateToPosition(ElevatorSubsystem elevator, double position) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_ElevatorSubsystem = elevator;
    m_Position = position;
    addRequirements(m_ElevatorSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_ElevatorSubsystem.movePivotWithPID(m_ElevatorSubsystem.getElevatorEncPos(), m_Position);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_ElevatorSubsystem.moveElevator(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  } 
}