package edu.pdx.cs410J.shuping;

import edu.pdx.cs410J.AbstractAppointment;

/**
 *  <code>Appointment</code> class that contain all the string object for defining the appointment
 *
 *  @author Shuping Chu
 */

public class Appointment extends AbstractAppointment {

    private String description;
    private String beginTime;
    private String endTime;

    /**
     * Create default <code>Appointment</code> constructor.
     */
    public Appointment() {
    }

    /**
     * Create <code>Appointment</code> constructor with parameters description, beginTime and endTime
     *
     * @param description detail for appointment
     * @param beginTime starting date and time
     * @param endTime ending date and time
     */
    public Appointment(String description, String beginTime, String endTime) {
        this.description = description;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    /**
     * Return the beginTime string
     *
     * @return beginTime string
     */
    @Override
    public String getBeginTimeString() {
        if (this.beginTime == null)
            throw new UnsupportedOperationException("This method is not implemented yet");
        else
            return this.beginTime;
    }

    /**
     *  Return the endTime string
     *
     * @return endTime string
     */
    @Override
    public String getEndTimeString() {
        if (this.endTime == null)
            throw new UnsupportedOperationException("This method is not implemented yet");
        else
            return this.endTime;
    }

    /**
     * Return the description string
     *
     * @return description string
     */
    @Override
    public String getDescription() {
        if (this.description == null)
            return "This method is not implemented yet";
        else
            return this.description;
    }

}
