import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateSchedulerComponent } from './update-scheduler.component';

describe('UpdateSchedulerComponent', () => {
  let component: UpdateSchedulerComponent;
  let fixture: ComponentFixture<UpdateSchedulerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateSchedulerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateSchedulerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
