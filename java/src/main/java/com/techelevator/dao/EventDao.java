package com.techelevator.dao;

import com.techelevator.model.EventDto;

import java.time.LocalTime;
import java.util.List;

public interface EventDao {
    List<EventDto> getEventsByUserId(int userId);
    EventDto getEventByEventId(int eventId);
    EventDto createEvent(EventDto newEvent);
    EventDto updateEvent(EventDto updatedEvent);
    int deleteEventByEventId(int eventId);
}
