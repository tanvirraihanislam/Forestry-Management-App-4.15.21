import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateSchedulerComponent } from './create-scheduler.component';

describe('CreateSchedulerComponent', () => {
  let component: CreateSchedulerComponent;
  let fixture: ComponentFixture<CreateSchedulerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateSchedulerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateSchedulerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
