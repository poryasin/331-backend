package se331.lab.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Organizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String organizationName;
    String address;
}