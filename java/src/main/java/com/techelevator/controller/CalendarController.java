package com.techelevator.controller;

import com.techelevator.dao.EventDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.EventDto;
import com.techelevator.model.WorkoutDto;
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


    //post request for adding an event to the database


    //put request for updating an event in the database


    //delete event based on eventId





}
