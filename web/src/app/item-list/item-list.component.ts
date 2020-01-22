import { Component, OnInit } from '@angular/core';
import {Item, ItemType} from '../models/Item';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {ItemService} from '../service/item.service';

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.scss']
})
export class ItemListComponent implements OnInit {

  items: Item[];
  selectedItem: Item;

  constructor(private modalService: NgbModal, private itemService: ItemService) { }

  getItemType(item: Item): string {
    return item.type === ItemType.ALBUM ? 'Album' : 'Wideo';
  }

  openDeleteModal(content, item: Item) {
    this.selectedItem = item;
    this.modalService.open(content).result.then((result) => {
      if (result === 'delete') {
        this.itemService.deletItem(item.id).subscribe(() => {this.getItems();});
      }
    }, () => {});
  }

  ngOnInit() {
    this.getItems();
  }

  getItems() {
    this.itemService.getItems().subscribe((data: Item[]) => this.items = data);
  }

}
