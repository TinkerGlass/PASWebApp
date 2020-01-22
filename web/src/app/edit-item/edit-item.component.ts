import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AlbumGenre, Item, ItemType, VideoGenre} from '../models/Item';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {filter} from 'rxjs/operators';
import {ItemService} from '../service/item.service';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';

@Component({
  selector: 'app-edit-item',
  templateUrl: './edit-item.component.html',
  styleUrls: ['./edit-item.component.scss']
})
export class EditItemComponent implements OnInit {

  item: Item;
  @Output() ready = new EventEmitter();
  itemType = ItemType;
  albumGenres = Object.keys(AlbumGenre).slice(Object.keys(AlbumGenre).length / 2, Object.keys(AlbumGenre).length - 1);
  videoGenre = Object.keys(VideoGenre).slice(Object.keys(VideoGenre).length / 2, Object.keys(VideoGenre).length - 1);

  constructor(private route: ActivatedRoute, private itemService: ItemService, private router: Router) { this.item = new Item();}

  ngOnInit() {

  }

  onSubmit() {
    this.ready.emit(true);
  }


}
