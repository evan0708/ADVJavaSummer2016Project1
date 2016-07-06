package edu.pdx.cs410J.shuping;

import edu.pdx.cs410J.AbstractAppointment;
import edu.pdx.cs410J.AbstractAppointmentBook;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <code>Project1</code>
 * The main class for the CS410J appointment book Project 1
 * utilizes {@link AppointmentBook}, {@link Appointment} for creating new appointment into the appointmentBook
 *
 * @author Shuping Chu
 */
public class Project1 {

    public static final String USAGE = "usage: java edu.pdx.cs410J.shuping.Project1 [Options] <args>\n" +
            "args are (in this order):\n" +
            "owner                The person whose owns the appt book\n" +
            "description          A description of the appointment\n" +
            "beginTime            When the appt begins (24-hour time)\n" +
            "endTime              When the appt ends (24-hour time)\n" +
            "options are (options may appear in any order):\n" +
            "-print               Prints a description of the new appointment\n" +
            "-README              Prints a README for this project and exits\n" +
            "Date and time should be in the format: mm/dd/yyyy hh:mm\n";
    public static final int MINIMUM_ARGUMENTS = 6;
    public static final int MAXIMUM_ARGUMENTS = 8;
    public static final String MISSING_COMMAND_LINE_ARGUMENTS = "Missing command line arguments";
    public static final String TOO_MANY_COMMAND_LINE_ARGUMENTS = "Too many command line arguments";
    public static final String COMMAND_LINE_ARGUMENTS_ARE_EXTRANEOUS = "Command line arguments are extraneous";
    public static final String DUPLICATED_COMMAND_LINE_OPTION_ARGUMENTS = "Duplicate option command line arguments";
    public static final String INVALID_COMMAND_LINE_OPTIONS_ARGUMENT = "Invalid command line options argument";
    public static final String INVALID_OWNER_CONTAINS_NONE_ALPHABET = "Invalid owner contains none alphabet";
    public static final String INVALID_DATE_AND_TIME = "Invalid date and time, please using format:MM/DD/YYYY hh:mm";
    public static final String INVALID_DATE = "Invalid date, please using format:MM/DD/YYYY";
    public static final String INVALID_TIME = "Invalid time, please using format:HH:mm";
    public static final String UNPARSEABLE_DATE = "Unparseable date:";
    public static final String UNPARSEABLE_TIME = "Unparseable time:";
    public static final String INCORRECT_DATE_OR_TIME_FORMAT = "Incorrect date or time format";
    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    private static final String README = USAGE + "\n" +
            "Name Of Assignment: CS510J Advance Java Programming - Project 1\n" +
            "Project Author: Shu-Ping Chu\n" +
            "Project Description:\n" +
            "    Project 1 should parse at least 6 - 8 arguments are specified by the above USAGE in order to create a new appointment.\n" +
            "    Once the new appointment was crated, it will be automatically stored into the appointmentBook that include the owner name,\n" +
            "    and a linear link list of appointments. Each detail of appointment would contains for example, description, beginTime (start\n" +
            "    date and start time) and endTime (end date and end time) respectively.";

    /**
     * Main method that will parse the command line arguments with at least 6 - 8 arguments, and
     * create as a appointment and store into the appointmentBook by owner
     *
     * @param args [Options] -print -readme [args] owner, description, beginDate beginTime endDate endTime
     * @throws ParseException for any unparseable exception
     */
    public static void main(String[] args) throws ParseException {
        Class c = AbstractAppointmentBook.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath
        boolean isContainPrint = false;           // Mark if command line argument contain -print
        boolean isContainReadme = false;          // Mark if command line argument contain -readme
        String[] rearrangeArgs = new String[MINIMUM_ARGUMENTS]; // Store command line argument without options
        int startingIndex = 0;                    // starting index for store rearrangeArgs
        AbstractAppointmentBook appointmentBook = null;
        AbstractAppointment appointment = null;

        // If contain readme flag, then print readme and exist
        checkIfContainReadme(args);

        // Validate correct number of args
        if (MINIMUM_ARGUMENTS > args.length) {
            errorPrintUsageAndExit(MISSING_COMMAND_LINE_ARGUMENTS);
            throw new AssertionError("Should never get here!!");
        }
        if (MAXIMUM_ARGUMENTS < args.length) {
            errorPrintUsageAndExit(TOO_MANY_COMMAND_LINE_ARGUMENTS);
            throw new AssertionError("Should never get here!!");
        }

        // Start parsing command line arguments
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
                try {
                    rearrangeArgs[startingIndex] = arg;
                    System.out.println("RearrangeArgs:" + rearrangeArgs[startingIndex]);
                    startingIndex += 1;
                } catch (ArrayIndexOutOfBoundsException ex) {
                    errorPrintUsageAndExit(COMMAND_LINE_ARGUMENTS_ARE_EXTRANEOUS);

                    throw new AssertionError("Should never get here!!");
                }
            }
        }

        // Store owner and description information
        String owner = validateOwner(rearrangeArgs[0]);
        String description = rearrangeArgs[1];

        // Valid begin date and time if is correct format, then store it.
        String beginDate = rearrangeArgs[2];
        validateMultipleDateFormat(beginDate);
        String beginTime = rearrangeArgs[3];
        validateMultipleTimeFormat(beginTime);
        StringBuilder sbBeginDateAndTime = new StringBuilder();
        sbBeginDateAndTime.append(beginDate + " " + beginTime);
        String validBeginDateTime = validateDateAndTime(sbBeginDateAndTime);
        System.out.println("validBeginDateTime: " + validBeginDateTime);

        // Valid end date and time if is correct format, then store it.
        String endDate = rearrangeArgs[4];
        validateMultipleDateFormat(endDate);
        String endTime = rearrangeArgs[5];
        validateMultipleTimeFormat(endTime);
        StringBuilder sbEndDateAndTime = new StringBuilder();
        sbEndDateAndTime.append(endDate + " " + endTime);
        String validEndDateTime = validateDateAndTime(sbEndDateAndTime);
        System.out.println("validEndDateTime: " + validEndDateTime);

        printAppointmentInfo(rearrangeArgs, isContainPrint, isContainReadme);

        // Adding appointment to appointment book
        appointmentBook = new AppointmentBook(owner);
        appointment = new Appointment(description, validBeginDateTime, validEndDateTime);
        appointmentBook.addAppointment(appointment);

        // Display appointmentBook info
        System.out.println("AppointmentBook detail: ");
        System.out.println("  Owner: " + appointmentBook.getOwnerName());
        System.out.println("  Detail: " + appointmentBook.getAppointments().toString());

        System.exit(0);
    }

    /**
     * Valid time format by given specific time format for example, HH:MM with input value, if
     * parsable return true, otherwise return false
     * @param timeFormat Given any string time format for example, hh:mm
     * @param value Given any value that want to be parsed
     * @return return true if time is in correct format, otherwise false
     */
    private static boolean isValidTimeFormat(String timeFormat, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            //ex.printStackTrace();
            errorPrintUsageAndExit(INVALID_TIME);
            return false;
        }
        return date != null;
    }

    /**
     * Validate multiple time format for example, kk:mm or k:mm, if it's valid do nothing, otherwise, print error
     *
     * @param inputDate Given any input string time for parsing
     */
    private static void validateMultipleTimeFormat(String inputDate) {
        if (true == isValidTimeFormat("kk:mm", inputDate)) {
            //System.out.println("time valid1");
        } else if (true == isValidTimeFormat("k:mm", inputDate)) {
            //System.out.println("time valid2");
        } else {
            errorPrintUsageAndExit(INVALID_TIME);
        }
    }

    /**
     * Valid date format by given specific date format for example, mm:dd:yyyy with input value, if
     * parsable return true, otherwise return false
     *
     * @param dateFormat Given any string date format for example, mm:dd:yyyy, m:dd:yyyy etc.
     * @param value Given any value that want to be parsed
     * @return true if date is in correct format, otherwise false
     */
    private static boolean isValidDateFormat(String dateFormat, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            //ex.printStackTrace();
            errorPrintUsageAndExit(INVALID_DATE);
            return false;
        }
        return date != null;
    }

    /**
     * Validate multiple date format for example, MM/dd/yyyy or M/d/yyyy,  if it's valid do nothing, otherwise, print error
     *
     * @param inputDate Given any input date for parsing
     */
    private static void validateMultipleDateFormat(String inputDate) {
        if (true == isValidDateFormat("MM/dd/yyyy", inputDate)) {
            //System.out.println("date valid1");
        } else if (true == isValidDateFormat("M/dd/yyyy", inputDate)) {
            //System.out.println("date valid2");
        } else if (true == isValidDateFormat("MM/d/yyyy", inputDate)) {
            //System.out.println("date valid3");
        } else if (true == isValidDateFormat("M/d/yyyy", inputDate)) {
            //System.out.println("date valid4");
        } else {
            errorPrintUsageAndExit(INVALID_DATE);
        }
    }

    /**
     * Validate both date and time to make sure the format is valid
     *
     * @param sb StringBuilder type that represent date and time
     * @return ture if is validate date, otherwise false
     */
    private static String validateDateAndTime(StringBuilder sb) {
        Date date;
        String validateDate;

        try {
            date = dateTimeFormat.parse(sb.toString());
            validateDate = dateTimeFormat.format(date);

        } catch (ParseException ex) {
            errorPrintUsageAndExit(INVALID_DATE_AND_TIME);

            throw new AssertionError("Should never get here!!");

        }
        return validateDate;
    }

    /**
     * Validate given owner argument if not contains any numbers. If contains, print error and exit code is 1
     *
     * @param rearrangeArg string for owner name
     * @return original owner string name
     */
    private static String validateOwner(String rearrangeArg) {
        if (true == rearrangeArg.matches(".*\\d.*")) {
            errorPrintUsageAndExit(INVALID_OWNER_CONTAINS_NONE_ALPHABET);
        }
        return rearrangeArg;
    }

    /**
     * Print error message to indicate what's going on in the program, and exit with code 1
     *
     * @param errorMessage string error message
     */
    public static void errorPrintUsageAndExit(String errorMessage) {
        System.err.println(errorMessage);
        System.err.println();
        System.err.println(USAGE);
        System.exit(1);
    }

    /**
     * Scan if -readme flag indicated at first two arguments, if found then call README() and exit
     *
     * @param args from command line arguments
     */
    public static void checkIfContainReadme(String[] args) {
        int count = 0;
        for (String arg: args) {
            if (arg.equalsIgnoreCase("-readme") && 2 > count)
                README();
            count += 1;
        }
    }

    /**
     * Print readme description and exit
     */

    public static void README() {
        System.out.println(README);
        System.exit(0);
    }

    /**
     * Print appointment info from the command line arguments just for debugging
     *
     * @param args from command line argumetns
     * @param isContainPrint indicate -print flag
     * @param isContainReadme indicate -readme flag
     */
    public static void printAppointmentInfo(String[] args, boolean isContainPrint, boolean isContainReadme) {
        System.out.print("\nValid Appointment Info: ");
        if (true == isContainPrint)
            System.out.print("-print ");
        if (true == isContainReadme)
            System.out.print("-readme ");
        for (String arg : args) {
            System.out.print(arg);
            System.out.print(" ");
        }
        System.out.println();
    }

}

