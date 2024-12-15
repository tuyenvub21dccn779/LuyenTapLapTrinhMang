/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;

import java.io.Serializable;

/**
 *
 * @author Acer
 */
public class Event implements Serializable {
    private static final long serialVersionUID = 20241131L;
    private int expectedAttendance;
    private String id, eventName, eventDate, eventCode;

    public Event() {
    }

    public Event(int expectedAttendance, String id, String eventName, String eventDate) {
        this.expectedAttendance = expectedAttendance;
        this.id = id;
        this.eventName = eventName;
        this.eventDate = eventDate;
    }

    public int getExpectedAttendance() {
        return expectedAttendance;
    }

    public void setExpectedAttendance(int expectedAttendance) {
        this.expectedAttendance = expectedAttendance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    @Override
    public String toString() {
        return "Event{" + "expectedAttendance=" + expectedAttendance + ", id=" + id + ", eventName=" + eventName + ", eventDate=" + eventDate + ", eventCode=" + eventCode + '}';
    }
    
}
