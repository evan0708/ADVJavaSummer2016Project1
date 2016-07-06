package edu.pdx.cs410J.shuping;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Integration tests for the {@link Project1} main class.
 */
public class Project1IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project1} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project1.class, args );
    }

    /**
     * Tests that invoking the main method with no arguments issues an error
     */
    @Test
    public void testNoCommandLineArguments() {
        MainMethodResult result = invokeMain();
        assertThat(result.getErr(), containsString("Missing command line arguments"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    public void testOneCommandLineArgumentsOwner() {
        String owner = "Evan";
        MainMethodResult result = invokeMain(owner);
        String expected = Project1.MISSING_COMMAND_LINE_ARGUMENTS;
        assertThat(result.getErr(), containsString(expected));
        assertThat(result.getExitCode(), equalTo(1));
    }

    private void assertThatStandardErrorContains(String errorMessage, String...args ) {
        MainMethodResult result = invokeMain(args);
        Assert.assertThat(result.getErr(), containsString(errorMessage));
        Assert.assertThat(result.getExitCode(), IsEqual.equalTo(1));
    }

    @Test
    public void missingLessThanSixCommandLineArgumentsPrintedStandardError() throws Exception {
        String[] moreThanTenArgus = new String[Project1.MINIMUM_ARGUMENTS - 1];
        for (int i = 0; i < Project1.MINIMUM_ARGUMENTS - 1; i++) {
            moreThanTenArgus[i] = String.valueOf(i);
            //System.out.println("value: " + moreThanTenArgus[i]);
        }
        String errorMessage = Project1.MISSING_COMMAND_LINE_ARGUMENTS;
        assertThatStandardErrorContains(errorMessage, moreThanTenArgus);
    }

    @Test
    public void moreThanEightCommandLineArgumentsPrintedStandardError() throws Exception {
        String[] moreThanTenArgus = new String[Project1.MAXIMUM_ARGUMENTS + 1];
        for (int i = 0; i < Project1.MAXIMUM_ARGUMENTS + 1; i++) {
            moreThanTenArgus[i] = String.valueOf(i);
        }
        String errorMessage = Project1.TOO_MANY_COMMAND_LINE_ARGUMENTS;
        assertThatStandardErrorContains(errorMessage, moreThanTenArgus);
    }

    @Test
    public void whenArgumentAreMissingUsageMessageIsPrintedToStandardError() {
        String errorMessage = Project1.USAGE;
        assertThatStandardErrorContains(errorMessage);
    }

    @Test
    public void whenNoOptionArgumentsPassingExitCodeIsZero() throws Exception {
        assertThatArgumentsAreValid("owner", "description", "7/08/2016", "14:39", "7/18/2016", "15:40");
    }

    private void assertThatArgumentsAreValid(String...args) {
        MainMethodResult result = invokeMain(args);
        Assert.assertThat(result.getExitCode(), IsEqual.equalTo(0));
    }

    private void assertThatArgumentsAreInvalid(String...args) {
        MainMethodResult result = invokeMain(args);
        Assert.assertThat(result.getExitCode(), IsEqual.equalTo(1));
    }

    @Test
    public void firstArgumentIsDashPrintOptionExitCodeIsZero() throws Exception {
        assertThatArgumentsAreValid("-print", "owner", "description", "7/08/2016", "14:39", "07/18/2016", "15:40");
    }

    @Test
    public void firstArgumentCaseInsensitiveDashPrintOptionIsValid() throws Exception {
        assertThatArgumentsAreValid("-prInT", "owner", "description", "7/08/2016", "14:39", "07/18/2016", "15:40");
    }

    @Test
    public void firstArgumentIsDashReadmeOptionExitCodeIsZero() throws Exception {
        assertThatArgumentsAreValid("-readme", "owner", "description", "7/08/2016", "14:39", "07/18/2016", "15:40");
    }

    @Test
    public void firstArgumentCaseInsensitiveDashReadmeOptionIsValid() throws Exception {
        assertThatArgumentsAreValid("-README", "owner", "description", "7/08/2016", "14:39", "07/18/2016", "15:40");
    }

    @Test
    public void firstAndSecondArgumentAreOptionWithReadmePrintOrderExitCodeIsZero() throws Exception {
        assertThatArgumentsAreValid("-README", "-print", "owner", "description", "7/08/2016", "14:39", "07/18/2016", "15:40");
    }

    @Test
    public void firstAndSecondArgumentAreOptionWithPrintReadmeOrderExitCodeIsZero() throws Exception {
        assertThatArgumentsAreValid("-print", "-readme", "owner", "description", "7/08/2016", "14:39", "07/18/2016", "15:40");
    }

    @Test
    public void whenPassingDuplicatedOptionArgumentShouldPrintStandardErrorMessage() throws Exception {
        String errorMessage = Project1.DUPLICATED_COMMAND_LINE_OPTION_ARGUMENTS;
        assertThatStandardErrorContains(errorMessage, "-print", "-Print", "owner", "description", "7/08/2016", "14:39", "07/18/2016", "15:40");
    }

    @Test
    public void whenPassingInvalidOptionArgumentShouldPrintStandardErrorMessage() throws Exception {
        String errorMessage = Project1.INVALID_COMMAND_LINE_OPTIONS_ARGUMENT;
        assertThatStandardErrorContains(errorMessage, "-Invalid", "owner", "description", "7/08/2016", "14:39", "07/18/2016", "15:40");
    }

    @Test
    public void whenPassingExtraArgumentsWithoutAnyValidOptionShouldPrintStandardErrorMessage() throws Exception {
        String errorMessage = Project1.COMMAND_LINE_ARGUMENTS_ARE_EXTRANEOUS;
        assertThatStandardErrorContains(errorMessage, "owner", "owner", "description", "7/08/2016", "14:39", "07/18/2016", "15:40");
    }

    @Test
    public void whenPassingExtraArgumentsWithOneValidOptionShouldPrintStandardErrorMessage() throws Exception {
        String errorMessage = Project1.COMMAND_LINE_ARGUMENTS_ARE_EXTRANEOUS;
        assertThatStandardErrorContains(errorMessage, "-print", "owner", "owner", "description", "7/08/2016", "14:39", "07/18/2016", "15:40");
    }

    @Test
    public void whenPassingExtraArgumentsWithTwoValidOptionExistCodeIsZero() throws Exception {
        assertThatArgumentsAreValid("-readme", "-print", "owner", "owner", "description", "7/08/2016", "14:39", "07/18/2016", "15:40");
    }

    @Test
    public void whenPassingThreeOptionArgumentsWithoutOwnerArgumentShouldPrintStandardErrorMessage() throws Exception {
        String errorMessage = Project1.INVALID_COMMAND_LINE_OPTIONS_ARGUMENT;
        assertThatStandardErrorContains(errorMessage, "-print", "-what'sup", "-nothing", "description", "7/08/2016", "14:39", "07/18/2016", "15:40");
    }

    @Test
    public void when1stArgumentOwnerContainSingleWorldExistCodeIsZero() throws Exception {
        assertThatArgumentsAreValid("Owner", "description", "7/08/2016", "14:39", "07/18/2016", "15:40");
    }

    @Test
    public void when1stArgumentOwnerContainMultipleWordExistCodeIsZero() throws Exception {
        assertThatArgumentsAreValid("Some name", "description", "7/08/2016", "14:39", "07/18/2016", "15:40");
    }

    @Test
    public void when1stArgumentOwnerContainNoneAlphabetShouldPrintStandardErrorMessage() throws Exception {
        String errorMessage = Project1.INVALID_OWNER_CONTAINS_NONE_ALPHABET;
        assertThatStandardErrorContains(errorMessage, "Evan0708", "description", "7/08/2016", "14:39", "07/18/2016", "15:40");
    }

    @Test
    public void when2ndArgumentDescriptionContainMultipleWordsExistCodeIsZero() throws Exception {
        assertThatArgumentsAreValid("Some name", "description something", "7/08/2016", "14:39", "07/18/2016", "15:40");
    }

    @Test
    public void when3rdArgumentBeginDateIsNotCorrectFormatContainUnderscoreShouldPrintStandardErrorMessage() throws Exception {
        String errorMessage = Project1.INVALID_DATE;
        assertThatStandardErrorContains(errorMessage, "Evan", "description", "7_08_2016", "14:39", "07/18/2016", "15:40");
    }

    @Test
    public void when3rdArgumentBeginDateIsNotCorrectFormatWithoutSlashShouldPrintStandardErrorMessage() throws Exception {
        String errorMessage = Project1.INVALID_DATE;
        assertThatStandardErrorContains(errorMessage, "Evan", "description", "7082016", "14:39", "07/18/2016", "15:40");
    }

    @Test
    public void when3rdArgumentBeginDateIsNotCorrectFormatContainLetterShouldPrintStandardErrorMessage() throws Exception {
        String errorMessage = Project1.INVALID_DATE;
        assertThatStandardErrorContains(errorMessage, "Evan", "description", "7_08_2016aa", "14:39", "07/18/2016", "15:40");
    }

    @Test
    public void when3rdArgumentBeginDateOutOfRangeShouldPrintStandardErrorMessage() throws Exception {
        String errorMessage = Project1.INVALID_DATE;
        assertThatStandardErrorContains(errorMessage, "Evan", "description", "99/99/9999", "14:39", "07/18/2016", "15:40");
    }

    @Test
    public void when3rdArgumentBeginDateIsNotCorrectFormatOnlyHaveYearShouldPrintStandardErrorMessage() throws Exception {
        String errorMessage = Project1.INVALID_DATE;
        assertThatStandardErrorContains(errorMessage, "Evan", "description", "2016", "14:39", "07/18/2016", "15:40");
    }

    @Test
    public void when4thArgumentBeginTimeOutOfRangeShouldPrintStandardErrorMessage() throws Exception {
        String errorMessage = Project1.INVALID_TIME;
        assertThatStandardErrorContains(errorMessage, "Evan", "description", "7/08/2016", "99:99", "07/18/2016", "15:40");
    }

    @Test
    public void when4thArgumentBeginTimeContainLetterShouldPrintStandardErrorMessage() throws Exception {
        String errorMessage = Project1.INVALID_TIME;
        assertThatStandardErrorContains(errorMessage, "Evan", "description", "7/08/2016", "08:39aa", "07/18/2016", "15:40");
    }

    @Test
    public void when4thArgumentBeginTimeWithoutColonShouldPrintStandardErrorMessage() throws Exception {
        String errorMessage = Project1.INVALID_TIME;
        assertThatStandardErrorContains(errorMessage, "Evan", "description", "7/08/2016", "0839", "07/18/2016", "15:40");
    }

    @Test
    public void when4thArgumentBeginTimeContainNoneColonSymbolShouldPrintStandardErrorMessage() throws Exception {
        String errorMessage = Project1.INVALID_TIME;
        assertThatStandardErrorContains(errorMessage, "Evan", "description", "7/08/2016", "08/39", "07/18/2016", "15:40");
    }

    @Test
    public void when4thArgumentBeginTimeOnlyHaveHourShouldPrintStandardErrorMessage() throws Exception {
        String errorMessage = Project1.INVALID_DATE;
        assertThatStandardErrorContains(errorMessage, "Evan", "description", "7_08_2016", "08", "07/18/2016", "15:40");
    }

    @Test
    public void whenAllOfTheArgumentsAreRandomExistCodeIsOne() throws Exception {
        assertThatArgumentsAreInvalid("owner", "7/08/2016", "15:40", "14:39", "07/18/2016", "description");
    }

    @Test
    public void afterOptionThe3rdArgumentBeginDateOutOfRangeShouldPrintStandardErrorMessage() throws Exception {
        String errorMessage = Project1.INVALID_DATE;
        assertThatStandardErrorContains(errorMessage, "-print", "Evan", "description", "99_99_9999", "14:39", "07/18/2016", "15:40");
    }

    @Test
    public void whenSeeREADMEFlagJustPrintREADMEThenProgramExistWithCodeZero() throws Exception {
        assertThatArgumentsAreValid("-README", "owner", "description", "7_08/2016", "14:39", "7/18/2016", "15:40");
    }

    @Test
    public void whenSeeREADMEFlagAtSecondOptionProgramExistCodeIsZero() throws Exception {
        assertThatArgumentsAreValid("-print", "-README", "owner", "description", "7_08/2016", "14:39", "7/18/2016", "15:40");
    }
}