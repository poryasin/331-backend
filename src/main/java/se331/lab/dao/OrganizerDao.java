package se331.lab.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.entity.Event;
import se331.lab.entity.Organizer;
import java.util.List;
import java.util.Optional;

public interface OrganizerDao {
   Page<Organizer> getOrganizer(Pageable pageRequest);
   Optional<Organizer> findById(Long id);
    Organizer save(Organizer organizer);
    Organizer getOrganize (Long id);




}