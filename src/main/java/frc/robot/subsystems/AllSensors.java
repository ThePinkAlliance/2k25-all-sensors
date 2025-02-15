// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.littletonrobotics.junction.Logger;

import com.ctre.phoenix6.hardware.CANcoder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AllSensors extends SubsystemBase {

  //STATIC VARS
  public static final String CAN_BUS_NAME = "canivore1";

  //DriveTrain
  private CANcoder m_SwerveFrontLeftEncoder          = new CANcoder(2, CAN_BUS_NAME);
  private CANcoder m_SwerveFrontRightEncoder         = new CANcoder(1, CAN_BUS_NAME);
  private CANcoder m_SwerveBackLeftEncoder           = new CANcoder(3, CAN_BUS_NAME);
  private CANcoder m_SwerveBackRightEncoder          = new CANcoder(4, CAN_BUS_NAME);

  //Algae
  //private Encoder m_AlgaePivotEncoder                = new Encoder(0, 1);
  //private DigitalInput m_AlgaeDetected               = new DigitalInput(2);
  
  //Elevator
  //public Encoder m_ElevatorEncoder                   = new Encoder(3,4);
  //public DigitalInput m_ElevatorBottomMagneticSwitch = new DigitalInput(5);
  //public DigitalInput m_ElevatorTopMagneticSwitch    = new DigitalInput(6);
  //public DigitalInput m_ElevatorTestSwitch           = new DigitalInput(9);
  //Coral
  //private DigitalInput m_CoralShooterProximitySensor = new DigitalInput(7);

  /** Creates a new AllSensors. */
  public AllSensors() {

    //Config
    //m_AlgaePivotEncoder.setReverseDirection(true);
    //m_ElevatorEncoder.setReverseDirection(true);

  }

  //public void resetAlgaePivotEncoder() {
  //  m_AlgaePivotEncoder.reset();
  //  System.out.println("RESET APE");
  //}

  // public void resetElevatorEncoder() {
  //   m_ElevatorEncoder.reset();
  //   System.out.println("RESET EE");
  // }

  // public boolean getElevatorTopSwitch() {
  //   return m_ElevatorTopMagneticSwitch.get();
  // }

  // public boolean getElevatorBottomSwitch() {
  //   return !m_ElevatorBottomMagneticSwitch.get();
  // }

  // public boolean getAlgaeDetector() {
  //   return !m_AlgaeDetected.get();
  // }

  // public Command resetAPE() {
  //   return runOnce(
  //     ()-> {
  //       resetAlgaePivotEncoder();
  //     }
      
  //   );
  // }

  // public Command resetEE() {
  //   return runOnce(
  //     ()-> {
  //       resetElevatorEncoder();
  //     }
  //   );
  // }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    Logger.recordOutput("AllSensors/Swerve/FrontLeftEncoder", m_SwerveFrontLeftEncoder.getPosition().getValueAsDouble());
    Logger.recordOutput("AllSensors/Swerve/FrontRightEncoder", m_SwerveFrontRightEncoder.getPosition().getValueAsDouble());
    Logger.recordOutput("AllSensors/Swerve/BackLeftEncoder", m_SwerveBackLeftEncoder.getPosition().getValueAsDouble());
    Logger.recordOutput("AllSensors/Swerve/BackRightEncoder", m_SwerveBackRightEncoder.getPosition().getValueAsDouble());
    //Logger.recordOutput("AllSensors/Algae/PivotEncodeREV", m_AlgaePivotEncoder.get());
    //Logger.recordOutput("AllSensors/Algae/DetectedSwitch", getAlgaeDetector());
    // Logger.recordOutput("AllSensors/Elevator/ElevatorEncodeREV", m_ElevatorEncoder.get());
    // Logger.recordOutput("AllSensors/Elevator/BottomMagneticSwitch", getElevatorBottomSwitch());
    // Logger.recordOutput("AllSensors/Elevator/TopMagneticSwitch", getElevatorTopSwitch());
    //Logger.recordOutput("AllSensors/Coral/BeamBreakDetect", m_CoralShooterProximitySensor.get());
    //Logger.recordOutput("AllSensors/Elevator/ElevatorTestSwitch", m_ElevatorTestSwitch.get()); 
  }
}
