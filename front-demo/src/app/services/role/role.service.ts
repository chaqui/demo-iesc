import { Injectable } from '@angular/core';
import { ApiCoreService } from '../api/api.core.service';
import Role from '../../models/role.model';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class RoleService {
  constructor(private apiService: ApiCoreService) {}

  public getRoles(): Observable<Role[]> {
    return this.apiService.get<Role[]>('/roles').pipe(
      map((roles: Role[]) => {
        return roles;
      })
    );
  }
}
