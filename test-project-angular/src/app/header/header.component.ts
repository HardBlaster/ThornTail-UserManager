import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {TransportService} from '../shared/transport.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private router: Router,
              private transportService: TransportService) { }

  onSelectAddresses() {
    this.transportService.toAddressPage(true);
  }

  onSelectUsers() {
    this.transportService.toAddressPage(false);
  }

  onLogout() {
    this.transportService.changeToken('');
    this.router.navigate(['/']);
  }

  ngOnInit(): void {
  }

}
