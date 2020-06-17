import {Component, OnInit} from '@angular/core';
import {AddressModel} from '../../shared/address.model';
import {AddressService} from '../address.service';
import {TransportService} from '../../shared/transport.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-address-list',
  templateUrl: './address-list.component.html',
  styleUrls: ['./address-list.component.css']
})
export class AddressListComponent implements OnInit {
  addresses: AddressModel[];
  token: string;

  constructor(private addressService: AddressService,
              private transportService: TransportService,
              private router: Router) { }

  onNewAddress() {
    this.router.navigate(['/addresses', 'addAddress']);
  }

  refreshAddresses(token: string) {
    this.addressService.getAddresses(token)
      .subscribe(data => {
        this.addresses = data;
      });
  }

  ngOnInit(): void {
    this.transportService.currentToken
      .subscribe(
        token => {
          this.token = token;
          this.refreshAddresses(token);
        });

    this.addressService.refreshAddresses
      .subscribe(
        () => this.refreshAddresses(this.token)
      );
  }

}
