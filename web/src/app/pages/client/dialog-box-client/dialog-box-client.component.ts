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
