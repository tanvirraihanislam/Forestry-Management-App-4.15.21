import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Admin } from 'src/module/admin';
import { AdminServiceService } from '../admin-service.service';

@Component({
  selector: 'app-update-admin',
  templateUrl: './update-admin.component.html',
  styleUrls: ['./update-admin.component.css']
})
export class UpdateAdminComponent implements OnInit {
  admin: Admin=null;
  

  validationMessages: string[] = null;
  errorMessage: string = null;
  successMessage: string = null;
  constructor(private service:AdminServiceService, 
    private route:ActivatedRoute, 
    private router: Router) { }

    ngOnInit() {
      this.route.paramMap.subscribe(
        (params) => {
  ///        let landId: string = +params.get('landId');  // parseInt
          let adminId: string = params.get('adminId');
          console.log(adminId+" from update admin");
  
          this.service.getAdmin(adminId).subscribe(
            (data) => {
              console.log(data);
              this.admin = data;
              
              console.log(this.admin);
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
        this.service.updateAdmin(this.admin).subscribe(
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
        this.router.navigate(["adminlist"]);
      }

}
