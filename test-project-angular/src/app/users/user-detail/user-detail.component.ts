import { Component, OnInit } from '@angular/core';
import {UserModel} from '../../shared/user.model';
import {UserService} from '../user.service';
import {TransportService} from '../../shared/transport.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {
  selectedUser: UserModel;
  inRole: string;
  token: string;

  constructor(private userService: UserService,
              private transportService: TransportService) { }

  onChangeRole() {
    if (this.selectedUser.role === 'user') {
      this.selectedUser.role = 'admin';
    } else {
      this.selectedUser.role = 'user';
    }

    this.userService.postUserRole(this.selectedUser, this.token)
      .subscribe(
        user => {
          this.selectedUser = user;
        });
  }

  onDelete() {
    this.userService.deleteUser(this.selectedUser, this.token)
      .subscribe(
        success => {
          if (success) {
            this.userService.refreshUsers.emit(null);
          }});
  }

  ngOnInit(): void {
    this.selectedUser = new UserModel();
    this.userService.userSelected.subscribe(
      user => this.selectedUser = user
    );

    this.transportService.currentRole.subscribe(
      role => this.inRole = role
    );

    this.transportService.currentToken.subscribe(
      token => this.token = token
    );
  }

}
