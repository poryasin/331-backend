package se331.lab.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.entity.Event;


public interface EventDao {
    Integer getEventSize();
    Page<Event> getEvents(Integer pageSize, Integer page);
    Event getEvent(Long id);
    Event save(Event event);
    Page<Event> getEvents(String name, Pageable page);
    Page<Event> getEventsAnd(String title,String description,String organizerName, Pageable page);
}