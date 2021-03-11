import {CreditOffer} from "./creditOffer";

export interface PaymentScheduleCreate {
  creditOfferId: string;
}

export interface PaymentSchedule {
  id: string;
  creditOffer: CreditOffer;
  date: Date;
  amountPayment: number;
  repaymentAmountLoanBody: number;
  repaymentAmountPercentages: number;
  indebtedness: number;
}
