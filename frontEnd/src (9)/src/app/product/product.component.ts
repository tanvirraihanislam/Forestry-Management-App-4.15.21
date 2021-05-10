import { Component, OnInit } from '@angular/core';
import { Product } from 'src/module/product';
import { ProductServiceService } from '../product-service.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  constructor(private service: ProductServiceService) { }

  ngOnInit() {
    this.loadData();
  }

  header: string = "List of Products";

  products: Product[];
  message: string = null;
  errorMessage: string = null;

  delete(productId: string): void {    
    this.service.deleteProduct(productId).subscribe(
      (response) => {
        this.message = response;
        this.loadData();
      },
      (error) => console.log(error)
    );

  }

  loadData(): void {

    this.service.getProducts().subscribe(
      (data) => {
        this.products = data;
        this.errorMessage = null;
      },
      (failResponse) => {
        this.errorMessage = failResponse.error.errorMessage;
      }
    )
  }





}
