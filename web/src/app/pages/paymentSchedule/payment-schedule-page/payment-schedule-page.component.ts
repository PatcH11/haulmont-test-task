import {Component, OnInit, ViewChild} from '@angular/core';
import {PaymentSchedule, PaymentScheduleCreate} from "../../../models/paymentSchedule";
import {MatTable} from "@angular/material/table";
import {PaymentScheduleService} from "../../../services/payment-schedule.service";
import {Client} from "../../../models/client";
import {ClientService} from "../../../services/client.service";
import {CreditService} from "../../../services/credit.service";
import {Credit} from "../../../models/credit";

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

  @ViewChild(MatTable, {static: true}) table: MatTable<any>;

  constructor(
    private paymentScheduleService: PaymentScheduleService,
    private clientService: ClientService,
    private creditService: CreditService
  ) {
  }

  ngOnInit(): void {
    this.getAllClients();
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

  getAllCreditsWhereClientIs(clientId: string) {
    this.creditService.getAllCreditsWhereClientIs(clientId).subscribe(
      res => {
        this.credits = res;
      }, error => {
        console.log('df');
      }
    );
  }

  getAllPaymentSchedules(clientId: string, creditId: string) {
    this.paymentScheduleService.getAllPaymentSchedulesWhereClientAndCreditIs(clientId, creditId).subscribe(
      res => {
        this.dataSource = res;
      }, error => {
        console.log('ss');
      }
    );
  }

  createPaymentSchedule(creditOfferId: string) {
    let paymentSchedule = {
      creditOfferId: creditOfferId
    } as PaymentScheduleCreate;

    this.paymentScheduleService.createPaymentSchedule(paymentSchedule).subscribe(
      res => {
        console.log(res);
      }, error => {
        console.log('bad');
      }
    );

    window.location.reload();
  }

  onChange(newValue) {
    console.log(newValue);
    this.credits = undefined;
    this.dataSource = undefined;
    this.client = newValue;
  }
}
