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

    public static final class Misc {
        public static final int kAhrsPort = 0;
    }

    public static final class Drivetrain {

        public static final class Map {

            public static final int kFrontLeftTalonPort = 1;
            public static final int kFrontRightTalonPort = 2;
            public static final int kBackLeftTalonPort = 3;
            public static final int kBackRightTalonPort = 4;
        }

        /**
         * IN TALON UNITS!!!!!!!!!!! NOT m/s!!!!!!
         */
        public static final int kMaxVelocity = 2950;

        
    }

    public static final class OI {
        public static final int kDriverControllerPort = 0;
    }

	public static double kp = -0.1;
	public static double mincommand = 0.05;
}
