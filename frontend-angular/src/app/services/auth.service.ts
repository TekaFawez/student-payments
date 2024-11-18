import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isAuthenticated=false
 public roles :string[]=[];
  username:any;
  public users : any= {
    admin:{password:'1234',roles:['ADMIN']},
    user1:{password:'1234',roles:['STUDENT']},


  }

  constructor(private router:Router) { }
  public login(username:string,password:string){
    if(this.users[username] && password==this.users[username].password){
      this.username=username
      this.isAuthenticated=true
      this.roles=this.users[username].roles
      return true;
    } else{
      return false
    }

  }

  logout() {
    this.isAuthenticated=false
    this.username=undefined
    this.roles=[]
    this.router.navigateByUrl("/login")
  }
}
