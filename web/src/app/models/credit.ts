export interface CreditCreate {
  name: string;
  loanLimit: bigint;
  interestRate: number;
}

export interface Credit {
  id: string;
  name: string;
  loanLimit: bigint;
  interestRate: number;
}
