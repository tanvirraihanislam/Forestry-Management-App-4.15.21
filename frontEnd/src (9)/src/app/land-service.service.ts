import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Land } from 'src/module/land';

@Injectable({
  providedIn: 'root'
})
export class LandServiceService {

  baseUrl: string = "http://localhost:8083/land"

  constructor(private http: HttpClient) { }

  getLands(): Observable<any> {
    const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
    return this.http.get(`${this.baseUrl}/getAllLand` , {headers});
  }


  // getLands(): Observable<any> {
  //   const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
  //   return this.http.get(this.baseUrl,{headers})
  // }

  getLand(landId: string): Observable<any> {
 //   const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
//     console.log(this.http.get(`${this.baseUrl}/getById/L005`));
const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
//return this.http.get(`http://localhost:8083/land/getById/L1`,{headers});
return this.http.get(`${this.baseUrl}/getById/${landId}`,{headers});
console.log(localStorage.getItem("token")+" this is token")
// let head=new HttpHeaders();

//         head.set("Authorization",localStorage.getItem("token")
//         )
//     return this.http.get(`http://localhost:8083/land/getById/L1`,{headers:head});
    
  }

    updateLand(land:Land): Observable<any> {
      const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
      return this.http.put(this.baseUrl, land, { responseType: 'text',headers });
    }

//    updateLand(landId:string, surveyNumber:string, ownerName:string, landArea:string ): Observable<any> {
//  //    const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
//      return this.http.put(`${this.baseUrl}/${landId}/${surveyNumber}/${ownerName}/${landArea}`, { responseType: 'text' });
//    }

  // deleteLand(landId: string): Observable<any> {
  //   const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
  //   return this.http.delete(`${this.baseUrl}/"deleteLand"/${landId}`, { headers, responseType: 'text' })
  // }

   deleteLand(landId: string): Observable<any> {
    const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
     return this.http.delete(`${this.baseUrl}/deleteLand/${landId}`, { responseType: 'text',headers })
   }

  
  //  addLand(newLand: Land): Observable<any> {
  //   const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
  //   return this.http.post(this.baseUrl, newLand, { responseType: 'text' });
  //  }

   addLand(newLand: Land ): Observable<any> {
     const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
    return this.http.post(this.baseUrl, newLand, { responseType: 'text',headers });
    }


}
