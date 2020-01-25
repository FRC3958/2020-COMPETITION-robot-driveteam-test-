/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.TurnToAngle;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // OI
  private final XboxController m_driverController = new XboxController(Constants.OI.kDriverControllerPort);

  // Subsystems
  public final static Drivetrain m_drivetrain = new Drivetrain();

  // Drivetrain
  private final AutonomousCommand m_autoCommand = new AutonomousCommand(m_drivetrain);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_drivetrain.setDefaultCommand(new RunCommand(() -> m_drivetrain
      .arcadeDrive(m_driverController.getY(GenericHID.Hand.kLeft), -m_driverController.getX(GenericHID.Hand.kRight)), m_drivetrain)
    );
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    // turn left or right 90 degrees based on bumper input

    new JoystickButton(m_driverController, XboxController.Button.kBumperLeft.value)
      .whenPressed(new TurnToAngle(m_drivetrain.getYaw() - 90.f, m_drivetrain).withTimeout(5.f));

    new JoystickButton(m_driverController, XboxController.Button.kBumperRight.value)
      .whenPressed(new TurnToAngle(m_drivetrain.getYaw() + 90.f, m_drivetrain).withTimeout(5.f));
  }
  /*
{
  :)- finn suppelsa was herMOMOe
}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
