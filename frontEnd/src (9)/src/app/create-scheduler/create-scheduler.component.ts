import { Component, OnInit } from '@angular/core';
import { SchedulerServiceService } from '../scheduler-service.service';

@Component({
  selector: 'app-create-scheduler',
  templateUrl: './create-scheduler.component.html',
  styleUrls: ['./create-scheduler.component.css']
})
export class CreateSchedulerComponent implements OnInit {

  validationMessages: string[] = null;
  errorMessage: string = null;
  successMessage: string = null;

  constructor(private service: SchedulerServiceService) { }

  ngOnInit() {
  }


  createNew(data) {  
    this.service.addScheduler(data).subscribe(
      (message) => {
        this.successMessage = message;
        this.validationMessages = null;
        this.errorMessage = null;
      },
      (failure) => {
        this.successMessage = null;
        this.validationMessages = JSON.parse(failure.error).errors;
        this.errorMessage = JSON.parse(failure.error).errorMessage;
      }

    )

  }

  // createNew(data: Land) {
  //   this.service.addLand(data).subscribe(
  //     (message) => {
  //       this.successMessage = message;
  //       this.validationMessages = null;
  //       this.errorMessage = null;
  //     },
  //     (failure) => {
  //       this.successMessage = null;
  //       this.validationMessages = JSON.parse(failure.error).errors;
  //       this.errorMessage = JSON.parse(failure.error).errorMessage;
  //     }

  //   )

  // }

}
