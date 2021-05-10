import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Order } from 'src/module/order';
@Injectable({
  providedIn: 'root'
})
export class OrderServiceService {

  baseUrl: string = "http://localhost:8083/orderDetails"
  constructor(private http: HttpClient) { }

  getOrders(): Observable<any> {
    const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
    return this.http.get(`${this.baseUrl}`,{headers});
  }
  // getLands(): Observable<any> {
  //   const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
  //   return this.http.get(this.baseUrl,{headers})
  // }

  getOrder(orderNumber: string): Observable<any> {
    //http://localhost:8083/orderDetails/O122
    const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
    // console.log(this.http.get(`${this.baseUrl}/getById/L005`));
    return this.http.get(`${this.baseUrl}/${orderNumber}`,{headers});
    
  }

  updateOrder(order: Order): Observable<any> {
         const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
    return this.http.put(this.baseUrl, order, { responseType: 'text',headers });
    }

//     updateOrder(orderNumber: string, deliveryPlace: string, deliveryDate: string, 
//       quantity: string, customerId: string, schedulerId: string, productId: string): Observable<any> {
//  //     const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
//       return this.http.put(`${this.baseUrl}/${orderNumber}/${deliveryPlace}/${deliveryDate}/${quantity}/${customerId}/${schedulerId}/${productId}`, { responseType: 'text' });
//     }

//    updateLand(landId:string, surveyNumber:string, ownerName:string, landArea:string ): Observable<any> {
//  //    const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
//      return this.http.put(`${this.baseUrl}/${landId}/${surveyNumber}/${ownerName}/${landArea}`, { responseType: 'text' });
//    }

  // deleteLand(landId: string): Observable<any> {
  //   const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
  //   return this.http.delete(`${this.baseUrl}/"deleteLand"/${landId}`, { headers, responseType: 'text' })
  // }

   deleteOrder(orderNumber: string): Observable<any> {
    //http://localhost:8083/orderDetails/orderNumber
    const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
     return this.http.delete(`${this.baseUrl}/${orderNumber}`, { responseType: 'text',headers })
   }

  
  //  addLand(newLand: Land): Observable<any> {
  //   const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
  //   return this.http.post(this.baseUrl, newLand, { responseType: 'text' });
  //  }

  addOrder(orderNumber:string, deliveryPlace:string, deliveryDate:string, 
    quantity:string, customerId:string,schedulerId:string,productId:string ): Observable<any> {
    const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
    return this.http.post(`${this.baseUrl}/${orderNumber}/${deliveryPlace}/${deliveryDate}/${quantity}/${customerId}/${schedulerId}/${productId}`, { responseType: 'text', headers });
    }
   
}
