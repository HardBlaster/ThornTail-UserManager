import {Component, OnInit} from '@angular/core';
import {UserModel} from '../../shared/user.model';
import {UserService} from '../user.service';
import {TransportService} from '../../shared/transport.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users: UserModel[];
  token: string;

  constructor(private userService: UserService,
              private transportService: TransportService) {
  }

  refreshUsers(token: string) {
    this.userService.getUsers(token).subscribe(
      users => this.users = users
    );
  }

  ngOnInit(): void {
    this.transportService.currentToken.subscribe(
      token => {
        this.token = token;
        this.refreshUsers(token);
      }
    );

    this.userService.refreshUsers.subscribe(
      () => this.refreshUsers(this.token)
    );
  }

}
