package fin.kg.ac.rs.pregledi2025.service;

import fin.kg.ac.rs.pregledi2025.model.Patient;
import fin.kg.ac.rs.pregledi2025.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    public List<Patient> findAll(){
        return patientRepository.findAll();
    }
}
