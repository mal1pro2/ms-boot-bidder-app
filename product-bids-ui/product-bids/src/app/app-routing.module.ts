import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router'; 
import { ActionitemsComponent } from './components/actionitems/actionitems.component';
import { BidsComponent } from './components/bids/bids.component';
import { ItemsComponent } from './components/items/items.component';

const routes: Routes = [
  {
    path: '',
    component: ItemsComponent
  } ,
  {
    path: 'auctionItems',
    component: ActionitemsComponent
  } ,
  {
    path: 'items',
    component: ItemsComponent
  },
  {
    path: 'bids',
    component: BidsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
