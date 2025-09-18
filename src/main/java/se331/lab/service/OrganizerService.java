package se331.lab.service;

import se331.lab.entity.Event;
import se331.lab.entity.Organizer;
import java.util.List;

public interface OrganizerService {
    Integer getOrganizerCount();
    List<Organizer> getOrganizers(Integer pageSize, Integer page);
    Organizer getOrganizer(Long id);
    Organizer saveOrganizer(Organizer organizer);
}