import {Component, OnInit, ViewChild} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {MatTable} from "@angular/material/table";
import {DialogBoxClientComponent} from "../dialog-box-client/dialog-box-client.component";
import {ClientService} from "../../../services/client.service";
import {Client, ClientCreate} from "../../../models/client";

@Component({
  selector: 'app-client-page',
  templateUrl: './client-page.component.html',
  styleUrls: ['./client-page.component.css']
})
export class ClientPageComponent implements OnInit {

  displayedColumns: string[] = ['lastName', 'firstName', 'patronymic', 'phoneNumber', 'email', 'passportNumber', 'action'];
  dataSource: Client[];

  @ViewChild(MatTable, {static: true}) table: MatTable<any>;

  constructor(
    public dialog: MatDialog,
    private clientService: ClientService
  ) {
  }

  ngOnInit(): void {
    this.getAllClients();
  }

  getAllClients() {
    this.clientService.getAllClients().subscribe(
      res => {
        this.dataSource = res;
      }, error => {
        console.log('Клиенты не загрузились!');
      }
    );
  }

  openDialog(action, obj) {
    obj.action = action;
    const dialogRef = this.dialog.open(DialogBoxClientComponent, {
      width: '350px',
      data: obj
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.event == 'Add') {
        this.addRowData(result.data);
      } else if (result.event == 'Update') {
        this.updateRowData(result.data);
      } else if (result.event == 'Delete') {
        this.deleteRowData(result.data);
      }
    });
  }

  addRowData(row_obj) {
    let client = {
      firstName: row_obj.firstName,
      lastName: row_obj.lastName,
      patronymic: row_obj.patronymic,
      phoneNumber: row_obj.phoneNumber,
      email: row_obj.email,
      passportNumber: row_obj.passportNumber
    } as ClientCreate;

    this.clientService.createClient(client).subscribe(
      res => {
        console.log('Клиент добавлен!');
      }, error => {
        console.log(client);
        console.log('Клиент не добавлен!');
      }
    );

    window.location.reload();
  }

  updateRowData(row_obj) {
    let updateClient = {
      id: row_obj.id,
      firstName: row_obj.firstName,
      lastName: row_obj.lastName,
      patronymic: row_obj.patronymic,
      phoneNumber: row_obj.phoneNumber,
      email: row_obj.email,
      passportNumber: row_obj.passportNumber
    } as Client;

    this.clientService.updateClient(updateClient).subscribe(
      res => {
        console.log('Коиент обновлен!');
        return true;
      }, error => {
        console.log('Коиент не обновлен!');
        return false;
      }
    );

    window.location.reload();
  }

  deleteRowData(row_obj) {
    this.clientService.deleteClient(row_obj.id).subscribe(
      res => {
        console.log('Клиент удален!');
        return true;
      }, error => {
        console.log('Клиент не удален!');
        return false;
      }
    );

    window.location.reload();
  }
}
