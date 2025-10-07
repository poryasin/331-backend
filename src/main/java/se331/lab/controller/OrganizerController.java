package se331.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.entity.Event;
import se331.lab.entity.Organizer;
import se331.lab.service.OrganizerService;
import se331.lab.util.LabMapper;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class OrganizerController {
     final OrganizerService organizerService;


    @GetMapping("/organizers")
ResponseEntity<?> getOrganizers() {
    return ResponseEntity.ok(LabMapper.INSTANCE.getOrganizerDTO(organizerService.getAllOrganizer()));
    }

    @GetMapping("/organizers/{id}")
    ResponseEntity<?> getOrganizer(@PathVariable Long id) {
        Organizer output = organizerService.getOrganize(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getOrganizerDTO(output));
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The given id does not exist");
        }
    }


    @PostMapping("/organizers")
    public ResponseEntity<?> saveOrganizer(@RequestBody Organizer organizer) {
        Organizer saved = organizerService.save(organizer);
        return  ResponseEntity.ok(LabMapper.INSTANCE.getOrganizerDTO(saved));
    }


}




//    public ResponseEntity<List<Organizer>> getOrganizers(
//            @RequestParam(value = "_limit", required = false) Integer limit,
//            @RequestParam(value = "_page",  required = false) Integer page) {
//
//        int total = organizerService.getOrganizerCount();
//        Page<Organizer> pageOutput = organizerService.getOrganizers(limit, page);
//        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
//        return new ResponseEntity<>(pageOutput.getContent(), responseHeaders, HttpStatus.OK);
//    }
//
//    // GET /organizers/{id}
//    @GetMapping("/organizers/{id}")
//    public ResponseEntity<Organizer> getOrganizer(@PathVariable Long id) {
//        Organizer o = organizerService.getOrganizer(id);
//        if (o == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organizer id not found");
//        }
//        return ResponseEntity.ok(o);
//    }
//    @PostMapping("/organizers")
//    public ResponseEntity<Organizer> addOrganizer(@RequestBody Organizer organizer) {
//        Organizer saved = organizerService.saveOrganizer(organizer);
//        return ResponseEntity.ok(saved);
//    }
//}