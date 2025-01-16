import { Component, OnInit } from '@angular/core';
import { ClientService } from '../../services/client/client.service';
import Client from '../../models/client.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-clients',
  imports: [CommonModule],
  standalone: true,
  templateUrl: './clients.component.html',
  styleUrl: './clients.component.css'
})
export class ClientsComponent implements OnInit {

  constructor(private service: ClientService) {}


  public clients: Client[] = [];

  ngOnInit(): void {
    this.service.getClients().subscribe((clients) => {
      this.clients = clients;
    });
  }


}
