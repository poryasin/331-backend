package se331.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se331.lab.dao.OrganizerDao;
import se331.lab.entity.Event;
import se331.lab.entity.Organizer;
import se331.lab.repository.OrganizerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizerServiceImpl implements OrganizerService {
    private final OrganizerDao organizerDao;
    private final OrganizerRepository organizerRepository;

    @Override
    public Integer getOrganizerCount() {
        return organizerDao.getOrganizerCount();
    }

    @Override
    public List<Organizer> getOrganizers(Integer pageSize, Integer page) {
        return organizerDao.getOrganizers(pageSize, page);
    }

    @Override
    public Organizer getOrganizer(Long id) {
        return organizerDao.getOrganizer(id);
    }

    @Override
    public Organizer saveOrganizer(Organizer organizer) {
        return organizerRepository.save(organizer);
    }

}