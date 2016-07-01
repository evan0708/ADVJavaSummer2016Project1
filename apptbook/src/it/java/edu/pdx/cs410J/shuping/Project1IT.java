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
    String expected = "Evan";
    assertThat(result.getOut(), containsString(expected));
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
    assertThatArgumentsAreValid("owner", "description", "7/08/2016", "14:39", "07/18/2016", "15:40");
  }

  private void assertThatArgumentsAreValid(String...args) {
    MainMethodResult result = invokeMain(args);
    Assert.assertThat(result.getExitCode(), IsEqual.equalTo(0));
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

}