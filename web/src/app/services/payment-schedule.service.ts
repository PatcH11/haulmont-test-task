import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {PaymentSchedule, PaymentScheduleCreate} from "../models/paymentSchedule";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PaymentScheduleService {

  constructor(private http: HttpClient) {
  }

  public createPaymentSchedule(paymentSchedule: PaymentScheduleCreate): Observable<PaymentSchedule> {
    return this.http.post<PaymentSchedule>(`/api/payment-schedule`, paymentSchedule);
  }

  public getPaymentSchedule(paymentScheduleId: string): Observable<PaymentSchedule> {
    return this.http.get<PaymentSchedule>(`/api/payment-schedule/${paymentScheduleId}`);
  }

  public getAllPaymentSchedules(): Observable<PaymentSchedule[]> {
    return this.http.get<PaymentSchedule[]>(`/api/payment-schedule`);
  }

  public getAllPaymentSchedulesWhereClientAndCreditIs(clientId: string, creditId: string): Observable<PaymentSchedule[]> {
    return this.http.get<PaymentSchedule[]>(`/api/payment-schedule/all/${clientId}/${creditId}`);
  }
}
