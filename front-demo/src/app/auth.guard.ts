import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(): boolean {
    console.log('AuthGuard#canActivate called');
    console.log(this.router.url);
    console.log('localStorage.getItem(token)', localStorage.getItem('token'));

    if (typeof window !== 'undefined' && localStorage.getItem('token')) {
      return true;
    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }
}
