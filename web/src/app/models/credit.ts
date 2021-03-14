export interface CreditCreate {
  name: string;
  loanLimit: bigint;
  interestRate: number;
}

export interface CreditUpdate {
  id: string;
  name: string;
}

export interface Credit {
  id: string;
  name: string;
  loanLimit: bigint;
  interestRate: number;
}
