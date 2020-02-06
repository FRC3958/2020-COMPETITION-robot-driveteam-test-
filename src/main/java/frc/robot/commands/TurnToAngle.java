/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Drivetrain;
  
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TurnToAngle extends PIDCommand {
  /**
   * Creates a new DriveStraight.
   */
  public TurnToAngle(double targetAngle, Drivetrain driveTrain) {
    super(
        // The controller that the command will use
        new PIDController(0.f, 0.f, 0.f),
        // This should return the measurement
        driveTrain::getYaw,
        // This should return the setpoint (can also be a constant)
        targetAngle,
        // This uses the output
        output -> {
          // Use the output here
          driveTrain.arcadeDrive(0, output);
        }
    );

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);

    // Configure additional PID options by calling `getController` here.
    getController().enableContinuousInput(-180.f, 180.f);
    getController().setTolerance(3.f /*degrees*/);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_controller.atSetpoint();
  }
}
