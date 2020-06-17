import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {UserModel} from '../shared/user.model';
import {BehaviorSubject} from 'rxjs';
import {AddressModel} from '../shared/address.model';

@Injectable()
export class UserService {
  baseURL = 'http://localhost:8080/secured/users/';
  users = 'allUsers';
  changeRole = 'admin/changeRole';
  delete = 'admin/deleteUser';
  newAddress = 'addAddress';
  deleteAddress = 'deleteAddress';

  private userSelectedSource = new BehaviorSubject(new UserModel());
  userSelected = this.userSelectedSource.asObservable();

  refreshUsers = new EventEmitter<any>();

  constructor(private http: HttpClient) { }

  postNewAddress(address: AddressModel, token: string) {
    const authHeader = {
      headers: new HttpHeaders({
        Authorization: 'Bearer ' + token,
        'Content-Type':  'application/json'
      })
    };

    console.log('postNewAddress');

    return this.http.post<boolean>(this.baseURL + this.newAddress,
      address, authHeader);
  }

  removeAddress(address: AddressModel, token: string) {
    const authHeader = {
      headers: new HttpHeaders({
        Authorization: 'Bearer ' + token,
        'Content-Type':  'application/json'
      })
    };

    return this.http.post<any>(this.baseURL + this.deleteAddress,
      address, authHeader);
  }

  deleteUser(user: UserModel, token: string) {
    const authHeader = {
      headers: new HttpHeaders({
        Authorization: 'Bearer ' + token
      })
    };

    return this.http.post<boolean>(this.baseURL + this.delete,
      user, authHeader);
  }

  postUserRole(user: UserModel, token: string) {
    const authHeader = {
      headers: new HttpHeaders({
        Authorization: 'Bearer ' + token,
        'Content-Type':  'application/json'
      })
    };

    return this.http.post<UserModel>(this.baseURL + this.changeRole,
      user, authHeader);
  }

  getUsers(token: string) {
    const authHeader = {
      headers: new HttpHeaders({
        Authorization: 'Bearer ' + token
      })
    };

    return this.http.get<UserModel[]>(this.baseURL + this.users,
      authHeader);
  }

  selectUser(user: UserModel) {
    this.userSelectedSource.next(user);
  }
}
