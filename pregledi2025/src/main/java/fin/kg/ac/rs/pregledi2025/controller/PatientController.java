package fin.kg.ac.rs.pregledi2025.controller;

import fin.kg.ac.rs.pregledi2025.model.Patient;
import fin.kg.ac.rs.pregledi2025.repository.PatientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping
    public List<Patient> findAll(){
        return patientRepository.findAll();
    }
}
