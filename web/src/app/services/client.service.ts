import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Client, ClientCreate} from "../models/client";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private BASE_CLIENT_URL = 'http://localhost:8080/api/v1/client';

  constructor(private http: HttpClient) {
  }

  public createClient(client: ClientCreate): Observable<Client> {
    return this.http.post<Client>(this.BASE_CLIENT_URL, client)
  }

  public updateClient(newClient: Client): Observable<Client> {
    return this.http.put<Client>(this.BASE_CLIENT_URL, newClient);
  }

  public getClient(clientId: string): Observable<Client> {
    return this.http.get<Client>(this.BASE_CLIENT_URL + `\\${clientId}`);
  }

  public deleteClient(clientId: string): Observable<void> {
    return this.http.delete<void>(this.BASE_CLIENT_URL + `\\${clientId}`);
  }

  public getAllClients(): Observable<Client[]> {
    return this.http.get<Client[]>(this.BASE_CLIENT_URL);
  }
}
