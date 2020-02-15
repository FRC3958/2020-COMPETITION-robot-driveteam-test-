/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

        //Drivetrain constants
        public static final int kAhrsPort = 0;
        public static final int kFrontLeftTalonPort = 1;
        public static final int kFrontRightTalonPort = 2;
        public static final int kBackLeftTalonPort = 3;
        public static final int kBackRightTalonPort = 4;
         /**
         * IN TALON UNITS!!!!!!!!!!! NOT m/s!!!!!!
         */
        public static final int kMaxVelocity = 2950;

        //intake constants
        public static final int intakewheels = 5;
	    public static final int intakeonesol = 0;
        public static final int intaketwosol = 1;
        public static final double intakespeed = 1.5;

        // index and belt constants
	    public static final int indexmotor = 6;
        public static final int beltmotor = 7;
        public static double indexspeed  = 2;
		public static double beltspeed = 3;
		
        //controller constants
        public static final int kDriverControllerPort = 0;
        public static final int kOperatorControllerPort = 1;
		

        //Button constants
            //driver controller
        public static int alighbutton = 1;
            //operator controller
		public static int intakebutton = 1;
		public static int indexbeltbutton = 2;
		

}
