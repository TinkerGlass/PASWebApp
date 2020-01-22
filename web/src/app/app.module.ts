import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ItemListComponent } from './item-list/item-list.component';
import {HttpClientModule} from '@angular/common/http';
import { EditItemComponent } from './edit-item/edit-item.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { EditStickerComponent } from './edit-sticker/edit-sticker.component';
import {ItemDirective} from './ItemDirective';
import { ItemComponent } from './item/item.component';

@NgModule({
  declarations: [
    AppComponent,
    ItemListComponent,
    EditItemComponent,
    EditStickerComponent,
    ItemDirective,
    ItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [EditItemComponent, EditStickerComponent]
})
export class AppModule { }
