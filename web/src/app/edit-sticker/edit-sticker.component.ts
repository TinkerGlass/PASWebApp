import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {FanSticker, Item} from '../models/Item';
import {HttpErrorResponse} from '@angular/common/http';
import {ItemService} from '../service/item.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-edit-sticker',
  templateUrl: './edit-sticker.component.html',
  styleUrls: ['./edit-sticker.component.scss']
})
export class EditStickerComponent implements OnInit {

  item: Item;
  sticker: FanSticker;
  errors = [] as string[];
  ready = new EventEmitter();

  constructor(private itemService: ItemService, private router: Router) { }

  ngOnInit() {
    this.sticker = this.item.sticker;
  }

  onSubmit() {
    this.errors = [];
    this.itemService.saveItem(this.item).subscribe(
      response => {
        this.ready.emit(true);
      },
      (error: HttpErrorResponse) => {
        this.errors.push(error.message);
        console.log(error);
      }
    );
  }

}
