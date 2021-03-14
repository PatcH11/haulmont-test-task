export interface ClientCreate {
  firstName: string;
  lastName: string;
  patronymic: string;
  phoneNumber: string;
  email: string;
  passportNumber: number;
}

export interface ClientUpdate {
  id: string;
  firstName: string;
  lastName: string;
  patronymic: string;
  phoneNumber: string;
  email: string;
  passportNumber: number;
}

export interface Client {
  id: string;
  firstName: string;
  lastName: string;
  patronymic: string;
  phoneNumber: string;
  email: string;
  passportNumber: number;
}
