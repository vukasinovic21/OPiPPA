package fin.kg.ac.rs.pregledi2025.repository;

import fin.kg.ac.rs.pregledi2025.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
