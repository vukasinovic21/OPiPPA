package fin.kg.ac.rs.pregledi2025.service;

import fin.kg.ac.rs.pregledi2025.model.Doctor;
import fin.kg.ac.rs.pregledi2025.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository){
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> findAll(){
        return doctorRepository.findAll();
    }
}
