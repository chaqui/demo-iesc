import { Routes } from '@angular/router';
import { LoginViewComponent } from './views/login-view/login-view.component';
import { AuthGuard } from './auth.guard';
import { HomeComponent } from './views/home/home.component';
import { ClientsViewComponent } from './views/clients-view/clients-view.component';
import { PagosViewComponent } from './views/pagos-view/pagos-view.component';
export const routes: Routes = [
  { path: 'login', component: LoginViewComponent },
  { path: '', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'clients', component: ClientsViewComponent, canActivate: [AuthGuard] },
  { path: 'payments', component: PagosViewComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: '' }];
