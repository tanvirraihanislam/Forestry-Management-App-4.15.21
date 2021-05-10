import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Scheduler } from 'src/module/scheduler';
import { SchedulerServiceService } from '../scheduler-service.service';

@Component({
  selector: 'app-update-scheduler',
  templateUrl: './update-scheduler.component.html',
  styleUrls: ['./update-scheduler.component.css']
})
export class UpdateSchedulerComponent implements OnInit {

  scheduler: Scheduler=null;

  validationMessages: string[] = null;
  errorMessage: string = null;
  successMessage: string = null;

  constructor(private service: SchedulerServiceService,
              private route: ActivatedRoute, 
              private router: Router) { }
  


  ngOnInit() {
    this.route.paramMap.subscribe(
      (params) => {
        let schid: string = params.get('schedulerId');

        this.service.getScheduler(schid).subscribe(
          (data) => {
            this.scheduler = data;
          },
          (fail) => {
            this.errorMessage = fail.error.errorMessage;
          }
        )
      }
    )
  }

  updated() {
    this.service.updateScheduler(this.scheduler).subscribe(
      (message) => {
        this.successMessage=message
        this.validationMessages = null
        this.errorMessage = null
      },
      (failure) => {
        this.successMessage = null;
        this.validationMessages = JSON.parse(failure.error).errors;
        this.errorMessage = JSON.parse(failure.error).errorMessage;
      }
    )
  }

  goBack(){
    this.router.navigate(["schlist"]);
  }

}
