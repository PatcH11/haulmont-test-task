import {Component, OnInit, ViewChild} from '@angular/core';
import {PaymentSchedule, PaymentScheduleCreate} from "../../../models/paymentSchedule";
import {MatTable} from "@angular/material/table";
import {MatDialog} from "@angular/material/dialog";
import {PaymentScheduleService} from "../../../services/payment-schedule.service";
import {Client} from "../../../models/client";
import {ClientService} from "../../../services/client.service";
import {CreditOffer} from "../../../models/creditOffer";
import {CreditOfferService} from "../../../services/credit-offer.service";
import {CreditService} from "../../../services/credit.service";
import {Credit} from "../../../models/credit";
import {DialogBoxPaymentScheduleComponent} from "../dialog-box-payment-schedule/dialog-box-payment-schedule.component";

@Component({
  selector: 'app-payment-schedule-page',
  templateUrl: './payment-schedule-page.component.html',
  styleUrls: ['./payment-schedule-page.component.css']
})
export class PaymentSchedulePageComponent implements OnInit {

  displayedColumns: string[] = ['date', 'amountPayment', 'repaymentAmountLoanBody', 'repaymentAmountPercentages', 'indebtedness', 'action'];
  dataSource: PaymentSchedule[];

  clients: Client[];
  creditOffers: CreditOffer[];
  credits: Credit[];
  client: Client;

  @ViewChild(MatTable, {static: true}) table: MatTable<any>;

  constructor(
    public dialog: MatDialog,
    private paymentScheduleService: PaymentScheduleService,
    private clientService: ClientService,
    private creditOfferService: CreditOfferService,
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

  openDialog(action, obj) {
    obj.action = action;
    const dialogRef = this.dialog.open(DialogBoxPaymentScheduleComponent, {
      width: '250px',
      data: obj
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.event == 'Delete') {
        this.deletePaymentSchedule(result.data);
      }
    });
  }

  onChange(newValue) {
    console.log(newValue);
    this.client = newValue;
  }

  deletePaymentSchedule(row_obj) {
    this.paymentScheduleService.deletePaymentSchedule(row_obj.id).subscribe(
      res => {
        console.log('График удален!');
        return true;
      }, error => {
        console.log('График не удален!');
        return false;
      }
    );

    window.location.reload();
  }
}
