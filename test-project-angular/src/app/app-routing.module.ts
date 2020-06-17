import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AuthComponent} from './auth/auth.component';
import {AddressEditComponent} from './addresses/address-edit/address-edit.component';
import {AddressDetailComponent} from './addresses/address-detail/address-detail.component';
import {AddressesComponent} from './addresses/addresses.component';
import {UsersComponent} from './users/users.component';
import {UserDetailComponent} from './users/user-detail/user-detail.component';

const routes: Routes = [
  {path: '', redirectTo: '/auth', pathMatch: 'full'},
  {path: 'auth', component: AuthComponent},
  {path: 'addresses', component: AddressesComponent, children: [
      {path: 'addressDetail', component: AddressDetailComponent},
      {path: 'editAddress', component: AddressEditComponent},
      {path: 'addAddress', component: AddressEditComponent}
    ]},
  {path: 'users', component: UsersComponent, children: [
      {path: 'userDetail', component: UserDetailComponent}
    ]},
  {path: '**', redirectTo: '/auth'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
