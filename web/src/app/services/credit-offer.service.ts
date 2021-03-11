import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CreditOffer, CreditOfferCreate} from "../models/creditOffer";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CreditOfferService {

  private BASE_CREDIT_OFFER_URL = 'http://localhost:8080/api/v1/creditoffer';

  constructor(private http: HttpClient) {
  }

  public createCreditOffer(creditOffer: CreditOfferCreate): Observable<CreditOffer> {
    return this.http.post<CreditOffer>(this.BASE_CREDIT_OFFER_URL, creditOffer);
  }

  public updateCreditOffer(newCreditOffer: CreditOffer): Observable<CreditOffer> {
    return this.http.put<CreditOffer>(this.BASE_CREDIT_OFFER_URL, newCreditOffer);
  }

  public getCreditOffer(creditOfferId: string): Observable<CreditOffer> {
    return this.http.get<CreditOffer>(this.BASE_CREDIT_OFFER_URL + `\\${creditOfferId}`);
  }

  public deleteCreditOffer(creditOfferId: string): Observable<void> {
    return this.http.delete<void>(this.BASE_CREDIT_OFFER_URL + `${creditOfferId}`);
  }

  public getAllCreditOffers(): Observable<CreditOffer[]> {
    return this.http.get<CreditOffer[]>(this.BASE_CREDIT_OFFER_URL);
  }
}
