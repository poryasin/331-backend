package se331.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.lab.entity.*;
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}