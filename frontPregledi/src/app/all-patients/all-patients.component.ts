import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Patient } from '../models/patient';
import { PatientService } from './patient.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-all-patients',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './all-patients.component.html',
  styleUrl: './all-patients.component.css'
})
export class AllPatientsComponent implements OnInit{

  patients: Patient[] = [];
  loading = true;
  error: string | null = null;

  constructor(private patientService: PatientService, private router: Router) {}

  ngOnInit(): void {
    this.patientService.getAllPatients().subscribe({
      next: (data) => {
        this.patients = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Error while loading all-patients.';
        this.loading = false;
      }
    });
  }

  selectPatient(id: number): void {
    localStorage.setItem('patientId', id.toString());
    this.router.navigate(['/doctors/all']);
  }
}