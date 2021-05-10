import { Component, OnInit } from '@angular/core';
import { CustomerServiceService } from '../customer-service.service';

@Component({
  selector: 'app-create-customer',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.css']
})
export class CreateCustomerComponent implements OnInit {

  validationMessages: string[] = null;
  errorMessage: string = null;
  successMessage: string = null;

  constructor(private service: CustomerServiceService) { }

  ngOnInit() {
  }

  createNew(data) {  
    this.service.addCustomer(data).subscribe(
      (message) => {
        this.successMessage = message;
        this.validationMessages = null;
        this.errorMessage = null;
      },
      (failure) => {
        this.successMessage = null;
 //       this.validationMessages = JSON.parse(failure.error).errors;
 //       this.errorMessage = JSON.parse(failure.error).errorMessage;
      }

    )

  }

}
