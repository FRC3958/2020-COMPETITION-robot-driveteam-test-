/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.*;
import frc.robot.commands.*;
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
  private final XboxController m_driverController = new XboxController(Constants.kDriverControllerPort);
  private final XboxController m_operatController = new XboxController(Constants.kOperatorControllerPort);

  // Subsystems
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Limelightlib m_limelight = new Limelightlib();
  private final Intake m_intake = new Intake();
  private final indexerandbelttoshooter m_index = new indexerandbelttoshooter();
  private final HoodedShooter m_shooter = new HoodedShooter();
  //commands
  private final Shootatrpm m_rpm = new Shootatrpm(m_shooter, Constants.rpm, 40);
  private final AutoAlign m_align = new AutoAlign(m_drivetrain, m_limelight);
  private final initiateintake  m_putintake = new initiateintake(m_intake, Constants.indexspeed);
  private final putbackintake m_putbackintake = new putbackintake(m_intake);
  private final runindexbelt m_run = new runindexbelt(m_index, Constants.indexspeed, Constants.beltspeed);
  private final zeroindexer m_zero = new zeroindexer(m_index);
  
  
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_drivetrain.setDefaultCommand(new RunCommand(() -> m_drivetrain
      .arcadeDrive(m_driverController.getY(GenericHID.Hand.kLeft), -m_driverController.getX(GenericHID.Hand.kRight)), m_drivetrain)
    );
    m_intake.setDefaultCommand(m_putbackintake);
    m_index.setDefaultCommand(m_zero);

  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    //driver
     new JoystickButton(m_driverController,Constants.alighbutton ).whenHeld(m_align);

     //operator
    new JoystickButton(m_operatController,Constants.intakebutton).whenPressed(m_putintake);
    new JoystickButton(m_operatController,Constants.outtakebutton).whenPressed(m_putbackintake);
    new JoystickButton(m_operatController, Constants.indexbeltbutton).whenPressed(m_run);
    new JoystickButton(m_operatController, Constants.shooterbutton).whenPressed(m_rpm);

     if (m_operatController.getAButton()){

      m_limelight.setledmode(3);
      m_limelight.setcammode(0);

     }
     else {

      m_limelight.setledmode(1);
      m_limelight.setcammode(1);

     }
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
   // return m_align;
   return null;
  }

}
