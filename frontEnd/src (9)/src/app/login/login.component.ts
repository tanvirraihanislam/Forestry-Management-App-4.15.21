import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../authentication.service';
import { AppComponent } from '../app.component';
import { bufferToggle } from 'rxjs/operators';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  invalidLogin: boolean;
  message: string;
  constructor(
    private router: Router,
    private appComp: AppComponent,
    private authenticationService: AuthenticationService) { }

 

  ngOnInit() {
  }

  signIn(credentials) {
    this.authenticationService.login(credentials)
      .subscribe(result => {
        this.router.navigate(['/']);
        
      },
         fail => {
          this.invalidLogin = true;
          this.appComp.toggle()
          this.message = fail.error.errorMessage;
        }
      );

 
  }
  showAll(){
    this.appComp.toggle()
  } 



}
