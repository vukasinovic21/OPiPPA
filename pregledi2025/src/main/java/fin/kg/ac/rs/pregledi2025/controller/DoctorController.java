package fin.kg.ac.rs.pregledi2025.controller;

import fin.kg.ac.rs.pregledi2025.model.Doctor;
import fin.kg.ac.rs.pregledi2025.service.DoctorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/all")
    public List<Doctor> findAllDooctors(){
        return doctorService.findAll();
    }
}
