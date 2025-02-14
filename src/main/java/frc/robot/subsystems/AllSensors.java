// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.littletonrobotics.junction.Logger;

import com.ctre.phoenix6.hardware.CANcoder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AllSensors extends SubsystemBase {

  //DriveTrain
  private CANcoder m_SwerveFrontLeftEncoder          = new CANcoder(2, "cancoder1");
  private CANcoder m_SwerveFrontRightEncoder         = new CANcoder(1, "cancoder1");
  private CANcoder m_SwerveBackLeftEncoder           = new CANcoder(3, "cancoder1");
  private CANcoder m_SwerveBackRightEncoder          = new CANcoder(4, "cancoder1");

  //Algae
  private Encoder m_AlgaePivotEncoder                = new Encoder(0, 1);
  private DigitalInput m_AlgaeDetected               = new DigitalInput(2);
  
  //Elevator
  public Encoder m_ElevatorEncoder                   = new Encoder(3,4);
  public DigitalInput m_ElevatorBottomMagneticSwitch = new DigitalInput(5);
  public DigitalInput m_ElevatorTopMagneticSwitch    = new DigitalInput(6);

  //Coral
  private DigitalInput m_CoralShooterProximitySensor = new DigitalInput(7);

  /** Creates a new AllSensors. */
  public AllSensors() {


  }

  public void resetAlgaePivotEncoder() {
    m_AlgaePivotEncoder.reset();
  }

  public void resetElevatorEncoder() {
    m_ElevatorEncoder.reset();
  }

  public Command resetAPE() {
    return runOnce(
      ()-> {
        resetAlgaePivotEncoder();
      }
      
    );
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
    // This method will be called once per scheduler run
    Logger.recordOutput("AllSensors/Swerve/FrontLeftEncoder", m_SwerveFrontLeftEncoder.getPosition().getValueAsDouble());
    Logger.recordOutput("AllSensors/Swerve/FrontRightEncoder", m_SwerveFrontRightEncoder.getPosition().getValueAsDouble());
    Logger.recordOutput("AllSensors/Swerve/BackLeftEncoder", m_SwerveBackLeftEncoder.getPosition().getValueAsDouble());
    Logger.recordOutput("AllSensors/Swerve/BackRightEncoder", m_SwerveBackRightEncoder.getPosition().getValueAsDouble());
    Logger.recordOutput("AllSensors/Algae/PivotEncodeREV", m_AlgaePivotEncoder.get());
    Logger.recordOutput("AllSensors/Algae/DetectedSwitch", m_AlgaeDetected.get());
    Logger.recordOutput("AllSensors/Elevator/ElevatorEncodeREV", m_ElevatorEncoder.get());
    Logger.recordOutput("AllSensors/Elevator/BottomMagneticSwitch", m_ElevatorBottomMagneticSwitch.get());
    Logger.recordOutput("AllSensors/Elevator/TopMagneticSwitch", m_ElevatorTopMagneticSwitch.get());
    Logger.recordOutput("AllSensors/Coral/BeamBreakDetect", m_CoralShooterProximitySensor.get());
  }
}
