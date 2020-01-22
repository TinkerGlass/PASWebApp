import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ItemListComponent} from './item-list/item-list.component';
import {EditItemComponent} from './edit-item/edit-item.component';
import {ItemComponent} from './item/item.component';


const routes: Routes = [
  { path: '', component: ItemListComponent, pathMatch: 'full' },
  { path: 'item', component: ItemComponent },
  { path: 'item/:id', component: ItemComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
