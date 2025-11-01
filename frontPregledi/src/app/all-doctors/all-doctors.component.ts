import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Doctor } from '../models/doctor';
import { DoctorService } from './doctor.service';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-all-doctors',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './all-doctors.component.html',
  styleUrl: './all-doctors.component.css'
})
export class AllDoctorsComponent implements OnInit{

  doctors: Doctor[] = [];
  loading = true;
  error: string | null = null;
  selectedDoctor: number | null = null;
  selectedDate: string = '';
  patientId: number | null = null;

  constructor(private doctorService: DoctorService, private http: HttpClient) {}

  ngOnInit(): void {
    this.doctorService.getAllDoctors().subscribe({
      next: (data) => {
        this.doctors = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Error while loading all-doctors.';
        this.loading = false;
      }
    });
  }

  selectDoctor(doctorId: number) {
    this.selectedDoctor = doctorId;
  }

  bookAppointment() {
    this.patientId = Number(localStorage.getItem('patientId') || null);
    
    if (!this.patientId || !this.selectedDoctor || !this.selectedDate) {
      alert('Please choose doctor and date.');
      return;
    }

    const payload = {
      patientId: this.patientId,
      doctorId: this.selectedDoctor,
      time: this.selectedDate
    };

    this.doctorService.bookAppointment(payload).subscribe({
      next: () => alert('Appointment')
    });
  }

}