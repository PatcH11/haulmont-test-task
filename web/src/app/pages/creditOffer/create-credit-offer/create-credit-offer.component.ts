import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CreditOfferService} from "../../../services/credit-offer.service";
import {CreditOfferCreate} from "../../../models/creditOffer";
import {ClientService} from "../../../services/client.service";
import {CreditService} from "../../../services/credit.service";
import {Client} from "../../../models/client";
import {Credit} from "../../../models/credit";

@Component({
  selector: 'app-create-credit-offer',
  templateUrl: './create-credit-offer.component.html',
  styleUrls: ['./create-credit-offer.component.css']
})
export class CreateCreditOfferComponent implements OnInit {

  creditOfferForm: FormGroup;

  clients: Client[];
  credits: Credit[];

  constructor(
    private formBuilder: FormBuilder,
    private creditOfferService: CreditOfferService,
    private clientService: ClientService,
    private creditService: CreditService
  ) {
  }

  ngOnInit(): void {
    this.buildForm();
    this.getAllClients();
    this.getAllCredits();
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

  createCreditOffer() {
    let creditOffer = {
      clientId: this.creditOfferForm.value.client.id,
      creditId: this.creditOfferForm.value.credit.id,
      creditAmount: this.creditOfferForm.value.creditAmount
    } as CreditOfferCreate;

    console.log(creditOffer);

    this.creditOfferService.createCreditOffer(creditOffer).subscribe(
      res => {
        console.log('gud');
      }, error => {
        console.log('bad');
      }
    )
  }

  private buildForm() {
    this.creditOfferForm = this.formBuilder.group({
      client: this.formBuilder.control(undefined, [Validators.required]),
      credit: this.formBuilder.control(undefined, [Validators.required]),
      creditAmount: this.formBuilder.control(undefined, [Validators.required, Validators.pattern("^\\d+$")])
    });
  }
}
