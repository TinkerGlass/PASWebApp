import {Component, ComponentFactoryResolver, OnInit, ViewChild} from '@angular/core';
import {ItemDirective} from '../ItemDirective';
import {EditItemComponent} from '../edit-item/edit-item.component';
import {FanSticker, Item, ItemType} from '../models/Item';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {ItemService} from '../service/item.service';
import {EditStickerComponent} from '../edit-sticker/edit-sticker.component';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.scss']
})
export class ItemComponent implements OnInit {

  @ViewChild(ItemDirective, {static: true}) itemHost: ItemDirective;
  item: Item;

  constructor(private componentFactoryResolver: ComponentFactoryResolver, private itemService: ItemService, private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params
      .subscribe((p: Params) => {
        if (p.id != null) {
          this.itemService.getItem(p.id).subscribe((item: Item) => {
            this.item = item;
            this.loadItemComponent();
          });
        } else {
          this.item = new Item();
          this.item.type = ItemType.ALBUM;
          this.item.sticker = new FanSticker();
          this.loadItemComponent();
        }
      });
  }

  loadItemComponent() {
    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(EditItemComponent);

    const viewContainerRef = this.itemHost.viewContainerRef;
    viewContainerRef.clear();

    const componentRef = viewContainerRef.createComponent(componentFactory);
    (componentRef.instance as EditItemComponent).item = this.item;
    (componentRef.instance as EditItemComponent).ready.subscribe(val => this.loadStickerComponent());
  }

  loadStickerComponent() {
    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(EditStickerComponent);

    const viewContainerRef = this.itemHost.viewContainerRef;
    viewContainerRef.clear();

    const componentRef = viewContainerRef.createComponent(componentFactory);
    (componentRef.instance as EditStickerComponent).item = this.item;
    (componentRef.instance as EditStickerComponent).ready.subscribe(val => this.complete());
  }

  complete() {
    this.router.navigate(['']);
  }

}
