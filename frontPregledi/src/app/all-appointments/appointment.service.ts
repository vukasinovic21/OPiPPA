import { Injectable } from '@angular/core';
import { environment } from '../environment/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Appointment } from '../models/appointment';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  private apiUrl = environment.backUrl + '/appointments';

  constructor(private http: HttpClient) { }

  getAllAppointments(): Observable<Appointment[]>{
    return this.http.get<Appointment[]>(this.apiUrl + "/all");
  }
}
