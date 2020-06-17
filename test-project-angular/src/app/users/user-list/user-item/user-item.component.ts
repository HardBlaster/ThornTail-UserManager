import {Component, Input, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {UserModel} from '../../../shared/user.model';
import {UserService} from '../../user.service';

@Component({
  selector: 'app-user-item',
  templateUrl: './user-item.component.html',
  styleUrls: ['./user-item.component.css']
})
export class UserItemComponent implements OnInit {
  @Input() user: UserModel;

  constructor(private userService: UserService,
              private router: Router) { }

  onSelected() {
    this.userService.selectUser(this.user);
    this.router.navigate(['/users', 'userDetail']);
  }

  ngOnInit(): void {
  }

}
