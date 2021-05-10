import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Customer } from 'src/module/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerServiceService {

  constructor(private http: HttpClient) { }

  baseUrlc: string = "http://localhost:8083/customerDetails"
    
      getCustomers(): Observable<any> {
        const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
        return this.http.get(`${this.baseUrlc}`,{headers});
      }

    
      getCustomer(customerId: string): Observable<any> {
       const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
//         console.log(this.http.get(`${this.baseUrlc}/getById/C005`));
        return this.http.get(`${this.baseUrlc}/${customerId}`,{headers});
        
      }
    
        updateCustomer(customer:Customer): Observable<any> {
         const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
          return this.http.put(this.baseUrlc, customer, { responseType: 'text',headers });
        }
    
    
       deleteCustomer(customerId: string): Observable<any> {
        const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
         return this.http.delete(`${this.baseUrlc}/${customerId}`, { responseType: 'text',headers })
       }
    
    
       addCustomer(newCustomer: Customer): Observable<any> {
         const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
        return this.http.post(this.baseUrlc, newCustomer, { responseType: 'text',headers });
        }
}
