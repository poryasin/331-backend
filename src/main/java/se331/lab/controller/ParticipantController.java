package se331.lab.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se331.lab.service.ParticipantService;
import se331.lab.util.LabMapper; // ต้องใช้ LabMapper

@RestController
@RequiredArgsConstructor
public class ParticipantController {

    // Spring จะ Autowire Service Layer เข้ามาให้
    private final ParticipantService participantService;

    @GetMapping("/participants")
    ResponseEntity<?> getParticipants() {
        // 1. เรียก Service เพื่อดึง Entity List
        // 2. ใช้ Mapper แปลง Entity List เป็น DTO List
        // 3. ส่ง DTO List กลับไปพร้อมสถานะ 200 OK
        return ResponseEntity.ok(LabMapper.INSTANCE.getParticipantDTO(participantService.getAllParticipants()
                )
        );
    }
}