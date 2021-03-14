import {Component, Inject, OnInit, Optional} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {CreditOffer} from "../../../models/creditOffer";
import {Client} from "../../../models/client";
import {Credit} from "../../../models/credit";
import {ClientService} from "../../../services/client.service";
import {CreditService} from "../../../services/credit.service";

@Component({
  selector: 'app-dialog-box-credit-offer',
  templateUrl: './dialog-box-credit-offer.component.html',
  styleUrls: ['./dialog-box-credit-offer.component.css']
})
export class DialogBoxCreditOfferComponent implements OnInit {

  creditOfferForm: FormGroup;
  action: string;
  local_data: any;

  clients: Client[];
  credits: Credit[];

  constructor(private formBuilder: FormBuilder,
              public dialogRef: MatDialogRef<DialogBoxCreditOfferComponent>,
              private clientService: ClientService,
              private creditService: CreditService,
              @Optional() @Inject(MAT_DIALOG_DATA) public data: CreditOffer) {
    console.log(data);
    this.local_data = {...data};
    this.action = this.local_data.action;
  }

  get client() {
    return this.creditOfferForm.get('client') as FormControl;
  }

  get creditAmount() {
    return this.creditOfferForm.get('creditAmount') as FormControl;
  }

  ngOnInit(): void {

    this.getAllClients();
    this.getAllCredits();

    this.creditOfferForm = new FormGroup({
      client: new FormControl(undefined, [Validators.required]),
      credit: new FormControl(undefined, [Validators.required]),
      creditAmount: new FormControl(undefined)
    });

    this.client.valueChanges.subscribe(checked => {
      if (checked) {
        const validators = [Validators.required, Validators.pattern("^\\d+$"), Validators.min(1), Validators.max(this.local_data.credit.loanLimit)];
        this.creditAmount.setValidators(validators);
      } else {
        this.creditAmount.setValidators(null);
      }
      this.creditOfferForm.updateValueAndValidity();
    });
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

  getAllCredits() {
    this.creditService.getAllCredits().subscribe(
      res => {
        this.credits = res;
      }, error => {
        console.log('gg');
      }
    );
  }

  doAction() {
    this.dialogRef.close({event: this.action, data: this.local_data});
  }

  closeDialog() {
    this.dialogRef.close({event: 'Cancel'});
  }

  // private buildForm() {
  //   this.creditOfferForm = this.formBuilder.group({
  //     client: this.formBuilder.control(undefined, [Validators.required]),
  //     credit: this.formBuilder.control(undefined, [Validators.required]),
  //     creditAmount: this.formBuilder.control(undefined, [Validators.required, Validators.pattern("^\\d+$"), Validators.min(1), Validators.max(this.local_data.credit.loanLimit)])
  //   });
  // }
}
