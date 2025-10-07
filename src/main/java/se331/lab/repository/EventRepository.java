package se331.lab.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se331.lab.entity.Event;

import java.util.List;
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAll();
    Page<Event> findByTitle(String title, Pageable pageable);
    Page<Event> findByTitleContaining(String title, Pageable pageRequest);
    Page<Event> findByTitleContainingOrDescriptionContaining(String title, String description, Pageable pageRequest);
    Page<Event> findByTitleContainingAndDescriptionContaining(String title, String description, Pageable pageRequest);
    Page<Event> findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContainingOrOrganizerIgnoreCaseContaining(
            String title, String description, String organizerName, Pageable pageRequest
    );
//    Page<Event> findByTitleContainingOrDescriptionContainingOrOrganizerContaining(
//            String title, String description, String organizerName, Pageable pageRequest
//    );
}



