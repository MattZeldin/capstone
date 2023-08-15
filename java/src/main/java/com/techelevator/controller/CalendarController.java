package com.techelevator.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techelevator.dao.EventDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.EventDto;
import com.techelevator.model.WorkoutDto;
import jdk.jfr.Event;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class CalendarController {

    private EventDao eventDao;

    public CalendarController(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    //get request for all events of a specific user using userId
    @RequestMapping(path = "/{userId}/events", method = RequestMethod.GET)
    public List<EventDto> getEventsByUserId(@PathVariable int userId) {
        List<EventDto> events;
        try {
            events = eventDao.getEventsByUserId(userId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password is incorrect.");
        }
        return events;
    }

    //get request for a single event using eventId
    @RequestMapping(path = "/events/{eventId}", method = RequestMethod.GET)
    public EventDto getEventByEventId(@PathVariable int eventId) {
        EventDto event;
        try {
            event = eventDao.getEventByEventId(eventId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password is incorrect.");
        }
        return event;
    }

    //post request for adding an event to the database
    @RequestMapping(path = "/{userId}/events", method = RequestMethod.POST)
    public void addEvent(@PathVariable int userId, @RequestBody EventDto newEvent) {
        try {
            EventDto event = eventDao.createEvent(newEvent);
            if (event == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Logging new event failed.");
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Logging new event failed.");
        }
    }

    //put request for updating an event in the database
    @RequestMapping(path = "/events/{eventId}", method = RequestMethod.PUT)
    public EventDto eventDto(@PathVariable int eventId, @RequestBody EventDto updatedEvent) {
        EventDto result;
        try {
            result = eventDao.updateEvent(updatedEvent);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Update workout failed. " + e.getMessage());
        }
        return result;
    }

    //delete event based on eventId
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/events/{eventId}", method = RequestMethod.DELETE)
    public void deleteEvent(@PathVariable int eventId) {
        eventDao.deleteEventByEventId(eventId);
    }




}
