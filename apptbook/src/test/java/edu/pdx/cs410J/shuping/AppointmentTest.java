package edu.pdx.cs410J.shuping;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link Appointment} class.
 */
public class AppointmentTest {

    @Test(expected = UnsupportedOperationException.class)
    public void getBeginTimeStringNeedsToBeImplemented() {
        Appointment appointment = new Appointment();
        appointment.getBeginTimeString();
    }

    @Test
    public void initiallyAllAppointmentsHaveTheSameDescription() {
        Appointment appointment = new Appointment();
        assertThat(appointment.getDescription(), containsString("not implemented"));
    }

    @Test
    public void forProject1ItIsOkayIfGetBeginTimeReturnsNull() {
        Appointment appointment = new Appointment();
        assertThat(appointment.getBeginTime(), is(nullValue()));
    }

    @Test
    public void appointmentOwnerContainDescriptionDinner(){
        String description = "dinner";
        String beginTime = "";
        String endTime ="";
        Appointment appointment = createAppointmentWithDescriptionBeginTimeEndTime(description, beginTime, endTime);
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
        Appointment appointment = createAppointmentWithDescriptionBeginTimeEndTime(description, beginTime, endTime);
        assertThat(appointment.getDescription(), containsString(description));
    }

    @Test
    public void appointmentBeginTimeContainGivenTime() throws Exception {
        String name = "";
        String description = "";
        String beginTime = "7/15/2016";
        String endTime ="";
        Appointment appointment = createAppointmentWithDescriptionBeginTimeEndTime(description, beginTime, endTime);
        assertThat(appointment.getBeginTimeString(), containsString(beginTime));

    }

    @Test
    public void appointmentEndTimeContainGivenTime() throws Exception {
        String description = "";
        String beginTime ="";
        String endTime = "06/2/2016";
        Appointment appointment = createAppointmentWithDescriptionBeginTimeEndTime(description, beginTime, endTime);
        assertThat(appointment.getEndTimeString(), containsString(endTime));
    }
}
