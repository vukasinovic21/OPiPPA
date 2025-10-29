package fin.kg.ac.rs.pregledi2025.repository;

import fin.kg.ac.rs.pregledi2025.model.Appointment;
import fin.kg.ac.rs.pregledi2025.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByDoctorAndTime(Doctor doctor, LocalDateTime time);
    Optional<Appointment> findByPatientIdAndDoctorIdAndTime(Long patientId, Long doctorId, LocalDateTime time);

}
