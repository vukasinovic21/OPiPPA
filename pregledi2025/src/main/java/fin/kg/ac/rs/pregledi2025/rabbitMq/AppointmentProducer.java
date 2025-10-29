package fin.kg.ac.rs.pregledi2025.rabbitMq;

import fin.kg.ac.rs.pregledi2025.dto.AppointmentDto;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AppointmentProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${queue.name}")
    private String queueName;

    public AppointmentProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(AppointmentDto appointmentDto) {
        rabbitTemplate.convertAndSend(queueName, appointmentDto);
    }
}