import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ButtonsModule } from 'ngx-bootstrap/buttons';
import { ModalModule } from 'ngx-bootstrap/modal';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ItemsComponent } from './components/items/items.component'; 
import { BidsComponent } from './components/bids/bids.component';
import { HttpClientModule } from "@angular/common/http";
import { FormsModule,ReactiveFormsModule} from '@angular/forms';
import { PaginationModule } from 'ngx-bootstrap/pagination';
import {NgxPaginationModule} from 'ngx-pagination';
import {ActionitemsComponent } from './components/actionitems/actionitems.component'; 

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ItemsComponent, 
    BidsComponent,
    ActionitemsComponent
  ],
  imports: [
    BrowserModule,
    ButtonsModule.forRoot(),
    ModalModule.forRoot(),
    AppRoutingModule,
    HttpClientModule,
    FormsModule,  
    ReactiveFormsModule,
    PaginationModule.forRoot(),
    NgxPaginationModule, 
  ],
  providers: [],
  exports: [ModalModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
