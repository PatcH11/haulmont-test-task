import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Credit, CreditCreate, CreditUpdate} from "../models/credit";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CreditService {

  constructor(private http: HttpClient) {
  }

  public createCredit(credit: CreditCreate): Observable<Credit> {
    return this.http.post<Credit>(`/api/credit`, credit);
  }

  public updateCredit(newCredit: CreditUpdate): Observable<Credit> {
    return this.http.put<Credit>(`/api/credit`, newCredit);
  }

  public getCredit(creditId: string): Observable<Credit> {
    return this.http.get<Credit>(`/api/credit/${creditId}`);
  }

  public deleteCredit(creditId: string): Observable<void> {
    return this.http.delete<void>(`/api/credit/${creditId}`);
  }

  public getAllCredits(): Observable<Credit[]> {
    return this.http.get<Credit[]>(`/api/credit`);
  }

  public getAllCreditsWhereClientIs(clientId: string): Observable<Credit[]> {
    return this.http.get<Credit[]>(`/api/credit/all/${clientId}`);
  }
}
