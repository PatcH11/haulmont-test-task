import {Component, Inject, OnInit, Optional} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Credit} from "../../../models/credit";

@Component({
  selector: 'app-dialog-box-credit',
  templateUrl: './dialog-box-credit.component.html',
  styleUrls: ['./dialog-box-credit.component.css']
})
export class DialogBoxCreditComponent implements OnInit {

  creditForm: FormGroup;
  action: string;
  local_data: any;

  constructor(
    private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<DialogBoxCreditComponent>,
    //@Optional() is used to prevent error if no data is passed
    @Optional() @Inject(MAT_DIALOG_DATA) public data: Credit) {
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
    this.creditForm = this.formBuilder.group({
      name: this.formBuilder.control(undefined, [Validators.required, Validators.pattern("^[А-Яа-я]+$")]),
      loanLimit: this.formBuilder.control(undefined, [Validators.required, Validators.pattern("^\\d+$"), Validators.min(100000), Validators.max(10000000)]),
      interestRate: this.formBuilder.control(undefined, [Validators.required, Validators.pattern("^\\d+$"), Validators.min(1), Validators.max(100), Validators.minLength(1), Validators.maxLength(3)])
    });
  }
}
