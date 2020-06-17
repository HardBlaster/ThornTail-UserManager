import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs';

@Injectable()
export class TransportService {
  private tokenSource = new BehaviorSubject('');
  currentToken = this.tokenSource.asObservable();
  private roleSource = new BehaviorSubject('');
  currentRole = this.roleSource.asObservable();
  private onAddressPage = new BehaviorSubject(true);
  currentlyOnAddressPage = this.onAddressPage.asObservable();

  constructor() { }

  changeToken(token: string) {
    this.tokenSource.next(token);
  }

  changeRole(role: string) {
    this.roleSource.next(role);
  }

  toAddressPage(status: boolean) {
    this.onAddressPage.next(status);
  }
}
