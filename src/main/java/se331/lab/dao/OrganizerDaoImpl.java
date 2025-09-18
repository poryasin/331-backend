package se331.lab.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import se331.lab.entity.Event;
import se331.lab.entity.Organizer;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrganizerDaoImpl implements OrganizerDao {
    private List<Organizer> organizerList;

    @PostConstruct
    public void init() {
        organizerList = new ArrayList<>();
        organizerList.add(Organizer.builder().id(1L).organizationName("Kat Foundation").address("Meow Town").build());
        organizerList.add(Organizer.builder().id(2L).organizationName("Green Garden Club").address("Flora City").build());
        organizerList.add(Organizer.builder().id(3L).organizationName("Ocean Care").address("Playa Del Carmen").build());
        organizerList.add(Organizer.builder().id(4L).organizationName("Highway Helpers").address("Highway 50").build());
        organizerList.add(Organizer.builder().id(5L).organizationName("Community Kitchen").address("Tin City").build());
        organizerList.add(Organizer.builder().id(6L).organizationName("Dog Rescue Org").address("Woof Town").build());
    }

    @Override
    public Integer getOrganizerCount() {
        return organizerList.size();
    }

    @Override
    public List<Organizer> getOrganizers(Integer pageSize, Integer page) {
        int total = organizerList.size();
        int size = (pageSize == null || pageSize <= 0) ? total : pageSize;
        int p = (page == null || page <= 0) ? 1 : page;

        int from = Math.min((p - 1) * size, total);
        int to   = Math.min(from + size, total);

        return new ArrayList<>(organizerList.subList(from, to));
    }

    @Override
    public Organizer getOrganizer(Long id) {
        for (Organizer o : organizerList) {
            if (o.getId().equals(id)) return o;
        }
        return null;
    }

    @Override
    public Organizer saveOrganizer(Organizer organizer) {
        Long newId = organizerList.isEmpty() ? 1L : organizerList.get(organizerList.size() - 1).getId() + 1;
        organizer.setId(newId);
        organizerList.add(organizer);
        return organizer;
    }


}