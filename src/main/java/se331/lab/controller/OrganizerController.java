package se331.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se331.lab.entity.Event;
import se331.lab.entity.Organizer;
import se331.lab.service.OrganizerService;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequiredArgsConstructor

public class OrganizerController {
    private final OrganizerService organizerService;

    // GET /organizers?_limit=2&_page=1
    @GetMapping("/organizers")
    public ResponseEntity<List<Organizer>> getOrganizers(
            @RequestParam(value = "_limit", required = false) Integer limit,
            @RequestParam(value = "_page",  required = false) Integer page) {

        int total = organizerService.getOrganizerCount();
        Page<Organizer> pageOutput = organizerService.getOrganizers(limit, page);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(pageOutput.getContent(), responseHeaders, HttpStatus.OK);
    }

    // GET /organizers/{id}
    @GetMapping("/organizers/{id}")
    public ResponseEntity<Organizer> getOrganizer(@PathVariable Long id) {
        Organizer o = organizerService.getOrganizer(id);
        if (o == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organizer id not found");
        }
        return ResponseEntity.ok(o);
    }
    @PostMapping("/organizers")
    public ResponseEntity<Organizer> addOrganizer(@RequestBody Organizer organizer) {
        Organizer saved = organizerService.saveOrganizer(organizer);
        return ResponseEntity.ok(saved);
    }
}