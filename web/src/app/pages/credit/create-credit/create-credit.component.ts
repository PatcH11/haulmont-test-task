import {Component, OnInit} from '@angular/core';
import {CreditService} from "../../../services/credit.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Credit} from "../../../models/credit";

@Component({
  selector: 'app-create-credit',
  templateUrl: './create-credit.component.html',
  styleUrls: ['./create-credit.component.css']
})
export class CreateCreditComponent implements OnInit {

  creditForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private creditService: CreditService
  ) {
  }

  ngOnInit(): void {
    this.buildForm();
  }

  createCredit() {
    let credit = {
      name: this.creditForm.value.name,
      loanLimit: this.creditForm.value.loanLimit,
      interestRate: this.creditForm.value.interestRate
    } as Credit;

    this.creditService.createCredit(credit).subscribe(
      res => {
        console.log('gud');
      }, error => {
        console.log('bad');
      }
    );
  }

  private buildForm() {
    this.creditForm = this.formBuilder.group({
      name: this.formBuilder.control(undefined, [Validators.required, Validators.pattern("^[А-Яа-я]+$")]),
      loanLimit: this.formBuilder.control(undefined, [Validators.required, Validators.pattern("^\\d+$")]),
      interestRate: this.formBuilder.control(undefined, [Validators.required, Validators.pattern("^\\d+$")])
    });
  }
}
