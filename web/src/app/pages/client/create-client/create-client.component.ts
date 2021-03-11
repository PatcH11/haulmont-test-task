import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ClientService} from "../../../services/client.service";
import {Client} from "../../../models/client";

@Component({
  selector: 'app-create-client',
  templateUrl: './create-client.component.html',
  styleUrls: ['./create-client.component.css']
})
export class CreateClientComponent implements OnInit {

  clientForm: FormGroup;

  client: Client;

  constructor(
    private formBuilder: FormBuilder,
    private clientService: ClientService
  ) {
  }

  ngOnInit(): void {
    this.buildForm();
  }

  createClient(clientForm: FormGroup) {
    let client = {
      firstName: clientForm.value.firstName,
      lastName: clientForm.value.lastName,
      patronymic: clientForm.value.patronymic,
      phoneNumber: clientForm.value.phoneNumber,
      email: clientForm.value.email,
      passportNumber: 141241
    } as Client;

    this.clientService.createClient(client).subscribe(
      res => {
        console.log(res);
      }, error => {
        console.log('bad');
      }
    );
  }

  getErrorLastNameMessage() {
    if (this.clientForm.controls['lastName'].hasError('required')) {
      return 'Вы должны ввести значение';
    }

    return this.clientForm.controls['lastName'].hasError('lastName') ? 'Not a valid email' : '';
  }

  getErrorFirstNameMessage() {
    if (this.clientForm.controls['firstName'].hasError('required')) {
      return 'Вы должны ввести значение';
    }

    return this.clientForm.controls['firstName'].hasError('firstName') ? 'Not a valid email' : '';
  }

  getErrorPatronymicMessage() {
    if (this.clientForm.controls['patronymic'].hasError('required')) {
      return 'Вы должны ввести значение';
    }

    return this.clientForm.controls['patronymic'].hasError('patronymic') ? 'Not a valid email' : '';
  }

  getErrorPhoneNumberMessage() {
    if (this.clientForm.controls['phoneNumber'].hasError('required')) {
      return 'Вы должны ввести значение';
    }

    return this.clientForm.controls['phoneNumber'].hasError('phoneNumber') ? 'Not a valid email' : '';
  }

  getErrorEmailMessage() {
    if (this.clientForm.controls['email'].hasError('required')) {
      return 'Вы должны ввести значение';
    }

    return this.clientForm.controls['email'].hasError('email') ? 'Not a valid email' : '';
  }

  getErrorPassportNumberMessage() {
    if (this.clientForm.controls['passportNumber'].hasError('required')) {
      return 'Вы должны ввести значение';
    }

    return this.clientForm.controls['passportNumber'].hasError('passportNumber') ? 'Not a valid email' : '';
  }

  private buildForm() {
    this.clientForm = this.formBuilder.group({
      firstName: this.formBuilder.control(undefined, [Validators.required, Validators.pattern("^[А-Яа-я]+$")]),
      lastName: this.formBuilder.control(undefined, [Validators.required, Validators.pattern("^[А-Яа-я]+$")]),
      patronymic: this.formBuilder.control(undefined, [Validators.required, Validators.pattern("^[А-Яа-я]+$")]),
      phoneNumber: this.formBuilder.control(undefined, [Validators.required, Validators.pattern("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$")]),
      email: this.formBuilder.control(undefined, [Validators.required, Validators.email]),
      passportNumber: this.formBuilder.control(undefined, [Validators.required, Validators.pattern("^\\d+$")])
    });
  }
}
