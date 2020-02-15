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
  public static final Drivetrain m_drivetrain = new Drivetrain();
  public static final Limelightlib m_limelight = new Limelightlib();
  public static final Intake m_intake = new Intake();
  public static final indexerandbelttoshooter m_index = new indexerandbelttoshooter();
  //commands
  public final AutoAlign m_align = new AutoAlign(m_drivetrain);
  public final initiateintake  m_putintake = new initiateintake(m_intake);
  public final putbackintake m_putbackintake = new putbackintake(m_intake);
  public final runindexbelt m_run = new runindexbelt(m_index);
  public final zeroindexerbelt m_zero = new zeroindexerbelt(m_index);
 
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
    
    //driver
     new JoystickButton(m_driverController,Constants.alighbutton ).whenHeld(m_align);

     //operator
     new JoystickButton(m_operatController, Constants.intakebutton).whenHeld(m_putintake).whenReleased(m_putbackintake);  
     new JoystickButton(m_operatController, Constants.indexbeltbutton).whenHeld(m_run).whenReleased(m_zero);

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
