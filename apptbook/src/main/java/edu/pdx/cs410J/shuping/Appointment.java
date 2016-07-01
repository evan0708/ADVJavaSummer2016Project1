package edu.pdx.cs410J.shuping;

import edu.pdx.cs410J.AbstractAppointment;

public class Appointment extends AbstractAppointment {

  private String owner;
  private String description;
  private String beginTime;
  private String endTime;

  public Appointment() {
  }

  public Appointment(String owner, String description, String beginTime, String endTime) {
    this.owner = owner;
    this.description = description;
    this.beginTime = beginTime;
    this.endTime = endTime;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner() {
    this.owner = owner;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String getBeginTimeString() {
    if (this.beginTime == null)
      throw new UnsupportedOperationException("This method is not implemented yet");
    else
      return this.beginTime;
  }

  @Override
  public String getEndTimeString() {
    if (this.endTime == null)
      throw new UnsupportedOperationException("This method is not implemented yet");
    else
      return this.endTime;
  }

  @Override
  public String getDescription() {
    if (this.description == null)
      return "This method is not implemented yet";
    else
      return this.description;
  }
}
