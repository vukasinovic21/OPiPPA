import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Doctor } from '../models/doctor';
import { environment } from '../environment/environment';

export interface AppointmentPayload {
  patientId: number;
  doctorId: number;
  time: string;
}

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  private apiUrl = environment.backUrl + '/doctors';

  constructor(private http: HttpClient) { }

  getAllDoctors(): Observable<Doctor[]>{
    return this.http.get<Doctor[]>(this.apiUrl + "/all");
  }

  bookAppointment(payload: AppointmentPayload): Observable<any> {
    return this.http.post(environment.backUrl + '/appointments/book', payload, { responseType: 'text' })
  }
}
