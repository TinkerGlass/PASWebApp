import { Directive, ViewContainerRef } from '@angular/core';

@Directive({
  selector: '[appItem]',
})
export class ItemDirective {
  constructor(public viewContainerRef: ViewContainerRef) { }
}
