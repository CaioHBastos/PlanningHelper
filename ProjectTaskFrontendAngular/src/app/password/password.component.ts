import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { AuthService } from '../auth.service';
import { UserAuth } from '../login/userAuth';
import { UserPassword } from '../login/userPassword';

@Component({
  selector: 'app-password',
  templateUrl: './password.component.html',
  styleUrls: ['./password.component.css'],
})
export class PasswordComponent implements OnInit {
  id: string;
  password: string;
  successMessage: string;
  errors: String[];
  errorMessage: string;
  user: UserPassword;

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private http: HttpClient,
    private authService: AuthService
  ) {
    this.user = new UserPassword();
  }

  ngOnInit(): void {
    let params: Params = this.activatedRoute.params;

    if (params && params.value && params.value.id) {
      this.id = params.value.id;
    }
  }

  onSubmit() {
    this.authService.handleChangePassword(this.id, this.user).subscribe(
      (response) => {
        this.successMessage = 'Senha alterada com sucesso! Efetue o login';
        this.router.navigate(['/']);
        console.log(this.user);
      },
      (errorResponse) => {
        this.errorMessage = 'Não foi possível alterar a senha';
        this.errors = ['Usuário e/ou senha incorretos'];
        console.log(errorResponse);
        console.log(this.user);
      }
    );
  }

  cancelaResetar() {
    this.router.navigate(['/']);
  }
}
