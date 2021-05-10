import { Component, OnInit } from '@angular/core';
import { Order } from 'src/module/order';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderServiceService } from '../order-service.service';
import { Orderupdt } from 'src/module/orderupdt';

@Component({
  selector: 'app-update-order',
  templateUrl: './update-order.component.html',
  styleUrls: ['./update-order.component.css']
})
export class UpdateOrderComponent implements OnInit {

  constructor(private service:OrderServiceService, 
    private route:ActivatedRoute, 
    private router: Router) { }

  order:Order=null;

//  order:Orderupdt=null;
  

  validationMessages: string[] = null;
  errorMessage: string = null;
  successMessage: string = null;

 
  ngOnInit() {
    this.route.paramMap.subscribe(
      (params) => {
///        let landId: string = +params.get('landId');  // parseInt
        let orderNumber: string = params.get('orderNumber');
        console.log(orderNumber);

        this.service.getOrder(orderNumber).subscribe(
          (data) => {
            console.log(data);
            this.order = data;
            
            console.log(this.order);
          },
          (fail) => {
            this.errorMessage = fail.error.errorMessage;
          }
        )
      }
    )
  }

  updated() {
    this.service.updateOrder(this.order).subscribe(
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
    this.router.navigate(["orderlist"]);
  }

}
