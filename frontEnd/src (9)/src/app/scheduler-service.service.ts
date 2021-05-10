import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Scheduler } from 'src/module/scheduler';

@Injectable({
  providedIn: 'root'
})
export class SchedulerServiceService {

  baseUrl: string = "http://localhost:8083/schedulerDetails"

  constructor(private http: HttpClient) { }

  getSchedulers(): Observable<any> {
    const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
    return this.http.get(this.baseUrl,{headers});
  }
  // getLands(): Observable<any> {
  //   const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
  //   return this.http.get(this.baseUrl,{headers})
  // }

  getScheduler(schedulerId: string):Observable<any> {
    const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
    return this.http.get(`${this.baseUrl}/${schedulerId}`,{headers})
  }

  updateScheduler(scheduler:Scheduler): Observable<any> {
      const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
   return this.http.put(this.baseUrl, scheduler, { responseType: 'text',headers });
  }

//    updateLand(landId:string, surveyNumber:string, ownerName:string, landArea:string ): Observable<any> {
//  //    const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
//      return this.http.put(`${this.baseUrl}/${landId}/${surveyNumber}/${ownerName}/${landArea}`, { responseType: 'text' });
//    }

  // deleteLand(landId: string): Observable<any> {
  //   const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
  //   return this.http.delete(`${this.baseUrl}/"deleteLand"/${landId}`, { headers, responseType: 'text' })
  // }

   deleteScheduler(schedulerId: string): Observable<any> {
    const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
     return this.http.delete(`${this.baseUrl}/${schedulerId}`, { responseType: 'text',headers })
   }

  
  //  addLand(newLand: Land): Observable<any> {
  //   const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
  //   return this.http.post(this.baseUrl, newLand, { responseType: 'text' });
  //  }

   addScheduler(newScheduler:Scheduler ): Observable<any> {
    const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
    return this.http.post(this.baseUrl, newScheduler, { responseType: 'text', headers });
    }



}
