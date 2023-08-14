package com.techelevator.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventDto {

    private int eventId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String eventTitle;
    private String content;
    private int userId;

    public EventDto(int eventId, LocalDate date, LocalTime startTime, LocalTime endTime, String eventTitle, String content, int userId) {
        this.eventId = eventId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventTitle = eventTitle;
        this.content = content;
        this.userId = userId;
    }

    public EventDto() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "eventId=" + eventId +
                ", startTime=" + startTime +
                ", date=" + date +
                ", endTime=" + endTime +
                ", eventTitle='" + eventTitle +
                ", content='" + content +
                ", userId='" + userId +
                '}';
    }
}