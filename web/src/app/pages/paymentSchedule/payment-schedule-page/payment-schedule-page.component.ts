import {Component, OnInit, ViewChild} from '@angular/core';
import {PaymentSchedule, PaymentScheduleCreate} from "../../../models/paymentSchedule";
import {MatTable} from "@angular/material/table";
import {PaymentScheduleService} from "../../../services/payment-schedule.service";
import {Client} from "../../../models/client";
import {ClientService} from "../../../services/client.service";
import {CreditService} from "../../../services/credit.service";
import {Credit} from "../../../models/credit";
import * as moment from 'moment';

@Component({
  selector: 'app-payment-schedule-page',
  templateUrl: './payment-schedule-page.component.html',
  styleUrls: ['./payment-schedule-page.component.css']
})
export class PaymentSchedulePageComponent implements OnInit {

  displayedColumns: string[] = ['date', 'amountPayment', 'repaymentAmountLoanBody', 'repaymentAmountPercentages', 'indebtedness'];
  dataSource: PaymentSchedule[];

  clients: Client[];
  credits: Credit[];
  client: Client;
  credit: Credit;
  isLoanPaid = false;

  @ViewChild(MatTable, {static: true}) table: MatTable<any>;

  constructor(
    private paymentScheduleService: PaymentScheduleService,
    private clientService: ClientService,
    private creditService: CreditService
  ) {
  }

  ngOnInit(): void {
    console.log(moment());
    this.getAllClients();
  }

  getAllClients() {
    this.clientService.getAllClients().subscribe(
      res => {
        this.clients = res;
      }, error => {
        console.log(error);
      }
    );
  }

  getAllCreditsWhereClientIs(clientId: string) {
    this.creditService.getAllCreditsWhereClientIs(clientId).subscribe(
      res => {
        this.credits = res;
      }, error => {
        console.log(error);
      }
    );
  }

  getAllPaymentSchedules(clientId: string, creditId: string) {
    this.paymentScheduleService.getAllPaymentSchedulesWhereClientAndCreditIs(clientId, creditId).subscribe(
      res => {
        if (res[res.length - 1].indebtedness === 0.0) {
          this.isLoanPaid = true;
        }
        res.forEach(value => {
          value.date = moment(value.date).locale('ru').format('MMMM Do YYYY, h:mm:ss a');
        });
        this.dataSource = res;
      }, error => {
        console.log(error);
      }
    );
  }

  createPaymentSchedule(creditOfferId: string) {
    let paymentSchedule = {
      creditOfferId: creditOfferId
    } as PaymentScheduleCreate;

    this.paymentScheduleService.createPaymentSchedule(paymentSchedule).subscribe(
      res => {
        if (res.indebtedness === 0) {
          this.isLoanPaid = true;
        }
        this.refresh()
      }, error => {
        console.log(error);
      }
    );
  }

  onChange(newValue) {
    console.log(newValue);
    this.credits = undefined;
    this.dataSource = undefined;
    this.client = newValue;
  }

  onChangeCredit(newValue) {
    this.credit = newValue;
  }

  refresh() {
    this.getAllPaymentSchedules(this.client.id, this.credit.id);
  }

  calculatePayments() {
    return this.calculateAmountPayment() - this.getCreditAmount();
  }

  getCreditAmount() {
    return this.dataSource[0].creditOffer.creditAmount
  }

  calculateAmountPayment() {
    let sum = 0;
    this.dataSource.forEach(value => {
      sum = sum + value.amountPayment;
    })
    return sum;
  }
}
