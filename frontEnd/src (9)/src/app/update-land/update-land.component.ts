import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Land } from 'src/module/land';
import { LandServiceService } from '../land-service.service';

@Component({
  selector: 'app-update-land',
  templateUrl: './update-land.component.html',
  styleUrls: ['./update-land.component.css']
})
export class UpdateLandComponent implements OnInit {

  land: Land=null;

  validationMessages: string[] = null;
  errorMessage: string = null;
  successMessage: string = null;

  constructor(private service:LandServiceService, 
              private route:ActivatedRoute, 
              private router: Router) { }

  ngOnInit() {
    this.route.paramMap.subscribe(
      (params) => {
//        let landId: string = +params.get('landId');  // parseInt
        let landId: string = params.get('landId');
        console.log(landId);

        this.service.getLand(landId).subscribe(
          (data) => {
            console.log(data);
            this.land = data;
           console.log(this.land);
          },
          (fail) => {
            this.errorMessage = fail.error.errorMessage;
          }
        )
      }
    )
  }

  //  updated() {
  //    this.service.updateLand(this.land.landId,this.land.surveyNumber,this.land.ownerName,this.land.landArea).subscribe(
  //      (message) => {
  //        this.successMessage=message
  //        this.validationMessages = null
  //        this.errorMessage = null
  //      },
  //      (failure) => {
  //        this.successMessage = null;
  //        this.validationMessages = JSON.parse(failure.error).errors;
  //        this.errorMessage = JSON.parse(failure.error).errorMessage;
  //      }

  //   )
  //  }

    updated() {
      this.service.updateLand(this.land).subscribe(
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
      this.router.navigate(["landlist"]);
    }



}
