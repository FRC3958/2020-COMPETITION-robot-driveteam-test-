/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import frc.robot.Constants;
import frc.robot.subsystems.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ShootSequence extends ParallelDeadlineGroup {
  /**
   * Creates a new ShootSequence.
   */
  public ShootSequence(Drivetrain m_drivetrain, Limelightlib m_limelight, indexerandbelttoshooter m_index, HoodedShooter m_shooter) {
    // Add your commands in the super() call.  Add the deadline first.
    super( (new AutoAlign(m_drivetrain, m_limelight).withTimeout(0.5)).andThen(new Shootatrpm(m_shooter, Constants.rpm, 40))
        .alongWith(new runindexbelt(m_index, Constants.indexspeed, Constants.beltspeed))
        
    );
  }
}
