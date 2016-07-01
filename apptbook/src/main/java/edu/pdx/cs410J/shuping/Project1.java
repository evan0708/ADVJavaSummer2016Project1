package edu.pdx.cs410J.shuping;

import edu.pdx.cs410J.AbstractAppointmentBook;

/**
 * The main class for the CS410J appointment book Project
 */
public class Project1 {

  public static final String USAGE = "usage: java edu.pdx.cs410J.shuping.Project1 [Options] owner description " +
          "beginTime endTime";
  public static final int MINIMUM_ARGUMENTS = 6;
  public static final int MAXIMUM_ARGUMENTS = 8;
  public static final String MISSING_COMMAND_LINE_ARGUMENTS = "Missing command line arguments";
  public static final String TOO_MANY_COMMAND_LINE_ARGUMENTS = "Too many command line arguments";
  public static final String COMMAND_LINE_ARGUMENTS_ARE_EXTRANEOUS = "Command line arguments are extraneous";
  public static final String DUPLICATED_COMMAND_LINE_OPTION_ARGUMENTS = "Duplicate option command line arguments";
  public static final String INVALID_COMMAND_LINE_OPTIONS_ARGUMENT = "Invalid command line options argument";

  public static void main(String[] args) {
    Class c = AbstractAppointmentBook.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath
    boolean isContainPrint = false;
    boolean isContainReadme = false;

    // Print out all the args
    for (String arg : args) {
      System.out.println(arg);
    }

    // Validate correct number of args
    if (MINIMUM_ARGUMENTS > args.length) {
      errorPrintUsageAndExit(MISSING_COMMAND_LINE_ARGUMENTS);
      throw new AssertionError("Should never get here!!");
    }
    if (MAXIMUM_ARGUMENTS < args.length) {
      errorPrintUsageAndExit(TOO_MANY_COMMAND_LINE_ARGUMENTS);
      throw new AssertionError("Should never get here!!");
    }

    for (String arg: args) {
      if(arg.equalsIgnoreCase("-print")) {
        if (true == isContainPrint) {
          errorPrintUsageAndExit(DUPLICATED_COMMAND_LINE_OPTION_ARGUMENTS);
        }
        isContainPrint = true;
      } else if(arg.equalsIgnoreCase("-readme")) {
        if (true == isContainReadme) {
          errorPrintUsageAndExit(DUPLICATED_COMMAND_LINE_OPTION_ARGUMENTS);
        }
        isContainReadme = true;
      } else if('-' == arg.charAt(0)) {
        errorPrintUsageAndExit(INVALID_COMMAND_LINE_OPTIONS_ARGUMENT);

        throw new AssertionError("Should never get here!!");
      } else {

      }
    }

    System.exit(0);
  }

  public static void errorPrintUsageAndExit(String errorMessage) {
    System.err.println(errorMessage);
    System.err.println();
    System.err.println(USAGE);
    System.exit(1);
  }

}

