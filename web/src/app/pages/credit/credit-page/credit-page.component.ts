import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTable} from "@angular/material/table";
import {Credit, CreditCreate, CreditUpdate} from "../../../models/credit";
import {MatDialog} from "@angular/material/dialog";
import {CreditService} from "../../../services/credit.service";
import {DialogBoxCreditComponent} from "../dialog-box-credit/dialog-box-credit.component";

@Component({
  selector: 'app-credit-page',
  templateUrl: './credit-page.component.html',
  styleUrls: ['./credit-page.component.css']
})
export class CreditPageComponent implements OnInit {

  displayedColumns: string[] = ['name', 'loanLimit', 'interestRate', 'action'];
  dataSource: Credit[];

  @ViewChild(MatTable, {static: true}) table: MatTable<any>;

  constructor(
    public dialog: MatDialog,
    private creditService: CreditService
  ) {
  }

  ngOnInit(): void {
    this.getAllCredits();
  }

  getAllCredits() {
    this.creditService.getAllCredits().subscribe(
      res => {
        this.dataSource = res;
      }, error => {
        console.log('Кредиты не загрузились!');
      }
    );
  }

  openDialog(action, obj) {
    obj.action = action;
    const dialogRef = this.dialog.open(DialogBoxCreditComponent, {
      width: '250px',
      data: obj
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.event == 'Add') {
        this.createCredit(result.data);
      } else if (result.event == 'Update') {
        this.updateCredit(result.data);
      } else if (result.event == 'Delete') {
        this.deleteCredit(result.data);
      }
    });
  }

  createCredit(row_obj) {
    let credit = {
      name: row_obj.name,
      loanLimit: row_obj.loanLimit,
      interestRate: row_obj.interestRate
    } as CreditCreate;

    this.creditService.createCredit(credit).subscribe(
      res => {
        console.log(res);
        console.log('Кредит добавлен!');
      }, error => {
        console.log(credit);
        console.log('Кредит не добавлен!');
      }
    );

    window.location.reload();
  }

  updateCredit(row_obj) {
    let updateCredit = {
      id: row_obj.id,
      name: row_obj.name
    } as CreditUpdate;

    this.creditService.updateCredit(updateCredit).subscribe(
      res => {
        console.log('Кредит обновлен!');
        return true;
      }, error => {
        console.log(updateCredit);
        console.log('Кредит не обновлен!');
        return false;
      }
    );

    window.location.reload();
  }

  deleteCredit(row_obj) {
    this.creditService.deleteCredit(row_obj.id).subscribe(
      res => {
        console.log('Кредит удален!');
        return true;
      }, error => {
        console.log('Кредит не удален!');
        return false;
      }
    );

    window.location.reload();
  }
}
