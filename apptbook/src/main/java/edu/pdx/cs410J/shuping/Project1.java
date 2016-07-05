package edu.pdx.cs410J.shuping;

import edu.pdx.cs410J.AbstractAppointment;
import edu.pdx.cs410J.AbstractAppointmentBook;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The main class for the CS410J appointment book Project
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
            "Date and time should be in the format: mm/dd/yyyy hh:mm";
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


    public static void main(String[] args) throws ParseException {
        Class c = AbstractAppointmentBook.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath
        boolean isContainPrint = false;           // Mark if command line argument contain -print
        boolean isContainReadme = false;          // Mark if command line argument contain -readme
        String[] rearrangeArgs = new String[MINIMUM_ARGUMENTS]; // Store command line argument without options
        int startingIndex = 0;                    // starting index for store rearrangeArgs
        AbstractAppointmentBook appointmentBook = null;
        AbstractAppointment appointment = null;


        // Print out all the args
        //for (String arg : args) {
        // System.out.println(arg);
        //}

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
        System.out.println(appointmentBook.getAppointments().toString());

        System.exit(0);
    }

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

    private static void validateMultipleTimeFormat(String inputDate) {
        if (true == isValidTimeFormat("kk:mm", inputDate)) {
            System.out.println("time valid1");
        } else if (true == isValidTimeFormat("k:mm", inputDate)) {
            System.out.println("time valid2");
        } else {
            errorPrintUsageAndExit(INVALID_TIME);
        }
    }

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

    private static void validateMultipleDateFormat(String inputDate) {
        if (true == isValidDateFormat("MM/dd/yyyy", inputDate)) {
            System.out.println("date valid1");
        } else if (true == isValidDateFormat("M/dd/yyyy", inputDate)) {
            System.out.println("date valid2");
        } else if (true == isValidDateFormat("MM/d/yyyy", inputDate)) {
            System.out.println("date valid3");
        } else if (true == isValidDateFormat("M/d/yyyy", inputDate)) {
            System.out.println("date valid4");
        } else {
            errorPrintUsageAndExit(INVALID_DATE);
        }
    }

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

    private static String validateOwner(String rearrangeArg) {
        if (true == rearrangeArg.matches(".*\\d.*")) {
            errorPrintUsageAndExit(INVALID_OWNER_CONTAINS_NONE_ALPHABET);
        }
        return rearrangeArg;
    }

    public static void errorPrintUsageAndExit(String errorMessage) {
        System.err.println(errorMessage);
        System.err.println();
        System.err.println(USAGE);
        System.exit(1);
    }

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

