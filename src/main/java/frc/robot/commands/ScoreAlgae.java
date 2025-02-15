// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AlgaeIntake;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class ScoreAlgae extends Command {
  private final AlgaeIntake m_subsytem;
  private double m_speed;

  /** Creates a new ScoreAlgae. */
  public ScoreAlgae(AlgaeIntake subsystem, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_subsytem = subsystem;
    m_speed = speed;

    addRequirements(m_subsytem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_subsytem.spinWheels(m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsytem.spinWheels(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
