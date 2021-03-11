import {Component, Inject, OnInit, Optional} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Client} from "../../../models/client";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-dialog-box-client',
  templateUrl: './dialog-box-client.component.html',
  styleUrls: ['./dialog-box-client.component.css']
})
export class DialogBoxClientComponent implements OnInit {

  clientForm: FormGroup;
  action: string;
  local_data: any;

  constructor(
    private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<DialogBoxClientComponent>,
    //@Optional() is used to prevent error if no data is passed
    @Optional() @Inject(MAT_DIALOG_DATA) public data: Client) {
    console.log(data);
    this.local_data = {...data};
    this.action = this.local_data.action;
  }

  ngOnInit(): void {
    this.buildForm();
  }

  doAction() {
    this.dialogRef.close({event: this.action, data: this.local_data});
  }

  closeDialog() {
    this.dialogRef.close({event: 'Cancel'});
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
