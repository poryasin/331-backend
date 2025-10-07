package se331.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.dao.EventDao;
import se331.lab.entity.Event;

import se331.lab.service.EventService;
import se331.lab.util.LabMapper;


@Controller
@RequiredArgsConstructor
public class EventController {

    final EventService eventService;

    @GetMapping("events")
    public ResponseEntity<?> getEventLists(@RequestParam(value = "_limit",required = false) Integer perPage,
                                           @RequestParam(value = "_page", required = false) Integer page,
                                            @RequestParam(value = "title", required = false) String title,
                                            @RequestParam(value = "description", required = false) String description,
                                           @RequestParam(value = "organizerName", required = false) String organizerName){
        perPage = (perPage == null) ? 6 : perPage;
        page = page ==null ? 1 : page;
        Page<Event> pageOutput = eventService.getEventsAnd(title,description,organizerName,PageRequest.of(page-1,perPage));
        if (title == null || title.isBlank()) {
            pageOutput = eventService.getEvents(perPage,page);
        }else{
            pageOutput = eventService.getEventsAnd(title,description,organizerName,PageRequest.of(page-1,perPage));
        }

        HttpHeaders respondHeader = new HttpHeaders();
        respondHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
      return new ResponseEntity<>(LabMapper.INSTANCE.getEventDTO(pageOutput.getContent()), respondHeader, HttpStatus.OK);
    }

    @GetMapping("events/{id}")
    public ResponseEntity<?> getEvent(@PathVariable("id") Long id) {
        Event output = eventService.getEvent(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getEventDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
    @PostMapping("/events")
    public ResponseEntity<?> addEvent(@RequestBody Event event){
        Event output = eventService.save(event);
        return ResponseEntity.ok(LabMapper.INSTANCE.getEventDTO( output));
    }
}


