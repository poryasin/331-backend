package se331.lab.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.entity.Event;
import se331.lab.entity.Organizer;
import java.util.List;

public interface OrganizerService {
   List<Organizer> getAllOrganizer();
   Page<Organizer> getOrganizer(Integer page,Integer pageSize);
    Organizer getOrganizerById(Long id);

    public Organizer save(Organizer organizer);
    Organizer getOrganize(Long id);

}