package com.techelevator.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EventDto {

    private int eventId;
    private LocalDate date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String eventTitle;
    private String content;
    private String classVarchar;
    private int userId;

    public EventDto(int eventId, LocalDate date, LocalDateTime startTime, LocalDateTime endTime, String eventTitle, String content, int userId, String classVarchar) {
        this.eventId = eventId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventTitle = eventTitle;
        this.content = content;
        this.classVarchar = classVarchar;
        this.userId = userId;
    }
    public EventDto(LocalDateTime startTime, LocalDateTime endTime, String eventTitle, String content, String classVarchar) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventTitle = eventTitle;
        this.content = content;
        this.classVarchar = classVarchar;
    }
    public EventDto() {
    }

    public String getClassVarchar() {
        return classVarchar;
    }

    public void setClassVarchar(String classVarchar) {
        this.classVarchar = classVarchar;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
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
                ", class='" + classVarchar +
                ", userId='" + userId +
                '}';
    }
}