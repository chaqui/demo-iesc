import { Injectable } from '@angular/core';

import { Api } from '../../constants/Api.constants';
import Client from '../../models/client.model';
import { ApiCoreService } from '../api/api.core.service';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private apiService: ApiCoreService) { }

  public getClients() {
    return this.apiService.get<Client[]>(Api.CLIENTS);
  }

  public saveClient(client: Client) {
    return this.apiService.post<Client>(Api.CLIENTS, client);
  }
}
