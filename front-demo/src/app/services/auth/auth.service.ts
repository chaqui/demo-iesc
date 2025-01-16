import { Injectable } from '@angular/core';
import { ApiCoreService } from '../api/api.core.service';
import Login from '../../models/login.model';
import Auth from '../../models/auth.model';
import { Api } from '../../constants/Api.constants';
import Register from '../../models/register.model';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private apiService: ApiCoreService, private router: Router) {}

  login(loginData: Login) {
    this.apiService.login(loginData).subscribe(
      (response: Auth) => {
        console.log(response);
        localStorage.setItem('token', response.token);
        this.router.navigate(['/']);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  register(registerData: Register) {
    this.apiService.post<Register>(Api.REGISTER, registerData).subscribe(
      (response: Register) => {
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
