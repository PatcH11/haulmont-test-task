import {CreditOffer} from "./creditOffer";

export interface PaymentScheduleCreate {
  creditOfferId: string;
}

export interface PaymentSchedule {
  id: string;
  creditOffer: CreditOffer;
  date: string;
  amountPayment: number;
  repaymentAmountLoanBody: number;
  repaymentAmountPercentages: number;
  indebtedness: number;
}
