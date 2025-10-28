package fin.kg.ac.rs.pregledi2025.controller;

import fin.kg.ac.rs.pregledi2025.dto.AppointmentDto;
import fin.kg.ac.rs.pregledi2025.model.Appointment;
import fin.kg.ac.rs.pregledi2025.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }

    @GetMapping("/all")
    public List<Appointment> findAllAppointments(){
        return appointmentService.findAll();
    }

    @PostMapping("/book")
    public ResponseEntity<String> bookAppointment(@RequestBody AppointmentDto request){
        boolean success = appointmentService.bookAppointment(request);
        if(success) {
            return ResponseEntity.ok("Appointment CONFIRMED!");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Doctor is already booked at this time.");
        }
    }
}
