// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AlgaeIntake;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class ManualAlgae extends Command {

  AlgaeIntake m_AlgaeIntake = null;
  DoubleSupplier m_Power;

  /** Creates a new ManualAlgae. */
  public ManualAlgae(AlgaeIntake algaeIntake, DoubleSupplier power) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Power = power;
    m_AlgaeIntake = algaeIntake;
    addRequirements(m_AlgaeIntake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double power = m_Power.getAsDouble();
    Logger.recordOutput("AllSensors/Algae/ManualAlgaePower", power);
    m_AlgaeIntake.movePivot(m_Power.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_AlgaeIntake.movePivot(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
