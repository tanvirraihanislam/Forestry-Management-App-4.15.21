import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/module/customer';
import { CustomerServiceService } from '../customer-service.service';
import { LandServiceService } from '../land-service.service';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {

  constructor(private service: CustomerServiceService) { }

  ngOnInit() {
    this.loadData();
  }

  header: string = "List of Customers";

  customers: Customer[];

  message: string = null;
  errorMessage: string = null;

  delete(customerId: string): void {
    this.service.deleteCustomer(customerId).subscribe(
      (response) => {
        this.message = response;
        this.loadData();
      },
      (error) => console.log(error)
    );
  }


  loadData(): void {

    this.service.getCustomers().subscribe(
      (data) => {
        this.customers = data;
        this.errorMessage = null;
      },
      (failResponse) => {
 //       this.errorMessage = failResponse.error.errorMessage;
      }
    )
  }

  
}
