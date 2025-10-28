package fin.kg.ac.rs.pregledi2025.repository;

import fin.kg.ac.rs.pregledi2025.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
