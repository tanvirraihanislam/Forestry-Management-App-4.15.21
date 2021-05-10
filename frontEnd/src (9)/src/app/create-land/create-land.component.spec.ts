import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateLandComponent } from './create-land.component';

describe('CreateLandComponent', () => {
  let component: CreateLandComponent;
  let fixture: ComponentFixture<CreateLandComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateLandComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateLandComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
