import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../admin-service.service';
import { LandServiceService } from '../land-service.service';

@Component({
  selector: 'app-create-admin',
  templateUrl: './create-admin.component.html',
  styleUrls: ['./create-admin.component.css']
})
export class CreateAdminComponent implements OnInit {
  validationMessages: string[] = null;
  errorMessage: string = null;
  successMessage: string = null;
  constructor(private service: AdminServiceService) { }

  ngOnInit() {
  }

  createNew(data) {  
    this.service.addAdmin(data).subscribe(
      (message) => {
        this.successMessage = message;
        this.validationMessages = null;
        this.errorMessage = null;
      },
      (failure) => {
        this.successMessage = null;
 //       this.validationMessages = JSON.parse(failure.error).errors;
       // this.errorMessage = JSON.parse(failure.error).errorMessage;
      }

    )

  }

}
