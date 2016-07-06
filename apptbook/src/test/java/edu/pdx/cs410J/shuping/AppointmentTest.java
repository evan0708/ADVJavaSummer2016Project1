package edu.pdx.cs410J.shuping;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link Appointment} class.
 */
public class AppointmentTest {

    private Appointment appointment;
    @Before
    public void setUp() throws Exception {
        appointment = new Appointment();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getBeginTimeStringNeedsToBeImplemented() {
        appointment.getBeginTimeString();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getEndTimeStringNeedsToBeImplemented() throws Exception {
        appointment.getEndTimeString();
    }

    @Test
    public void initiallyAllAppointmentsHaveTheSameDescription() {
        assertThat(appointment.getDescription(), containsString("not implemented"));
    }

    @Test
    public void forProject1ItIsOkayIfGetBeginTimeReturnsNull() {
        assertThat(appointment.getBeginTime(), is(nullValue()));
    }

    @Test
    public void appointmentOwnerContainDescriptionDinner(){
        String description = "dinner";
        String beginTime = "";
        String endTime ="";
        appointment = createAppointmentWithDescriptionBeginTimeEndTime(description, beginTime, endTime);
        assertThat(appointment.getDescription(), containsString(description));
    }

    private Appointment createAppointmentWithDescriptionBeginTimeEndTime(String description, String beginTime, String endTime) {
        return new Appointment(description, beginTime, endTime);
    }

    @Test
    public void appointmentDescriptionContainHaveLunchWithLisa() throws Exception {
        String description = "Have lunch with Lisa";
        String beginTime = "";
        String endTime ="";
        appointment = createAppointmentWithDescriptionBeginTimeEndTime(description, beginTime, endTime);
        assertThat(appointment.getDescription(), containsString(description));
    }

    @Test
    public void appointmentBeginTimeContainGivenTime() throws Exception {
        String description = "";
        String beginTime = "7/15/2016";
        String endTime ="";
        appointment = createAppointmentWithDescriptionBeginTimeEndTime(description, beginTime, endTime);
        assertThat(appointment.getBeginTimeString(), containsString(beginTime));

    }

    @Test
    public void appointmentEndTimeContainGivenTime() throws Exception {
        String description = "";
        String beginTime ="";
        String endTime = "06/2/2016";
        appointment = createAppointmentWithDescriptionBeginTimeEndTime(description, beginTime, endTime);
        assertThat(appointment.getEndTimeString(), containsString(endTime));
    }

    @Test
    public void appointmentContainEntireString() throws Exception {
        String description = "Have dinner with Tina";
        String beginTime ="06/2/2016 16:00";
        String endTime = "06/2/2016 18:00";
        appointment = createAppointmentWithDescriptionBeginTimeEndTime(description, beginTime, endTime);
        assertThat(appointment.toString(), containsString("from"));
    }
}
