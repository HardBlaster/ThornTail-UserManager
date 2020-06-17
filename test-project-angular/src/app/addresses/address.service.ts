import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AddressModel} from '../shared/address.model';
import {BehaviorSubject} from 'rxjs';

@Injectable()
export class AddressService {
  baseURL = 'http://localhost:8080/secured/address/';
  userAddresses = 'addresses';
  modifyAddress = 'modifyAddress';

  private addressSelectedSource = new BehaviorSubject(new AddressModel());
  addressSelected = this.addressSelectedSource.asObservable();

  editModeToggle = new BehaviorSubject(new AddressModel());
  editMode = this.editModeToggle.asObservable();

  refreshAddresses = new EventEmitter<any>();

  constructor(private http: HttpClient) { }

  postEditedAddress(oldAddress: AddressModel, newAddress: AddressModel, token: string) {
    const authHeader = {
      headers: new HttpHeaders({
        Authorization: 'Bearer ' + token,
        'Content-Type':  'application/json'
      })
    };

    const addresses: AddressModel[] = [oldAddress, newAddress];
    return this.http.post<boolean>(this.baseURL + this.modifyAddress,
      addresses, authHeader);
  }

  getAddresses(token: string) {
    const authHeader = {
      headers: new HttpHeaders({
        Authorization: 'Bearer ' + token
      })
    };

    console.log('getAddresses');

    return this.http.post<AddressModel[]>(this.baseURL + this.userAddresses,
      null, authHeader);
  }

  selectAddress(address: AddressModel) {
    this.addressSelectedSource.next(address);
  }

  toggleEdit(address: AddressModel) {
    this.editModeToggle.next(address);
  }
}
