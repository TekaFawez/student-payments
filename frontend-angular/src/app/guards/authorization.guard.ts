import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanActivateFn,
  GuardResult,
  MaybeAsync, Router,
  RouterStateSnapshot
} from '@angular/router';
import { AuthService } from '../services/auth.service';
// export const authGuard: CanActivateFn = (route, state) => {
//   return true;
// };
@Injectable()
export class AuthorizationGuard   {
  constructor(public auth : AuthService,private router:Router){}
  
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): MaybeAsync<GuardResult> {
    if(this.auth.isAuthenticated){
      let authUserRoles=this.auth.roles
      let requiredRoles=route.data['roles']
      for(let role of authUserRoles){
        if(requiredRoles.includes(role)){
          return true
        }
      }
      return false

    }else {
    this.router.navigateByUrl("/login")
    return false;
    }
  }

}
