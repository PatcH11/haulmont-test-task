import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {NavigationComponent} from './navigation/navigation.component';
import {RegisterPageComponent} from './pages/client/register-page/register-page.component';
import {RouterModule, Routes} from "@angular/router";
import {NotFoundComponent} from './not-found/not-found.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {LoginPageComponent} from './pages/client/login-page/login-page.component';
import {MatExpansionModule} from "@angular/material/expansion";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatListModule} from "@angular/material/list";
import {_MatMenuDirectivesModule, MatMenuModule} from "@angular/material/menu";
import {CreateClientComponent} from './pages/client/create-client/create-client.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {ClientPageComponent} from './pages/client/client-page/client-page.component';
import {UpdateClientComponent} from './pages/client/update-client/update-client.component';
import {MatSelectModule} from "@angular/material/select";
import {DeleteClientComponent} from './pages/client/delete-client/delete-client.component';
import {CreateCreditComponent} from './pages/credit/create-credit/create-credit.component';
import {CreateCreditOfferComponent} from './pages/creditOffer/create-credit-offer/create-credit-offer.component';
import {CreatePaymentScheduleComponent} from './pages/paymentSchedule/create-payment-schedule/create-payment-schedule.component';
import {DialogBoxClientComponent} from './pages/client/dialog-box-client/dialog-box-client.component';
import {MatDialogModule} from "@angular/material/dialog";
import {MatTableModule} from "@angular/material/table";
import {CreditPageComponent} from './pages/credit/credit-page/credit-page.component';
import {DialogBoxCreditComponent} from './pages/credit/dialog-box-credit/dialog-box-credit.component';
import {CreditOfferPageComponent} from './pages/creditOffer/credit-offer-page/credit-offer-page.component';
import {DialogBoxCreditOfferComponent} from './pages/creditOffer/dialog-box-credit-offer/dialog-box-credit-offer.component';
import {PaymentSchedulePageComponent} from './pages/paymentSchedule/payment-schedule-page/payment-schedule-page.component';
import {DialogBoxPaymentScheduleComponent} from './pages/paymentSchedule/dialog-box-payment-schedule/dialog-box-payment-schedule.component';

const appRoutes: Routes = [
  {
    path: 'create',
    children: [
      {path: 'client', component: CreateClientComponent},
      {path: 'credit', component: CreateCreditComponent},
      {path: 'credit-offer', component: CreateCreditOfferComponent},
      {path: 'payment-schedule', component: CreatePaymentScheduleComponent},
      {path: 'cl', component: ClientPageComponent},
      {path: 'cr', component: CreditPageComponent},
      {path: 'cro', component: CreditOfferPageComponent}
    ]
  },
  {
    path: 'update',
    children: [
      {path: 'client', component: UpdateClientComponent}
    ]
  },
  {
    path: 'delete',
    children: [
      {path: 'client', component: DeleteClientComponent}
    ]
  },
  {
    path: '**',
    component: NotFoundComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    RegisterPageComponent,
    NotFoundComponent,
    LoginPageComponent,
    CreateClientComponent,
    ClientPageComponent,
    UpdateClientComponent,
    DeleteClientComponent,
    CreateCreditComponent,
    CreateCreditOfferComponent,
    CreatePaymentScheduleComponent,
    DialogBoxClientComponent,
    CreditPageComponent,
    DialogBoxCreditComponent,
    CreditOfferPageComponent,
    DialogBoxCreditOfferComponent,
    PaymentSchedulePageComponent,
    DialogBoxPaymentScheduleComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    ReactiveFormsModule,
    NgbModule,
    FontAwesomeModule,
    MatExpansionModule,
    MatSidenavModule,
    MatListModule,
    _MatMenuDirectivesModule,
    MatMenuModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatDialogModule,
    MatTableModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
