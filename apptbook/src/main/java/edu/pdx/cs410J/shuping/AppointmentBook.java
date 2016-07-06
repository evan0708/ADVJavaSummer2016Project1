package edu.pdx.cs410J.shuping;

import edu.pdx.cs410J.AbstractAppointmentBook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <code>AppointmentBook</code> Each appointmentBook has owner and a list of appointments
 *
 * @Author Shu-Ping Chu on 6/30/16.
 */
public class AppointmentBook extends AbstractAppointmentBook<Appointment> {
    private String owner = null;
    private List<Appointment> appointments = null;

    /**
     * Create default <code>AppointmentBook</code> constructor.
     */
    public AppointmentBook() {
        this("AppointmentBook default constructor");
        appointments = new ArrayList<Appointment>();
    }

    /**
     * Create <code>AppointmentBook</code> constructor with parameters owner
     * Set owner to owner, and create a list of appointment
     *
     * @param owner
     */
    public AppointmentBook(String owner) {
        this.owner = owner;
        appointments = new ArrayList<Appointment>();
    }

    /**
     * Return the string owner name
     *
     * @return owner string
     */
    @Override
    public String getOwnerName() {
        return this.owner;
    }

    /**
     * Return the appointments pointer
     *
     * @return appointments pointer, if null return null
     */
    @Override
    public Collection<Appointment> getAppointments() {
        if (appointments == null)
            return null;
        return this.appointments;
    }

    /**
     * Add appointment into the appointmentBook
     *
     * @param appointment for input appointment
     */
    @Override
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

}
