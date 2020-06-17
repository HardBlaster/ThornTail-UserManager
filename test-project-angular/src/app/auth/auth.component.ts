import {Component, OnInit} from '@angular/core';
import {UserModel} from '../shared/user.model';
import {AuthService} from './auth.service';
import {HttpClient, HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Router} from '@angular/router';
import {TransportService} from '../shared/transport.service';
import {catchError} from 'rxjs/operators';
import {throwError} from 'rxjs';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {
  register: boolean;
  user: UserModel;
  message: string;
  msgVisible: boolean;

  constructor(private authService: AuthService,
              private router: Router,
              private transportService: TransportService) {
  }

  auth(): void {
    console.log(this.user);
    if (this.register) {

      this.authService.register(this.user)
        .subscribe(data => {
            if (data) {

              this.user = new UserModel();
              this.message = 'Registered sucessfully!';
              this.msgVisible = true;

            }
          },
          error => {

            this.message = 'Something went wrong...\n' + error;
            this.msgVisible = true;

          });

    } else {
      this.authService.login(this.user)
        .subscribe(
          token => {
            this.transportService.changeToken(token);

            this.authService.getRole(token).subscribe(
              role => {
                this.transportService.changeRole(role);
              });

            this.router.navigate(['/', 'addresses']);
          },
          error => {
            this.message = 'Something went wrong...\n' + error;
            this.msgVisible = true;
          });
    }
  }

  onFormChange() {
    this.register = !this.register;
    this.msgVisible = false;
    this.user = new UserModel();
  }

  ngOnInit(): void {
    this.register = false;
    this.user = new UserModel();
    this.msgVisible = false;
  }

}
