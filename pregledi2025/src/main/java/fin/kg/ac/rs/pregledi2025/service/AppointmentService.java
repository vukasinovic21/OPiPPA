package fin.kg.ac.rs.pregledi2025.service;

import fin.kg.ac.rs.pregledi2025.dto.AppointmentDto;
import fin.kg.ac.rs.pregledi2025.model.Appointment;
import fin.kg.ac.rs.pregledi2025.model.Doctor;
import fin.kg.ac.rs.pregledi2025.model.Patient;
import fin.kg.ac.rs.pregledi2025.model.Status;
import fin.kg.ac.rs.pregledi2025.repository.AppointmentRepository;
import fin.kg.ac.rs.pregledi2025.repository.DoctorRepository;
import fin.kg.ac.rs.pregledi2025.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              PatientRepository patientRepository,
                              DoctorRepository doctorRepository){
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }

    public boolean bookAppointment(AppointmentDto request){
        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        boolean exists = appointmentRepository.existsByDoctorAndTime(doctor, request.getTime());
        if (exists) return false;

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setTime(request.getTime());
        appointment.setStatus(Status.PENDING);

        appointmentRepository.save(appointment);

        return true;
    }
}
