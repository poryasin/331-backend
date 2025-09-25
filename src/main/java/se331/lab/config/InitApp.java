package se331.lab.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import se331.lab.Application;
import se331.lab.entity.Event;
import se331.lab.entity.Organizer;
import se331.lab.repository.EventRepository;
import se331.lab.repository.OrganizerRepository;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final EventRepository eventRepository;
    final OrganizerRepository organizerRepository;
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        eventRepository.save(Event.builder()
                .category("Academic")
                .title("Midterm Exam")
                .description("A time for taking the exam")
                .location("CAMT Building")
                .date("3rd Sept")
                .time("3.00-4.00 pm.")
                .petAllowed(false)
                .organizer("CAMT").build());
        eventRepository.save(Event.builder()
                .category("Academic")
                .title("Commencement Day")
                .description("A time for celebration")
                .location("CAMT Convention hall")
                .date("21th Jan")
                .time("8.00am.-4.00 pm.")
                .petAllowed(false)
                .organizer("CAMT").build());
        eventRepository.save(Event.builder()
                .category("Cultural")
                .title("Loy Krathong")
                .description("A time for krathong")
                .location("Ping River")
                .date("21th Nov")
                .time("8.00-10.00 pm.")
                .petAllowed(false)
                .organizer("Chiangmai").build());
        eventRepository.save(Event.builder()
                .category("Cultural")
                .title("Songkran")
                .description("Let's play water")
                .location("Chiangmai Moat")
                .date("13th April")
                .time("10.00am.-6.00 pm.")
                .petAllowed(true)
                .organizer("Chiangmai Municipality").build());
        organizerRepository.save(Organizer.builder()
                .organizationName("CAMT")
                .address("123 A Street")
                .build());

        organizerRepository.save(Organizer.builder()
                .organizationName("CMU")
                .address("456 B Avenue")
                .build());

        organizerRepository.save(Organizer.builder()
                .organizationName("Chiang Mai")
                .address("789 C Road")
                .build());

        organizerRepository.save(Organizer.builder()
                .organizationName("Chiang Mai Municipality")
                .address("101 D Lane")
                .build());
    }

}
