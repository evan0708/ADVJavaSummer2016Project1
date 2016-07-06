package edu.pdx.cs410J.shuping;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by shuping chu on 7/6/16.
 */
public class AppointmentBookTest {
    private AppointmentBook appointmentBook;

    @Before
    public void setUp() throws Exception {
        appointmentBook = new AppointmentBook();
    }

    @Test
    public void initiallyAppointmentBookOwnerContainDefaultConstructor() throws Exception {
        assertThat(appointmentBook.getOwnerName(), containsString("AppointmentBook default constructor"));
    }

    @Test
    public void initiallyAppointmentBookHasAppointmentsListNullValue() throws Exception {
        assertThat(appointmentBook.getAppointments(), is(nullValue()));
    }
}