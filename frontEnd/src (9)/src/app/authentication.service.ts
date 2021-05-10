import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { tokenNotExpired } from 'angular2-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) { }

  login(credentials) {
    return this.http.post<any>('http://localhost:8083/authenticate', credentials).pipe(
      map(
        response => {
          if (response && response.token) {
            console.log(response.token);
            let tokenStr = 'Bearer ' + response.token;
            localStorage.setItem("role",response.role)
            
            localStorage.setItem('token', tokenStr);
            console.log(localStorage.getItem("role"));
            return response;
          }
        },
        error => error

      )
    );
  }

  logout() {
    localStorage.removeItem('token');
  }

  isLoggedIn() {
    let token = localStorage.getItem('token');

    if (!token)
      return false;

    return tokenNotExpired(null, token);
  }



}
