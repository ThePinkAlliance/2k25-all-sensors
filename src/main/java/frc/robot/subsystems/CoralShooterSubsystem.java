// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.littletonrobotics.junction.Logger;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CoralShooterSubsystem extends SubsystemBase {
  SparkMax m_CoralShooterMotor  = new SparkMax(24, MotorType.kBrushed);
  SparkMaxConfig m_CoralShooterConfig = new SparkMaxConfig();
  SparkMax m_AlgaeEjectorMotor  = new SparkMax(25,MotorType.kBrushed);
  DigitalInput m_CoralShooterProximitySensor = new DigitalInput(7);
  
  /** Creates a new CoralShooterSubsystem. */
  public CoralShooterSubsystem() {
    m_CoralShooterConfig.inverted(true);
    m_CoralShooterConfig.idleMode(IdleMode.kBrake);
    m_CoralShooterMotor.configure((SparkBaseConfig)m_CoralShooterConfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    Logger.recordOutput("AllSensors/Coral/BeamBreakDetect", m_CoralShooterProximitySensor.get());

  }
  public void setCoralInputSpeed(double coralPower){
    m_CoralShooterMotor.set(coralPower);
  }
  public boolean getCoralShooterProximitySensor(){
    return m_CoralShooterProximitySensor.get();
  }
    public void setAlgaeEject(double algaeEjectorPower){
    m_AlgaeEjectorMotor.set(algaeEjectorPower);
  }

  //To be run from a command composition:  button or auto sequence
  public Command startAlgaeEject() {
    return runOnce(
      ()-> {
        setAlgaeEject(Constants.CoralConstants.CORAL_ALGAE_EJECT_SPEED);
      }
    );
  }

  //To be run from a command composition:  button or auto sequence
  public Command stopAlgaeEject() {
    return runOnce(
      ()-> {
        setAlgaeEject(0);
      }
    );
  }

}
