package fin.kg.ac.rs.pregledi2025.rabbitMq;

import fin.kg.ac.rs.pregledi2025.dto.AppointmentDto;
import fin.kg.ac.rs.pregledi2025.service.AppointmentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentConsumer {

    @Autowired
    private final AppointmentService appointmentService;

    public AppointmentConsumer(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @RabbitListener(queues = "${queue.name}")
    public void receive(AppointmentDto appointmentDto) {
        appointmentService.confirmAppointment(appointmentDto);
    }
}