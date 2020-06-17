import {Component, Input, OnInit} from '@angular/core';
import {AddressModel} from '../../../shared/address.model';
import {AddressService} from '../../address.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-address-item',
  templateUrl: './address-item.component.html',
  styleUrls: ['./address-item.component.css']
})
export class AddressItemComponent implements OnInit {
  @Input() address: AddressModel;

  constructor(private addressService: AddressService,
              private router: Router) { }

  onSelected() {
    this.addressService.selectAddress(this.address);
    this.router.navigate(['/addresses', 'addressDetail']);
  }

  ngOnInit(): void {
  }

}
