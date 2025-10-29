package fin.kg.ac.rs.pregledi2025.service;

import fin.kg.ac.rs.pregledi2025.config.RabbitConfig;
import fin.kg.ac.rs.pregledi2025.dto.AppointmentDto;
import fin.kg.ac.rs.pregledi2025.model.Appointment;
import fin.kg.ac.rs.pregledi2025.model.Doctor;
import fin.kg.ac.rs.pregledi2025.model.Patient;
import fin.kg.ac.rs.pregledi2025.model.Status;
import fin.kg.ac.rs.pregledi2025.rabbitMq.AppointmentProducer;
import fin.kg.ac.rs.pregledi2025.repository.AppointmentRepository;
import fin.kg.ac.rs.pregledi2025.repository.DoctorRepository;
import fin.kg.ac.rs.pregledi2025.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentProducer appointmentProducer;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              PatientRepository patientRepository,
                              DoctorRepository doctorRepository,
                              AppointmentProducer appointmentProducer){
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentProducer = appointmentProducer;
    }

    public List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }

    public void bookAppointment(AppointmentDto request) {
        appointmentProducer.send(request);
    }

    public void confirmAppointment(AppointmentDto request){
        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Appointment appointment = appointmentRepository
                .findByPatientIdAndDoctorIdAndTime(request.getPatientId(), request.getDoctorId(), request.getTime())
                .orElseGet(() -> {
                    Appointment a = new Appointment();
                    a.setPatient(patient);
                    a.setDoctor(doctor);
                    a.setTime(request.getTime());
                    a.setStatus(Status.PENDING);
                    return a;
                });

        boolean exists = appointmentRepository.existsByDoctorAndTime(doctor, request.getTime());

        if (exists && (appointment.getStatus() == Status.PENDING || appointment.getStatus() == Status.REJECTED)) {
            appointment.setStatus(Status.REJECTED);
        } else {
            appointment.setStatus(Status.CONFIRMED);
        }

        appointmentRepository.save(appointment);
    }
}
