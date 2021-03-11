import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Credit, CreditCreate} from "../models/credit";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CreditService {

  private BASE_CREDIT_URL = 'http://localhost:8080/api/v1/credit';

  constructor(private http: HttpClient) {
  }

  public createCredit(credit: CreditCreate): Observable<Credit> {
    return this.http.post<Credit>(this.BASE_CREDIT_URL, credit);
  }

  public updateCredit(newCredit: Credit): Observable<Credit> {
    return this.http.put<Credit>(this.BASE_CREDIT_URL, newCredit);
  }

  public getCredit(creditId: string): Observable<Credit> {
    return this.http.get<Credit>(this.BASE_CREDIT_URL + `\\${creditId}`);
  }

  public deleteCredit(creditId: string): Observable<void> {
    return this.http.delete<void>(this.BASE_CREDIT_URL + `\\${creditId}`);
  }

  public getAllCredits(): Observable<Credit[]> {
    return this.http.get<Credit[]>(this.BASE_CREDIT_URL);
  }
}
