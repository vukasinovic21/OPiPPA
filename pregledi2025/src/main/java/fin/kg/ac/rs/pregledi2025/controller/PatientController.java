package fin.kg.ac.rs.pregledi2025.controller;

import fin.kg.ac.rs.pregledi2025.model.Patient;
import fin.kg.ac.rs.pregledi2025.service.PatientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/all")
    public List<Patient> findAllPatients(){
        return patientService.findAll();
    }
}
