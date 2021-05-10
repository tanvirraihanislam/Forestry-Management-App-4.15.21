import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from 'src/module/product';

@Injectable({
  providedIn: 'root'
})
export class ProductServiceService {

  baseUrl: string = "http://localhost:8083/productDetails"

  constructor(private http: HttpClient) { }

  getProducts(): Observable<any> {
    const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
    return this.http.get(`${this.baseUrl}`,{headers});
  }

  getProduct(productId: string): Observable<any> {
       const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
        console.log(this.http.get(`${this.baseUrl}/${productId}`));
          console.log(productId)
       return this.http.get(`${this.baseUrl}/${productId}`,{headers});
       
     }

  updateProduct(product: Product): Observable<any> {
          const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
    return this.http.put(this.baseUrl, product, { responseType: 'text', headers });
    }



  deleteProduct(productId: string): Observable<any> {
    const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
    return this.http.delete(`${this.baseUrl}/${productId}`, { responseType: 'text', headers })
    }


    addProduct(newProduct: Product): Observable<any> {
      const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
      return this.http.post(this.baseUrl, newProduct, {  responseType: 'text', headers });
    }

//   addContract(newEmp: Contract): Observable<any> {
// //    const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
//     console.log(newEmp)
//     return this.http.post(this.baseUrl, newEmp, { responseType: 'text' });
//   }


}

