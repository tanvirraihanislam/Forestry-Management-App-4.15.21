import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
) { }

canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    
  if (this.authenticationService.isLoggedIn()) {
      // logged in so return true
      
      return true;
  }

  // not logged in so redirect to login page with the return url
  this.router.navigate(['/login']);
  return false;
}




}



