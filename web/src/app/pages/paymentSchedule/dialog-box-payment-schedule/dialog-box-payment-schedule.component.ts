import {Component, Inject, OnInit, Optional} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {PaymentSchedule} from "../../../models/paymentSchedule";

@Component({
  selector: 'app-dialog-box-payment-schedule',
  templateUrl: './dialog-box-payment-schedule.component.html',
  styleUrls: ['./dialog-box-payment-schedule.component.css']
})
export class DialogBoxPaymentScheduleComponent implements OnInit {

  paymentSchedule: FormGroup;
  action: string;
  local_data: any;

  constructor(private formBuilder: FormBuilder,
              public dialogRef: MatDialogRef<DialogBoxPaymentScheduleComponent>,
              @Optional() @Inject(MAT_DIALOG_DATA) public data: PaymentSchedule) {
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
    this.paymentSchedule = this.formBuilder.group({
      id: this.formBuilder.control(undefined)
    });
  }
}
