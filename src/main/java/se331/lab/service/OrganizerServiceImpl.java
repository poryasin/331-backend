package se331.lab.service;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se331.lab.dao.EventDao;
import se331.lab.dao.OrganizerDao;
import se331.lab.entity.Event;
import se331.lab.entity.Organizer;
import se331.lab.repository.OrganizerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizerServiceImpl implements OrganizerService {
  final OrganizerDao organizerDao;
  final EventDao eventDao;


    @Override
    public List<Organizer> getAllOrganizer(){
        return organizerDao.getOrganizer(Pageable.unpaged()).getContent();
    }

    @Override
    public Page<Organizer> getOrganizer(Integer page, Integer pageSize){
        return organizerDao.getOrganizer(PageRequest.of(page, pageSize));
    }
    @Override
    public Organizer getOrganizerById(Long id) {
        return organizerDao.findById(id).orElseThrow(
                () -> new RuntimeException("Organizer not found with id " + id)
        );
    }

    @Override
    @Transactional
    public Organizer save(Organizer organizer) {
        if (organizer.getOwnEvents() != null) {
            List<Event> events = new ArrayList<>();
            for (Event event : organizer.getOwnEvents()) {
                // แก้ไข: ใช้ getEvent(id) ตามที่กำหนดใน EventDao
                Event existingEvent = eventDao.getEvent(event.getId());
                if (existingEvent != null) {
                    existingEvent.setOrganizer(organizer);
                    events.add(existingEvent);
                }
            }
            organizer.setOwnEvents(events);
        }
        return organizerDao.save(organizer);
    }

    @Override
    public Organizer getOrganize(Long id){
        return organizerDao.getOrganize(id);
    }



}