import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/module/product';
import { ProductServiceService } from '../product-service.service';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {

  product: Product=null;

  validationMessages: string[] = null;
  errorMessage: string = null;
  successMessage: string = null;

  constructor(private service: ProductServiceService,
              private route: ActivatedRoute, 
              private router: Router) { }

  ngOnInit() {
    this.route.paramMap.subscribe(
      (params) => {
        let productId: string = params.get('productId');
        console.log(productId);

        this.service.getProduct(productId).subscribe(
          (data) => {
            this.product = data;
          },
          (fail) => {
            this.errorMessage = fail.error.errorMessage;
          }
        )
      }
    )
  }

  updated() {
    this.service.updateProduct(this.product).subscribe(
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
    this.router.navigate(["productlist"]);
  }






}
