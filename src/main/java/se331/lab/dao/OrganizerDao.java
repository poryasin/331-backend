package se331.lab.dao;

import org.springframework.data.domain.Page;
import se331.lab.entity.Event;
import se331.lab.entity.Organizer;
import java.util.List;

public interface OrganizerDao {
    Integer getOrganizerCount();
   Page<Organizer> getOrganizers(Integer pageSize, Integer page);
    Organizer getOrganizer(Long id);
    Organizer saveOrganizer(Organizer organizer);
}