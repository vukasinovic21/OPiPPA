import { Injectable } from '@angular/core';
import { environment } from '../environment/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Patient } from '../models/patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  private apiUrl = environment.backUrl + '/patients';

  constructor(private http: HttpClient) { }

  getAllPatients(): Observable<Patient[]>{
    return this.http.get<Patient[]>(this.apiUrl + "/all");
  }
}
