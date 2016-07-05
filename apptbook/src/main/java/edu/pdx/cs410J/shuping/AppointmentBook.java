package edu.pdx.cs410J.shuping;

import edu.pdx.cs410J.AbstractAppointmentBook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by evanchu on 6/30/16.
 */
public class AppointmentBook extends AbstractAppointmentBook<Appointment> {
    private String owner = null;
    private List<Appointment> appointments = null;

    public AppointmentBook() {

    }

    public AppointmentBook(String owner) {
        this.owner = owner;
        appointments = new ArrayList<Appointment>();
    }

    @Override
    public String getOwnerName() {
        return this.owner;
    }

    @Override
    public Collection<Appointment> getAppointments() {
        return this.appointments;
    }

    @Override
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

}
