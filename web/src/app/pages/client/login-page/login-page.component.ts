import {Component, OnInit} from '@angular/core';
import {ClientService} from "../../../services/client.service";
import {Client} from "../../../models/client";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  clients: Client[] = [];

  constructor(private service: ClientService) {
  }

  ngOnInit(): void {
    this.getAllClients();
  }

  public getAllClients() {
    return this.service.getAllClients().subscribe(
      res => {
        this.clients = res;
      }, error => {
        console.log('gg');
      }
    )
  };

  public signIn() {

  }
}
