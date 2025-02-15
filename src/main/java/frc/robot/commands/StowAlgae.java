// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.AlgaeIntake;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class StowAlgae extends Command {
  /** Creates a new DetectAlgae. */
  private final AlgaeIntake m_algaeIntake;  

  public StowAlgae(AlgaeIntake subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_algaeIntake = subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_algaeIntake.spinWheels(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {    
    m_algaeIntake.movePivotWithPID(m_algaeIntake.getPivotEncoderPos(), Constants.AlgaeIntakeConstants.STOW_POSITION);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
