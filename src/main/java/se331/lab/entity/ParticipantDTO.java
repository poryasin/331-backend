package se331.lab.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDTO {
    Long id;
    String name;
    String telNo;

    // ✅ เพิ่มความสัมพันธ์ Many-to-Many กับ Event
    // ต้องใช้ DTO ของ Event ที่มีการตัดความสัมพันธ์แบบวงจร (Circular Reference) ออกไปแล้ว
    List<EventParticipantDTO> eventHistory;
}