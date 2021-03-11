import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {PaymentSchedule} from "../models/paymentSchedule";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PaymentScheduleService {

  private BASE_PAYMENT_SCHEDULE_URL = 'http://localhost:8080/api/v1/paymentschedule';

  constructor(private http: HttpClient) {
  }

  public createPaymentSchedule(paymentSchedule: PaymentSchedule): Observable<PaymentSchedule> {
    return this.http.post<PaymentSchedule>(this.BASE_PAYMENT_SCHEDULE_URL, paymentSchedule);
  }

  public updatePaymentSchedule(newPaymentSchedule: PaymentSchedule): Observable<PaymentSchedule> {
    return this.http.put<PaymentSchedule>(this.BASE_PAYMENT_SCHEDULE_URL, newPaymentSchedule);
  }

  public getPaymentSchedule(paymentScheduleId: string): Observable<PaymentSchedule> {
    return this.http.get<PaymentSchedule>(this.BASE_PAYMENT_SCHEDULE_URL + `\\${paymentScheduleId}`);
  }

  public deletePaymentSchedule(paymentScheduleId: string): Observable<void> {
    return this.http.delete<void>(this.BASE_PAYMENT_SCHEDULE_URL + `${paymentScheduleId}`);
  }

  public getAllPaymentSchedules(): Observable<PaymentSchedule[]> {
    return this.http.get<PaymentSchedule[]>(this.BASE_PAYMENT_SCHEDULE_URL);
  }
}
