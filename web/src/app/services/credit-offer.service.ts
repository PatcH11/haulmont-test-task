import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CreditOffer, CreditOfferCreate, CreditOfferUpdate} from "../models/creditOffer";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CreditOfferService {

  constructor(private http: HttpClient) {
  }

  public createCreditOffer(creditOffer: CreditOfferCreate): Observable<CreditOffer> {
    return this.http.post<CreditOffer>(`/api/credit-offer`, creditOffer);
  }

  public updateCreditOffer(newCreditOffer: CreditOfferUpdate): Observable<CreditOffer> {
    return this.http.put<CreditOffer>(`/api/credit-offer`, newCreditOffer);
  }

  public getCreditOffer(creditOfferId: string): Observable<CreditOffer> {
    return this.http.get<CreditOffer>(`/api/credit-offer/${creditOfferId}`);
  }

  public deleteCreditOffer(creditOfferId: string): Observable<void> {
    return this.http.delete<void>(`/api/credit-offer/${creditOfferId}`);
  }

  public getAllCreditOffers(): Observable<CreditOffer[]> {
    return this.http.get<CreditOffer[]>(`/api/credit-offer`);
  }

  public getAllCreditOffersWhereClientIs(clientId: string): Observable<CreditOffer[]> {
    return this.http.get<CreditOffer[]>(`/api/credit-offer/all/${clientId}`);
  }
}
