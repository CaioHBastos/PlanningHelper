import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';
import { UserService } from '../../user.service';
import { UserAuth } from '../../login/userAuth';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
})
export class SidebarComponent implements OnInit {
  //userAuthenticated: string;
  user: UserAuth;
  jwtHelper: JwtHelperService = new JwtHelperService();
  constructor(
    private authService: AuthService,
    private userService: UserService,
    private router: Router
  ) {
    this.user = new UserAuth();
  }

  ngOnInit(): void {
    const tokenString = localStorage.getItem('token');

    const token = JSON.parse(tokenString).token;
    const userAuthenticated = this.jwtHelper.decodeToken(token).sub;

    this.userService.getUserById(userAuthenticated).subscribe(
      (response) => {
        this.user = response;
      },

      (errorResponse) => (this.user = new UserAuth())
    );

    console.log('this.user');
    console.log(this.user);
  }

  //findUser() {
  //  if (this.userAuthenticated) {
  //    this.userService.getUserById(this.userAuthenticated).subscribe(
  //      (response) => (this.user = response),
  //      (errorResponse) => (this.user = new UserAuth())
  //    );
  //  }
  //
  //  console.log(this.user);
  //}

  logout() {
    this.authService.signOut();
    this.router.navigate(['']);
  }
}
