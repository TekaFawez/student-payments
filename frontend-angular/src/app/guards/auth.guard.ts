import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanActivateFn,
  GuardResult,
  MaybeAsync, Router,
  RouterStateSnapshot
} from '@angular/router';
import { retry } from 'rxjs';
import { AuthService } from '../services/auth.service';
// export const authGuard: CanActivateFn = (route, state) => {
//   return true;
// };
@Injectable()
export class AuthGuard {
  constructor(public auth : AuthService,private router:Router){}
  
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): MaybeAsync<GuardResult> {
    if(this.auth.isAuthenticated){
      return true

    }else {
    this.router.navigateByUrl("/login")
    return false;
    }
  }

}
