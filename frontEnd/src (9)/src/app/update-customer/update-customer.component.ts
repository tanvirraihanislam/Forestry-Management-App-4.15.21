import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from 'src/module/customer';
import { CustomerServiceService } from '../customer-service.service';

@Component({
  selector: 'app-update-customer',
  templateUrl: './update-customer.component.html',
  styleUrls: ['./update-customer.component.css']
})
export class UpdateCustomerComponent implements OnInit {


  customer: Customer=null;

  validationMessages: string[] = null;
  errorMessage: string = null;
  successMessage: string = null;

  constructor(private service:CustomerServiceService, 
    private route:ActivatedRoute, 
    private router: Router) { }

  ngOnInit() {
    this.route.paramMap.subscribe(
      (params) => {
///        let customerId: string = +params.get('customerId');  // parseInt
        let customerId: string = params.get('customerId');
        console.log(customerId);

        this.service.getCustomer(customerId).subscribe(
          (data) => {
            console.log(data);
            this.customer = data;
            
            console.log(this.customer);
          },
          (fail) => {
            this.errorMessage = fail.error.errorMessage;
          }
        )
      }
    )
  }




  updated() {
    this.service.updateCustomer(this.customer).subscribe(
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
    this.router.navigate(["customerlist"]);
  }


}
