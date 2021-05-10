import { Product } from "./product";
import { Products } from "./products";


export interface Order{
    orderNumber: string;
    deliveryPlace:string;
    deliveryDate: string;
    quantity: string;
 //   productId: string;


   
   
 //   product:Product;

 products: Products;


}
