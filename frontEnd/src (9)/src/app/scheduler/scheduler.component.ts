import { Component, OnInit } from '@angular/core';
import { Scheduler } from 'src/module/scheduler';
import { SchedulerServiceService } from '../scheduler-service.service';

@Component({
  selector: 'app-scheduler',
  templateUrl: './scheduler.component.html',
  styleUrls: ['./scheduler.component.css']
})
export class SchedulerComponent implements OnInit {

  header: string = "List of Schedulers";

  constructor(private service: SchedulerServiceService) { }

  ngOnInit() {
    this.loadData();
  }

  schedulers: Scheduler[];
  message: string = null;
  errorMessage: string = null;

  delete(schid: string): void {
    this.service.deleteScheduler(schid).subscribe(
      (response) => {
        this.message = response;
        this.loadData();
      },
      (error) => console.log(error)
    );

  }

  
  
  loadData(): void {

    this.service.getSchedulers().subscribe(
      (data) => {
        this.schedulers = data;
        this.errorMessage = null;
      },
      (failResponse) => {
        this.errorMessage = failResponse.error.errorMessage;
      }
    )
  }



}
