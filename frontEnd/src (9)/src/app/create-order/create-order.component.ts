import { Component, OnInit } from '@angular/core';
import { OrderServiceService } from '../order-service.service';

@Component({
  selector: 'app-create-order',
  templateUrl: './create-order.component.html',
  styleUrls: ['./create-order.component.css']
})
export class CreateOrderComponent implements OnInit {

  constructor(private service: OrderServiceService) { }

  validationMessages: string[] = null;
  errorMessage: string = null;
  successMessage: string = null;

  ngOnInit() {
  }

  createNew(data) {  
    this.service.addOrder(data.orderNumber,data.deliveryPlace,data.deliveryDate,data.quantity,data.customerId,data.schedulerId,data.productId).subscribe(
      (message) => {
        this.successMessage = message;
        this.validationMessages = null;
        this.errorMessage = null;
      },
      (failure) => {
        this.successMessage = null;
 //       this.validationMessages = JSON.parse(failure.error).errors;
        this.errorMessage = JSON.parse(failure.error).errorMessage;
      }

    )
  }

}
