import { Component, OnInit } from '@angular/core';
import { Order } from 'src/module/order';
import { OrderServiceService } from '../order-service.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  constructor(private service:OrderServiceService) { }

  ngOnInit() {
    this.loadData();
  }
  header: string = "List of Orders";

  orders: Order[];
  message: string = null;
  errorMessage: string = null;

  delete(orderNumber: string): void {
    this.service.deleteOrder(orderNumber).subscribe(
      (response) => {
        this.message = response;
        this.loadData();
      },
      (error) => console.log(error)
    );

  }
  
  loadData(): void {

    this.service.getOrders().subscribe(
      (data) => {
        this.orders = data;
        this.errorMessage = null;
      },
      (failResponse) => {
 //       this.errorMessage = failResponse.error.errorMessage;
      }
    )
  }

}
