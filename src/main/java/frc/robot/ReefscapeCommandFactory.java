// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.config.MAXMotionConfig;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import frc.robot.commands.CollectAlgae;
import frc.robot.commands.ElevateToPosition;
import frc.robot.commands.IntakeCoral;
import frc.robot.commands.ScoreAlgae;
import frc.robot.commands.ScoreCoral;
import frc.robot.commands.StowAlgae;
import frc.robot.subsystems.AlgaeIntake;
import frc.robot.subsystems.CoralShooterSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;

/** Add your docs here. */
public class ReefscapeCommandFactory {
    ElevatorSubsystem m_ElevatorSubsystem = null;
    CoralShooterSubsystem m_CoralShooterSubsystem = null;
    AlgaeIntake m_AlgaeIntake = null;

    public ReefscapeCommandFactory(ElevatorSubsystem elevator, CoralShooterSubsystem coral, AlgaeIntake algae) {
        m_ElevatorSubsystem = elevator;
        m_CoralShooterSubsystem = coral;
        m_AlgaeIntake = algae;
    }

    public Command doNothing() {
        return Commands.none();
    }

    public Command waitTimeInSeconds(double seconds) {
        return Commands.waitSeconds(seconds);
    }

    public ConditionalCommand reefLevelOne() {
        return new ConditionalCommand(
            new ElevateToPosition(m_ElevatorSubsystem, Constants.ElevatorConstants.LEVEL1_POSITION),
            elevatorStow(),
            () -> m_CoralShooterSubsystem.getCoralShooterProximitySensor()
        );
    }

    public Command reefLevelTwo() {
        return new ElevateToPosition(m_ElevatorSubsystem, Constants.ElevatorConstants.LEVEL2_POSITION);
    }

    public Command reefLevelThree() {
        return new ElevateToPosition(m_ElevatorSubsystem, Constants.ElevatorConstants.LEVEL3_POSITION);
    }

    public Command reefLevelFour() {
        return new ElevateToPosition(m_ElevatorSubsystem, Constants.ElevatorConstants.LEVEL4_POSITION);
    }

    public Command reefStartAlgaeEjector(double speed) {
        return m_CoralShooterSubsystem.startAlgaeEject();
    }

    public Command reefStopAlgaeEjectort(double speed) {
        return m_CoralShooterSubsystem.stopAlgaeEject();
    }

    public Command reefEjectAlgaeLevelTwo(double speed) {
        //Got to position , spin ejector wheels simultaneously
        return reefLevelTwo().alongWith(reefStartAlgaeEjector(speed));
    }

    public Command reefEjectAlgaeLevelThree(double speed) {
        //Got to position , spin ejector wheels simultaneously
        return reefLevelThree().alongWith(reefStartAlgaeEjector(speed));
    }

    public Command elevatorStow() {
        return new ElevateToPosition(m_ElevatorSubsystem, Constants.ElevatorConstants.STARTING_POSITION);
    }

    public Command elevatorResetEncoder() {
        return m_ElevatorSubsystem.resetEE();
    }

    public Command coralEject(double speed) {
        return new ScoreCoral(m_CoralShooterSubsystem, speed);
    }

    public Command coralIntake(double speed) {
        //Got to collection starting point, spin intake wheels simultaneously
        return elevatorStow().alongWith(new IntakeCoral(m_CoralShooterSubsystem, speed));
    }

    public Command algaeScore(double speed) {
        return new ScoreAlgae(m_AlgaeIntake, speed);
    }

    public Command algaeStow() {
        return new StowAlgae(m_AlgaeIntake);
    }

    public Command algaeCollect(double speed) {
        return new CollectAlgae(m_AlgaeIntake, speed);
    }

    public Command algaeResetEncoder() {
        return m_AlgaeIntake.resetAPE();
    }

    

}
