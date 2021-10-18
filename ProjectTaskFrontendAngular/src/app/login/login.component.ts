import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { User } from './user';
import { UserAuth } from './userAuth';
declare var $: any;
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  email: string;
  password: string;
  name: string;
  answer: string;
  question: string;
  cadastrando: boolean;
  successMessage: string;
  errorMessage: string;
  errors: String[];
  user: User;
  userAuth: UserAuth;

  constructor(private router: Router, private authService: AuthService) {
    this.user = new User();
    this.userAuth = new UserAuth();
  }

  onSubmit() {
    const user: User = new User();

    user.email = this.email;
    user.password = this.password;
    this.authService.handleLogin(user).subscribe(
      (response) => {
        const token = JSON.stringify(response);
        localStorage.setItem('token', token);
        this.router.navigate(['/squad/addTeam']);
      },
      (errorResponse) => {
        this.errors = ['Usuário e/ou senha incorretos'];
      }
    );
  }

  preparaCadastrar(event) {
    event.preventDefault();
    this.cadastrando = true;
  }

  cancelaCadastro() {
    this.cadastrando = false;
  }

  create() {
    const user: User = new User();

    user.email = this.email;
    user.password = this.password;
    user.name = this.name;
    user.answer = this.answer;
    user.question = this.question;

    this.authService.save(user).subscribe(
      (response) => {
        this.successMessage = 'Cadastro efetuado com sucesso! Efetue o login';
        this.cadastrando = false;
        this.email = '';
        this.password = '';
        this.name = '';
        this.answer = '';
        this.question = '';
        this.errors = [];
      },
      (errorResponse) => {
        this.successMessage = null;
        this.errors = errorResponse.error.errors;
      }
    );
  }

  onSubmitForgot() {
    const user: User = new User();

    user.answer = this.answer;
    user.question = this.question;

    this.authService.handleForgotPassword(user).subscribe(
      (response) => {
        this.successMessage = response;
        this.answer = '';
        this.question = '';
        this.errors = [];
        this.userAuth = response.body;
        $('#modalForgotPassword').modal('hide');
        this.router.navigate([`/reset-password/${this.userAuth.id}`]);
      },
      (errorResponse) => {
        this.successMessage = null;
        this.errors = errorResponse.error.errors;
        this.errorMessage =
          'Pergunta e resposta não correspondem ao cadastrado';
        setTimeout(() => (this.successMessage = null), 2000);
        $('#modalForgotPassword').modal('hide');
      }
    );
  }
}
