package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.EventDto;
import com.techelevator.model.WorkoutDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcEventDao implements EventDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcEventDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<EventDto> getEventsByUserId(int userId) {
        List<EventDto> events = new ArrayList<>();
        String sql = "SELECT event_id, start_time, date, end_time, event_title, content, user_id FROM events WHERE user_id = ?"; // ORDER BY date DESC;
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                EventDto event = mapRowToEventDto(results);
                events.add(event);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return events;
    }

    @Override
    public EventDto getEventByEventId(int eventId) {
        EventDto event = null;
        String sql = "SELECT event_id, start_time, date, end_time, event_title, content, user_id FROM events WHERE event_id = ?"; // ORDER BY date DESC;
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, eventId);
            while (results.next()) {
                event = mapRowToEventDto(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return event;    }

    @Override
    public EventDto createEvent(EventDto event) {
        EventDto newEvent = null;
        String insertEventSql = "INSERT INTO events (start_time, date, end_time, event_title, content, user_id) values (?, ?, ?, ?, ?, ?) RETURNING event_id";
        try {
            int newEventId = jdbcTemplate.queryForObject(insertEventSql, int.class, event.getStartTime(), event.getDate(), event.getEndTime(), event.getEventTitle(), event.getContent(), event.getUserId());
            newEvent = getEventByEventId(newEventId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newEvent;
    }

    @Override
    public EventDto updateEvent(EventDto updatedEvent) {
        EventDto result;
        String sql = "UPDATE events SET start_time = ?, date = ?, end_time = ?, event_title = ?, content = ?, user_id = ? WHERE event_id = ? RETURNING event_id;";
        try {
            int newId = jdbcTemplate.queryForObject(sql, int.class, updatedEvent.getStartTime(), updatedEvent.getDate(), updatedEvent.getEndTime(), updatedEvent.getEventTitle(), updatedEvent.getContent(), updatedEvent.getUserId(), updatedEvent.getEventId());
            result = getEventByEventId(newId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return result;
    }

    @Override
    public int deleteEventByEventId(int eventId) {
        int numberOfRows = 0;
        String sql = "DELETE FROM events WHERE event_id = ?;";
        try {
            numberOfRows = jdbcTemplate.update(sql, eventId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }


    private EventDto mapRowToEventDto(SqlRowSet results) {
        EventDto event = new EventDto();
        event.setEventId(results.getInt("event_id"));
        event.setDate(results.getDate("date").toLocalDate());
        event.setStartTime(results.getTime("start_time").toLocalTime());
        event.setEndTime(results.getTime("end_time").toLocalTime());
        event.setEventTitle(results.getString("event_title"));
        event.setContent(results.getString("content"));
        event.setUserId(results.getInt("user_id"));
        return event;
    }
}
