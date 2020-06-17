import {Component, OnInit} from '@angular/core';
import {AddressService} from '../address.service';
import {AddressModel} from '../../shared/address.model';
import {TransportService} from '../../shared/transport.service';
import {ActivatedRoute} from '@angular/router';
import {UserService} from '../../users/user.service';

@Component({
  selector: 'app-address-edit',
  templateUrl: './address-edit.component.html',
  styleUrls: ['./address-edit.component.css']
})
export class AddressEditComponent implements OnInit {
  address: AddressModel;
  backupAddress: AddressModel;
  token: string;

  constructor(private addressService: AddressService,
              private transportService: TransportService,
              private userService: UserService,
              private route: ActivatedRoute) {
  }

  onSave() {
    if (this.route.snapshot.url[0].path === 'addAddress') {

      this.userService.postNewAddress(this.address, this.token)
        .subscribe(() => {
          this.addressService.refreshAddresses.emit(null);
        });
      this.address = new AddressModel();

    } else {

      this.addressService.postEditedAddress(this.backupAddress, this.address, this.token)
        .subscribe(() => {
          this.addressService.refreshAddresses.emit(null);
        });
      this.backupAddress = null;
      this.address = new AddressModel();

    }
  }

  onCancel() {
    this.address = new AddressModel();
    this.backupAddress = null;
  }

  ngOnInit(): void {
    this.address = new AddressModel();
    this.backupAddress = null;

    this.addressService.editMode.subscribe(
      address => {
        this.address = {...address};
        this.backupAddress = {...address};
      }
    );

    this.transportService.currentToken.subscribe(
      token => {
        this.token = token;
      }
    );
  }

}
