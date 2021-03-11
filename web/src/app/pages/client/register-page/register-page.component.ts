import {Component, OnInit} from '@angular/core';
import {Client} from "../../../models/client";
import {ClientService} from "../../../services/client.service";
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {

  form: FormGroup;
  model: Client = {
    id: '',
    firstName: '',
    lastName: '',
    patronymic: '',
    phoneNumber: '',
    email: '',
    passportNumber: 0
  };

  constructor(private service: ClientService) {
  }

  ngOnInit(): void {
  }

  register(): void {
    this.service.createClient(this.model).subscribe(
      res => {
        console.log('успешно');
      }, error => {
        console.log('неуспешно');
      }
    );
  }
}
