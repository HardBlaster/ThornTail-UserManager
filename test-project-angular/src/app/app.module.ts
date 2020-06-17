import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AuthComponent } from './auth/auth.component';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {AuthService} from './auth/auth.service';
import { AddressDetailComponent } from './addresses/address-detail/address-detail.component';
import { AddressListComponent } from './addresses/address-list/address-list.component';
import { AddressItemComponent } from './addresses/address-list/address-item/address-item.component';
import {AddressService} from './addresses/address.service';
import { AppRoutingModule } from './app-routing.module';
import {TransportService} from './shared/transport.service';
import {UserService} from './users/user.service';
import { AddressEditComponent } from './addresses/address-edit/address-edit.component';
import { DropdownDirective } from './shared/dropdown.directive';
import { HeaderComponent } from './header/header.component';
import { AddressesComponent } from './addresses/addresses.component';
import {UsersComponent} from './users/users.component';
import {UserItemComponent} from './users/user-list/user-item/user-item.component';
import {UserDetailComponent} from './users/user-detail/user-detail.component';
import {UserListComponent} from './users/user-list/user-list.component';

@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    AddressDetailComponent,
    AddressListComponent,
    AddressItemComponent,
    AddressEditComponent,
    DropdownDirective,
    HeaderComponent,
    AddressesComponent,
    UsersComponent,
    UserListComponent,
    UserDetailComponent,
    UserItemComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [ AuthService,
               AddressService,
               TransportService,
               UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
