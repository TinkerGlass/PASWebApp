import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditStickerComponent } from './edit-sticker.component';

describe('EditStickerComponent', () => {
  let component: EditStickerComponent;
  let fixture: ComponentFixture<EditStickerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditStickerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditStickerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
