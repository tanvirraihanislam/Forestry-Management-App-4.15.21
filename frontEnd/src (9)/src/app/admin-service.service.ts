import { Injectable } from '@angular/core';
import { Admin } from 'src/module/admin';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {

  constructor(private http: HttpClient) { }

  baseUrl1: string = "http://localhost:8083/adminDetails"
    
    getAdmins(): Observable<any> {
      const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
      return this.http.get(`${this.baseUrl1}`,{headers});
    }
    // getLands(): Observable<any> {
    //   const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
    //   return this.http.get(this.baseUrl,{headers})
    // }
  
    getAdmin(adminId: string): Observable<any> {
      //http://localhost:8083/adminDetails/getById/55
      const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
       console.log(this.http.get(`${this.baseUrl1}/getById/${adminId}`));
      return this.http.get(`${this.baseUrl1}/getById/${adminId}`,{headers});
      
    }
  
      updateAdmin(admin:Admin): Observable<any> {
      
       //http://localhost:8083/adminDetails/putupdate
        const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
        return this.http.put(`${this.baseUrl1}/putupdate`, admin, { responseType: 'text',headers });
      }
  
  //    updateLand(landId:string, surveyNumber:string, ownerName:string, landArea:string ): Observable<any> {
  //  //    const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
  //      return this.http.put(`${this.baseUrl}/${landId}/${surveyNumber}/${ownerName}/${landArea}`, { responseType: 'text' });
  //    }
  
    // deleteLand(landId: string): Observable<any> {
    //   const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
    //   return this.http.delete(`${this.baseUrl}/"deleteLand"/${landId}`, { headers, responseType: 'text' })
    // }
  
     deleteAdmin(adminId: string): Observable<any> {
      const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
       return this.http.delete(`${this.baseUrl1}/${adminId}`, { responseType: 'text',headers })
     }
  
    
    //  addLand(newLand: Land): Observable<any> {
    //   const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
    //   return this.http.post(this.baseUrl, newLand, { responseType: 'text' });
    //  }
  
     addAdmin(newAdmin: Admin ): Observable<any> {
       const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
      return this.http.post(this.baseUrl1, newAdmin, { responseType: 'text',headers });
      }

      verifyContract(contractNumber : string){
        
        const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
        return this.http.get("http://localhost:8083/adminDetails/verify/"+contractNumber,{headers,responseType:"text"});
      }


}
