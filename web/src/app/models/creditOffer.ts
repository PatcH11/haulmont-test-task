import {Client} from "./client";
import {Credit} from "./credit";

export interface CreditOfferCreate {
  clientId: string;
  creditId: string;
  creditAmount: number
}

export interface CreditOffer {
  id: string;
  client: Client;
  credit: Credit;
  creditAmount: number;
}
