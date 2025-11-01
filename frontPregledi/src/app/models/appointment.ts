import { Doctor } from "./doctor"
import { Patient } from "./patient"

export interface Appointment 
{
    id: number,
    patient: Patient,
    doctor: Doctor
    time: Date,
    status: string
}