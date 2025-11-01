import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Appointment } from '../models/appointment';
import { AppointmentService } from './appointment.service';

@Component({
  selector: 'app-all-appointments',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './all-appointments.component.html',
  styleUrl: './all-appointments.component.css'
})
export class AllAppointmentsComponent implements OnInit{

  appointments: Appointment[] = [];
  loading = true;
  error: string | null = null;

  constructor(private appointmentsService: AppointmentService) {}

  ngOnInit(): void {
    this.appointmentsService.getAllAppointments().subscribe({
      next: (data) => {
        this.appointments = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Error while loading all-appointments.';
        this.loading = false;
      }
    });
  }
}