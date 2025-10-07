package se331.lab.config;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import se331.lab.entity.Event;
import se331.lab.entity.Organizer;
import se331.lab.entity.Participant;
import se331.lab.repository.EventRepository;
import se331.lab.repository.OrganizerRepository;
import se331.lab.repository.ParticipantRepository;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final EventRepository eventRepository;
    final OrganizerRepository organizerRepository;
    final ParticipantRepository participantRepository;


    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
            Organizer org1,org2,org3;
            org1 = organizerRepository.save(Organizer.builder().name("CAMT").build());
            org2 = organizerRepository.save(Organizer.builder().name("CMU").build());
            org3 = organizerRepository.save(Organizer.builder().name("Chaingmai").build());

            Event tempEvent;
            Event event1 , event2 , event3 ;
            tempEvent = eventRepository.save(Event.builder()
                    .category("Academic")
                    .title("Midterm Exam")
                    .description("A time for taking the exam")
                    .location("CAMT Building")
                    .date("3rd Sept")
                    .time("3.00-4.00 pm.")
                    .petAllowed(false)
                    .build());

            tempEvent.setOrganizer(org1);
            org1.getOwnEvents().add(tempEvent);
            event1 = tempEvent;
            tempEvent = eventRepository.save(Event.builder()
                .category("Academic")
                .title("Commencement Day")
                .description("A time for celebration")
                .location("CAMT Convention hall")
                .date("21th Jan")
                .time("8.00am.-4.00 pm.")
                .petAllowed(false)
                .build());

        tempEvent.setOrganizer(org1);
        org2.getOwnEvents().add(tempEvent);
        event2 = tempEvent;
        tempEvent = eventRepository.save(Event.builder()
                .category("Cultural")
                .title("Loy Krathong")
                .description("A time for krathong")
                .location("Ping River")
                .date("21th Nov")
                .time("8.00-10.00 pm.")
                .petAllowed(false)
                .build());
        tempEvent.setOrganizer(org3);
        org3.getOwnEvents().add(tempEvent);
        event3 = tempEvent;

        Participant p1, p2, p3, p4, p5;
        p1 = participantRepository.save(Participant.builder().name("porya").telNo("111-111-1111").build());
        p2 = participantRepository.save(Participant.builder().name("jig").telNo("222-222-2222").build());
        p3 = participantRepository.save(Participant.builder().name("stamp").telNo("333-333-3333").build());
        p4 = participantRepository.save(Participant.builder().name("tle").telNo("444-444-4444").build());
        p5 = participantRepository.save(Participant.builder().name("win").telNo("555-555-5555").build());


        addParticipantToEvent(p1, event1);
        addParticipantToEvent(p2, event2);
        addParticipantToEvent(p3, event3);

        addParticipantToEvent(p4, event1);
        addParticipantToEvent(p4, event2);
        addParticipantToEvent(p4, event3);

        addParticipantToEvent(p5, event1);
        addParticipantToEvent(p5, event2);
        addParticipantToEvent(p5, event3);

    }

    private void addParticipantToEvent(Participant participant, Event event) {
        // 1. อัปเดตฝั่ง Participant: เพิ่ม Event เข้าไปใน eventHistory
        if (participant.getEventHistory() == null) {
            participant.setEventHistory(new ArrayList<>());
        }
        participant.getEventHistory().add(event);

        // 2. อัปเดตฝั่ง Event: เพิ่ม Participant เข้าไปใน participant list
        if (event.getParticipants() == null) { // ใช้ .getParticipant() ตามชื่อ Field ใน Event Entity ของคุณ
            event.setParticipants(new ArrayList<>());
        }
        event.getParticipants().add(participant);

        // 3. บันทึกการเปลี่ยนแปลง (จำเป็นสำหรับ Many-to-Many)
        participantRepository.save(participant);
        eventRepository.save(event);
    }
}

