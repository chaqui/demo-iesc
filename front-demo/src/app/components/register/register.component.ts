import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import Register from '../../models/register.model';
import Role from '../../models/role.model';
import { RoleService } from '../../services/role/role.service';

@Component({
  selector: 'app-register',
  imports: [],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent implements OnInit {
  public register: Register = {
    name: '',
    password: '',
    roleId: 0,
  };

  public roles: Role[] = [];

  constructor(private authService: AuthService,
    private roleService: RoleService
  ) {}
  ngOnInit(): void {
    this.getRoles();
  }

  /**
   * Get all roles
   */
  private getRoles() {
    this.roleService.getRoles().subscribe(
      (response: Role[]) => {
        this.roles = response;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  public onSubmit() {
    this.authService.register(this.register);
  }
}
