import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActionitemsComponent } from './actionitems.component';

describe('ActionitemsComponent', () => {
  let component: ActionitemsComponent;
  let fixture: ComponentFixture<ActionitemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActionitemsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ActionitemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
