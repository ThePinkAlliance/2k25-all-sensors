// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import org.littletonrobotics.junction.Logger;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {
  private TalonFX m_ElevatorMotor                     = new TalonFX(20, "canivore1");
  private DigitalInput m_ElevatorBottomMagneticSwitch = new DigitalInput(5);
  private DigitalInput m_ElevatorTopMagneticSwitch    = new DigitalInput(6);
  private Encoder m_ElevatorEncoder                   = new Encoder(3,4);
  private PIDController m_ElevatorPID;
  public double kP, kI, kD, kMinOutput, kMaxOutput;
  
  
  /** Creates a new ElevatorSubsystem. */
  public ElevatorSubsystem() {
      kP = 0.001;
      kI = 0.0;
      kD = 0.0;
      m_ElevatorPID = new PIDController(kP, kI, kD);
      m_ElevatorEncoder.setReverseDirection(true);

  }

  public void resetElevatorEncoder() {
    m_ElevatorEncoder.reset();
    System.out.println("RESET EE");
  }

  public boolean getElevatorTopSwitch() {
    return m_ElevatorTopMagneticSwitch.get();
  }

  public boolean getElevatorBottomSwitch() {
    return !m_ElevatorBottomMagneticSwitch.get();
  }

  public double getElevatorEncPos() {
    return m_ElevatorEncoder.get();
  }

  public Command resetEE() {
    return runOnce(
      ()-> {
        resetElevatorEncoder();
      }
    );
  }

  @Override
  public void periodic() {
    
    Logger.recordOutput("AllSensors/Elevator/ElevatorEncodeREV", m_ElevatorEncoder.get());
    Logger.recordOutput("AllSensors/Elevator/BottomMagneticSwitch", getElevatorBottomSwitch());
    Logger.recordOutput("AllSensors/Elevator/TopMagneticSwitch", getElevatorTopSwitch());
    Logger.recordOutput("AllSensors/Elevator/ElevatorMotorCurrent", m_ElevatorMotor.getTorqueCurrent().getValueAsDouble());
  }

  //Purpose: use by awesome closed loop controls
  public void movePivotWithPID(double currentDistance, double setPoint) {
    m_ElevatorMotor.set(MathUtil.clamp(m_ElevatorPID.calculate(currentDistance, setPoint),Constants.ElevatorConstants.ELEVATOR_PIVOT_PID_CLAMP_MIN, Constants.ElevatorConstants.ELEVATOR_PIVOT_PID_CLAMP_MAX));
  }
  public void moveElevator(double power) {
    m_ElevatorMotor.set(power);
  }
  
  
}
