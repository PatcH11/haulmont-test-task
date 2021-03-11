import {Component, OnInit, ViewChild} from '@angular/core';
import {CreditOffer, CreditOfferCreate} from "../../../models/creditOffer";
import {MatTable} from "@angular/material/table";
import {MatDialog} from "@angular/material/dialog";
import {CreditOfferService} from "../../../services/credit-offer.service";
import {DialogBoxCreditOfferComponent} from "../dialog-box-credit-offer/dialog-box-credit-offer.component";

@Component({
  selector: 'app-credit-offer-page',
  templateUrl: './credit-offer-page.component.html',
  styleUrls: ['./credit-offer-page.component.css']
})
export class CreditOfferPageComponent implements OnInit {

  displayedColumns: string[] = ['client', 'credit', 'interestRate', 'action'];
  dataSource: CreditOffer[];

  @ViewChild(MatTable, {static: true}) table: MatTable<any>;

  constructor(
    public dialog: MatDialog,
    private creditOfferService: CreditOfferService
  ) {
  }

  ngOnInit(): void {
    this.getAllCreditOffers();
  }

  getAllCreditOffers() {
    this.creditOfferService.getAllCreditOffers().subscribe(
      res => {
        this.dataSource = res;
      }, error => {
        console.log('gg');
      }
    );
  }

  openDialog(action, obj) {
    obj.action = action;
    const dialogRef = this.dialog.open(DialogBoxCreditOfferComponent, {
      width: '250px',
      data: obj
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.event == 'Add') {
        this.addRowData(result.data);
      } else if (result.event == 'Update') {
        this.updateRowData(result.data);
      } else if (result.event == 'Delete') {
        this.deleteRowData(result.data);
      }
    });
  }

  addRowData(row_obj) {
    let creditOffer = {
      clientId: row_obj.client.id,
      creditId: row_obj.credit.id,
      creditAmount: row_obj.creditAmount
    } as CreditOfferCreate;

    this.creditOfferService.createCreditOffer(creditOffer).subscribe(
      res => {
        console.log(res);
        console.log('Кредитное предложение добавлено!');
      }, error => {
        console.log(creditOffer);
        console.log('Кредитное предложение не добавлен!');
      }
    );

    this.table.renderRows();
  }

  updateRowData(row_obj) {
    let updateCreditOffer = {
      id: row_obj.id,
      client: row_obj.client,
      credit: row_obj.credit,
      creditAmount: row_obj.creditAmount,
    } as CreditOffer;

    this.creditOfferService.updateCreditOffer(updateCreditOffer).subscribe(
      res => {
        console.log('Кредитное предложение обновлено!');
        return true;
      }, error => {
        console.log(updateCreditOffer);
        console.log('Кредитное предложение не обновлено!');
        return false;
      }
    );

    this.table.renderRows();
  }

  deleteRowData(row_obj) {
    this.creditOfferService.deleteCreditOffer(row_obj.id).subscribe(
      res => {
        console.log('Кредит удален!');
        return true;
      }, error => {
        console.log('Кредит не удален!');
        return false;
      }
    );

    this.table.renderRows();
  }

}
