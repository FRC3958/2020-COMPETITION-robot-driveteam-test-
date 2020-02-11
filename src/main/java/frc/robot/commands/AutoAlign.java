/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoAlign extends PIDCommand {

  static double firstSeenAngle = 0d;

  Drivetrain m_drivetrain;
  Timer m_timer = new Timer();

  /**
   * Creates a new AutoAlign.
   */
  public AutoAlign(Drivetrain drivetrain) {
    super(
        // The controller that the command will use
        new PIDController(.05d, 0, 0),
        // This should return the measurement
        () -> { 
          if(drivetrain.gettx() != 0d) {
            return drivetrain.gettx();
          } else {
            return firstSeenAngle - drivetrain.getYaw();
          }
        },
        // This should return the setpoint (can also be a constant)
        0,
        // This uses the output
        output -> {
          // Use the output here
          drivetrain.arcadeDrive(0, output);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
    // Configure additional PID options by calling `getController` here.
    getController().setTolerance(.7d);

    SmartDashboard.putData(getController());
    m_timer.reset();
    m_timer.stop();

    m_drivetrain = drivetrain;
  }

  @Override
  public void execute() {
    super.execute();

    if(m_drivetrain.gettx() != 0d) {
      firstSeenAngle = m_drivetrain.getYaw() + m_drivetrain.gettx();
    }

    SmartDashboard.putNumber("extrapolated tx", firstSeenAngle - m_drivetrain.getYaw());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(getController().atSetpoint()) {

      m_timer.start();

      if(m_timer.get() >= 5d) {
        return true;
      } 
    }

    m_timer.reset();
    m_timer.stop();

    return false;
  }
}
