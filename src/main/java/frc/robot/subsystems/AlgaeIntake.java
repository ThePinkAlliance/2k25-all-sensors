// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.littletonrobotics.junction.Logger;

import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkFlex;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class AlgaeIntake extends SubsystemBase {
  //Define subsystem components
  private SparkFlex m_CollectorMotor;
  private Encoder m_AlgaePivotEncoder; 
  private DigitalInput m_AlgaeDetected;
  private PIDController m_AlgaePID;
  private SparkFlex m_PivotMotor;
  private SparkFlexConfig m_PivotConfig, m_CollectorConfig;
  public double kP, kI, kD, kMinOutput, kMaxOutput;

  

  /** Creates a new AlgaeIntake. */
  public AlgaeIntake() {
    //Wheels
    m_CollectorMotor = new SparkFlex(23, MotorType.kBrushless); 
    m_CollectorConfig = new SparkFlexConfig();

    m_AlgaePivotEncoder = new Encoder(0, 1);
    m_AlgaePivotEncoder.reset();
    m_AlgaePivotEncoder.setReverseDirection(true);
    m_AlgaeDetected = new DigitalInput(2);
    //Sets PID coefficients for the Pivot neo
    kP = 0.001;
    kI = 0;
    kD = 0;
    kMinOutput = 0;
    kMaxOutput = 0.75;
    m_AlgaePID = new PIDController(kP, kI, kD);
    m_AlgaePID.setTolerance(Constants.AlgaeIntakeConstants.ALGAE_PIVOT_PID_TOLERANCE);


    //Pivot
    m_PivotMotor = new SparkFlex(22, MotorType.kBrushless);
    m_PivotConfig = new SparkFlexConfig();
    
    //Config
    m_PivotConfig.idleMode(IdleMode.kBrake);
    m_PivotConfig.inverted(true);
    m_CollectorConfig.idleMode(IdleMode.kBrake);
    m_PivotMotor.configure((SparkBaseConfig)m_PivotConfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);
  }
  
  public void spinWheels(double speed){
    //Spins the NEO motor, speed +- 1
    m_CollectorMotor.set(speed);
  }

  //Purpose: use by open (manual or joystick) controls
  public void movePivot(double speed) {
    m_PivotMotor.set(speed);
  }

  //Purpose: use by awesome closed loop controls
  public void movePivotWithPID(double currentDistance, double setPoint) {
    m_PivotMotor.set(MathUtil.clamp(m_AlgaePID.calculate(currentDistance, setPoint),Constants.AlgaeIntakeConstants.ALGAE_PIVOT_PID_CLAMP_MIN, Constants.AlgaeIntakeConstants.ALGAE_PIVOT_PID_CLAMP_MAX));
  }

  public boolean getAlgaeDetected() {
    return (!m_AlgaeDetected.get());
  }

  public double getPivotEncoderPos() {
    return (m_AlgaePivotEncoder.get());
  }

  public void resetAlgaePivotEncoder() {
    m_AlgaePivotEncoder.reset();
    System.out.println("RESET APE");
  }

  public boolean getAlgaeDetector() {
    return !m_AlgaeDetected.get();
  }

  public Command resetAPE() {
    return runOnce(
      ()-> {
        resetAlgaePivotEncoder();
      }
      
    );
  }

   
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //Logger.recordOutput("AlgaeIntake/AlgaeIntake Proximity Sensor", ??);
    Logger.recordOutput("AllSensors/Algae/DetectedSwitch", getAlgaeDetected());
    Logger.recordOutput("AllSensors/Algae/PivotEncodeREV", getPivotEncoderPos());
    Logger.recordOutput("AllSensors/Algae/PivotMotorCurrent", m_PivotMotor.getOutputCurrent());
  }
}
