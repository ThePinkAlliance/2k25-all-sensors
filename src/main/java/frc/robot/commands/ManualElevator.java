// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class ManualElevator extends Command {

  ElevatorSubsystem m_Elevator = null;
  DoubleSupplier m_Power;

  /** Creates a new ManualElevator. */
  public ManualElevator(ElevatorSubsystem elevatorSubsystem, DoubleSupplier power) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Power = power;
    m_Elevator = elevatorSubsystem;
    addRequirements(m_Elevator);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double power = m_Power.getAsDouble();
    Logger.recordOutput("AllSensors/Elevator/ManualElevatorPower", power);
    m_Elevator.moveElevator(m_Power.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Elevator.moveElevator(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
