import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Contract } from 'src/module/contract';

@Injectable({
  providedIn: 'root'
})
export class ContractServiceService {

  baseUrl: string = "http://localhost:8083/contractDetails"

  constructor(private http: HttpClient) { }

  getContracts(): Observable<any> {
    const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
    return this.http.get(`${this.baseUrl}`,{headers});
  }

  getContract(contractNumber: string): Observable<any> {
      const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
        console.log(this.http.get(`${this.baseUrl}/${contractNumber}`));
          console.log(contractNumber)
       return this.http.get(`${this.baseUrl}/${contractNumber}`,{headers});
       
     }

  updateContract(contract: Contract): Observable<any> {
          const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
    return this.http.put(this.baseUrl, contract, { responseType: 'text',headers });
    }



  deleteContract(landId: string): Observable<any> {
    const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
    return this.http.delete(`${this.baseUrl}/${landId}`, { responseType: 'text',headers })
    }


    addContract(contractNumber:string, quotation:number, startDate:Date, endDate:Date, 
      contractStatus:string, customerId:string, adminId:string, landId:string ): Observable<any> {
      const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
return this.http.post(`${this.baseUrl}/${contractNumber}/${quotation}/${startDate}/${endDate}/${contractStatus}/${customerId}/${adminId}/${landId}`, { responseType: 'text',headers });
}

    

//   addContract(newEmp: Contract): Observable<any> {
// //    const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
//     console.log(newEmp)
//     return this.http.post(this.baseUrl, newEmp, { responseType: 'text' });
//   }
    
}
