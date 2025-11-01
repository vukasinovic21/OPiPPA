import { Routes } from '@angular/router';
import { AllPatientsComponent } from './all-patients/all-patients.component';
import { AllAppointmentsComponent } from './all-appointments/all-appointments.component';
import { AllDoctorsComponent } from './all-doctors/all-doctors.component';

export const routes: Routes = [
    { path: 'patients/all', component: AllPatientsComponent },
    { path: 'doctors/all', component: AllDoctorsComponent },
    { path: 'appointments/all', component: AllAppointmentsComponent },
    { path: '', redirectTo: 'doctors/all', pathMatch: 'full' },
];
