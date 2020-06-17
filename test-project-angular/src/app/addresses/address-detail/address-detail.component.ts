import {Component, Input, OnInit} from '@angular/core';
import {AddressModel} from '../../shared/address.model';
import {AddressService} from '../address.service';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../../users/user.service';
import {TransportService} from '../../shared/transport.service';

@Component({
  selector: 'app-address-detail',
  templateUrl: './address-detail.component.html',
  styleUrls: ['./address-detail.component.css']
})
export class AddressDetailComponent implements OnInit {
  selectedAddress: AddressModel;
  token: string;

  constructor(private addressService: AddressService,
              private userService: UserService,
              private transportService: TransportService,
              private router: Router) {
  }

  onEdit() {
    this.addressService.toggleEdit(this.selectedAddress);
    this.router.navigate(['/addresses', 'editAddress']);
  }

  onDelete() {
    this.userService.removeAddress(this.selectedAddress, this.token)
      .subscribe((success) => {
        console.log(success);
        this.addressService.refreshAddresses.emit(null);
      });
  }

  ngOnInit(): void {
    this.selectedAddress = new AddressModel();
    this.addressService.addressSelected.subscribe(
      address => this.selectedAddress = address);

    this.transportService.currentToken.subscribe(
      token => {
        this.token = token;
      }
    );
  }

}
