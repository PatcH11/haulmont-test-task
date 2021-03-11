import {Component, OnInit} from '@angular/core';
import {Client} from "../../../models/client";
import {ClientService} from "../../../services/client.service";

@Component({
  selector: 'app-delete-client',
  templateUrl: './delete-client.component.html',
  styleUrls: ['./delete-client.component.css']
})
export class DeleteClientComponent implements OnInit {

  clients: Client[];
  clientId: string;

  constructor(private clientService: ClientService) {
  }

  ngOnInit(): void {
    this.getAllClients();
  }

  getAllClients() {
    this.clientService.getAllClients().subscribe(
      res => {
        this.clients = res;
      }, error => {
        console.log('gg');
      }
    );
  }

  deleteClient(id: string) {
    this.clientService.deleteClient(id).subscribe(
      res => {
        console.log('deleted');
      }, error => {
        console.log('gg');
      }
    )
  }

  onChange(newValue) {
    console.log(newValue);
    this.clientId = newValue;
  }
}
