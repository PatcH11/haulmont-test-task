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

  displayedColumns: string[] = ['date', 'amountPayment', 'repaymentAmountLoanBody', 'repaymentAmountPercentages', 'indebtedness'];
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

  getAllCreditOffersWhereClientIs(clientId: string) {
    this.creditOfferService.getAllCreditOffersWhereClientIs(clientId).subscribe(
      res => {
        this.creditOffers = res;
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
  }

  openDialog(action, obj) {
    obj.action = action;
    const dialogRef = this.dialog.open(DialogBoxPaymentScheduleComponent, {
      width: '250px',
      data: obj
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.event == 'Add') {
        this.addRowData(result.data);
      }
      // else if (result.event == 'Update') {
      //   this.updateRowData(result.data);
      // } else if (result.event == 'Delete') {
      //   this.deleteRowData(result.data);
      // }
    });
  }

  addRowData(row_obj) {
    let paymentSchedule = {
      creditOfferId: row_obj.creditOffer.id
    } as PaymentScheduleCreate;

    this.paymentScheduleService.createPaymentSchedule(paymentSchedule).subscribe(
      res => {
        console.log(res);
        console.log('Кредитное предложение добавлено!');
      }, error => {
        console.log(paymentSchedule);
        console.log('Кредитное предложение не добавлен!');
      }
    );

    window.location.reload();
  }

  onChange(newValue) {
    console.log(newValue);
    this.client = newValue;
  }

  // updateRowData(row_obj) {
  //   let updateCreditOffer = {
  //     id: row_obj.id,
  //     client: row_obj.client,
  //     credit: row_obj.credit,
  //     creditAmount: row_obj.creditAmount,
  //   } as CreditOffer;
  //
  //   this.creditOfferService.updateCreditOffer(updateCreditOffer).subscribe(
  //     res => {
  //       console.log('Кредитное предложение обновлено!');
  //       return true;
  //     }, error => {
  //       console.log(updateCreditOffer);
  //       console.log('Кредитное предложение не обновлено!');
  //       return false;
  //     }
  //   );
  //
  //   this.table.renderRows();
  // }
  //
  // deleteRowData(row_obj) {
  //   this.creditOfferService.deleteCreditOffer(row_obj.id).subscribe(
  //     res => {
  //       console.log('Кредит удален!');
  //       return true;
  //     }, error => {
  //       console.log('Кредит не удален!');
  //       return false;
  //     }
  //   );
  //
  //   this.table.renderRows();
  // }
}
