import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateLandComponent } from './update-land.component';

describe('UpdateLandComponent', () => {
  let component: UpdateLandComponent;
  let fixture: ComponentFixture<UpdateLandComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateLandComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateLandComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
