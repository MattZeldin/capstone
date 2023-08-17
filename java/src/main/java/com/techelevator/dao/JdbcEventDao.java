package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.EventDto;
import com.techelevator.model.WorkoutDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcEventDao implements EventDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcEventDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    @Override
//    public List<EventDto> getEventsByUserId(int userId) {
//        List<EventDto> events = new ArrayList<>();
//        String sql = "SELECT event_id, start_time, date, end_time, event_title, content, user_id FROM events WHERE user_id = ?"; // ORDER BY date DESC;
//        try {
//            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
//            while (results.next()) {
//                EventDto event = mapRowToEventDto(results);
//                events.add(event);
//            }
//        } catch (CannotGetJdbcConnectionException e) {
//            throw new DaoException("Unable to connect to server or database", e);
//        }
//        return events;
//    }
    @Override
    public List<EventDto> getEventsByUserId(int userId) {
        List<EventDto> events = new ArrayList<>();
        String sql = "SELECT event_id, starts, ends, title, content, class, user_id FROM events WHERE user_id = ?"; // ORDER BY date DESC;
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
        String sql = "SELECT starts, ends, title, content, class, user_id FROM events WHERE event_id = ?"; // ORDER BY date DESC;
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, eventId);
            while (results.next()) {
                event = mapRowToEventDto(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return event;
    }

    @Override
    public EventDto createEvent(EventDto event) {
        EventDto newEvent = null;
        String insertEventSql = "INSERT INTO events (starts, ends, title, content, class, user_id) values (?, ?, ?, ?, ?, ?) RETURNING event_id";
        try {
            int newEventId = jdbcTemplate.queryForObject(insertEventSql, int.class, event.getStartTime(), event.getEndTime(), event.getEventTitle(), event.getContent(), event.getClassVarchar(), event.getUserId());
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
        String sql = "UPDATE events SET starts = ?, ends = ?, title = ?, content = ?, class = ?, user_id = ? WHERE event_id = ? RETURNING event_id;";
        try {
            int newId = jdbcTemplate.queryForObject(sql, int.class, updatedEvent.getStartTime(), updatedEvent.getEndTime(), updatedEvent.getEventTitle(), updatedEvent.getContent(), updatedEvent.getClassVarchar(), updatedEvent.getUserId(), updatedEvent.getEventId());
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


//    private EventDto mapRowToEventDto(SqlRowSet results) {
//        EventDto event = new EventDto();
//        event.setEventId(results.getInt("event_id"));
//        event.setDate(results.getDate("date").toLocalDate());
//        event.setStartTime(results.getTimestamp("start").toLocalDateTime());
//        event.setEndTime(results.getTimestamp("end").toLocalDateTime());
//        event.setEventTitle(results.getString("title"));
//        event.setContent(results.getString("content"));
//        event.setClassVarchar(results.getString("class"));
//        event.setUserId(results.getInt("user_id"));
//        return event;
//    }
    private EventDto mapRowToEventDto(SqlRowSet results) {
        EventDto event = new EventDto();
        event.setEventId(results.getInt("event_id"));
        event.setStartTime(results.getTimestamp("starts").toLocalDateTime());
        event.setEndTime(results.getTimestamp("ends").toLocalDateTime());
        event.setEventTitle(results.getString("title"));
        event.setContent(results.getString("content"));
        event.setClassVarchar(results.getString("class"));
        event.setUserId(results.getInt("user_id"));
        return event;
    }
}
