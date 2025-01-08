import { Component } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { FormsModule } from '@angular/forms';
import Login from '../../models/login.model';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  standalone: true,
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  public login: Login = {
    name: '',
    password: '',
  }

  constructor(private authService: AuthService) {}

  public onSubmit() {
    this.authService.login(this.login);

  }


}
