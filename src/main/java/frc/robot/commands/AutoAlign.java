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
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoAlign extends PIDCommand {

  static double firstSeenAngle = 0;

  private final Drivetrain m_drivetrain;
  private int kCountsToFinished = 100;

  /**
   * Creates a new AutoAlign.
   */
  public AutoAlign(Drivetrain drivetrain) {
    super(
        // The controller that the command will use
        new PIDController(0.040000d, 0.004030, 0.003099),
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

          double absOutput = Math.abs(output);

          if(absOutput <= .3) {
            
            output *= 2;

          } else if(absOutput <= .6) {

            output *= 1.1;
          }

          output = MathUtil.clamp(output, -1.0, +1.0);

          System.out.println(output);
          drivetrain.arcadeDrive(0, output);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
    // Configure additional PID options by calling `getController` here.
    getController().setTolerance(0.05);

    SmartDashboard.putData(getController());

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

  private int counts = 0;

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
    if(getController().atSetpoint()) {

      if(counts++ >= kCountsToFinished) {

        return true;
      }
    } else {

      counts = 0;
    }

    return false;
  }
}
