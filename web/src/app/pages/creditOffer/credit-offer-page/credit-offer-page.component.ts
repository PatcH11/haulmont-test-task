import {Component, OnInit, ViewChild} from '@angular/core';
import {CreditOffer, CreditOfferCreate, CreditOfferUpdate} from "../../../models/creditOffer";
import {MatTable} from "@angular/material/table";
import {MatDialog} from "@angular/material/dialog";
import {CreditOfferService} from "../../../services/credit-offer.service";
import {DialogBoxCreditOfferComponent} from "../dialog-box-credit-offer/dialog-box-credit-offer.component";
import {Credit} from "../../../models/credit";

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
      width: '450px',
      data: obj
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.event == 'Add') {
        this.createCreditOffer(result.data);
      } else if (result.event == 'Update') {
        this.updateCreditOffer(result.data);
      } else if (result.event == 'Delete') {
        this.deleteCreditOffer(result.data);
      }
    });
  }

  createCreditOffer(row_obj) {
    let creditOffer = {
      clientId: row_obj.client.id,
      creditId: row_obj.credit.id,
      creditAmount: row_obj.creditAmount
    } as CreditOfferCreate;

    this.creditOfferService.createCreditOffer(creditOffer).subscribe(() => this.refresh());
  }

  updateCreditOffer(row_obj) {
    let updateCreditOffer = {
      id: row_obj.id,
      creditAmount: row_obj.creditAmount
    } as CreditOfferUpdate;

    this.creditOfferService.updateCreditOffer(updateCreditOffer).subscribe(() => this.refresh());
  }

  deleteCreditOffer(row_obj) {
    this.creditOfferService.deleteCreditOffer(row_obj.id).subscribe(() => this.refresh());
  }

  refresh() {
    this.creditOfferService.getAllCreditOffers().subscribe((data: CreditOffer[]) => {
      this.dataSource = data
    });
  }
}
