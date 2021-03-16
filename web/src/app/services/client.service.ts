import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Client, ClientCreate, ClientUpdate} from "../models/client";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http: HttpClient) {
  }

  public createClient(client: ClientCreate): Observable<Client> {
    return this.http.post<Client>(`/api/client`, client)
  }

  public updateClient(newClient: ClientUpdate): Observable<Client> {
    return this.http.put<Client>(`/api/client`, newClient);
  }

  public getClient(clientId: string): Observable<Client> {
    return this.http.get<Client>(`/api/client/${clientId}`);
  }

  public deleteClient(clientId: string): Observable<void> {
    return this.http.delete<void>(`/api/client/${clientId}`);
  }

  public getAllClients(): Observable<Client[]> {
    return this.http.get<Client[]>(`/api/client`);
  }

  public getAllClientsWhereCreditsNotContains(creditId: string): Observable<Client[]> {
    return this.http.get<Client[]>(`/api/client/all/not/${creditId}`);
  }
}
