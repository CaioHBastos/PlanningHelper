import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { FileService } from '../../file.service';

@Component({
  selector: 'app-files-form',
  templateUrl: './files-form.component.html',
  styleUrls: ['./files-form.component.css'],
})
export class FilesFormComponent implements OnInit {
  mensagemSucesso: string;
  mensagemErro: string;

  constructor(private service: FileService, private router: Router) {}

  ngOnInit(): void {}

  gerarPokerPlanning() {
    this.service.gerarPokerPlanning().subscribe(
      (response) => {
        console.log('response');
        console.log(response.body);
      },
      (erro) => (this.mensagemErro = 'Ocorreu um erro ao gerar arquivo.')
    );
  }

  gerarJiraImporter() {
    this.service.gerarJiraImporter().subscribe(
      (response) => {
        console.log('response');
        console.log(response.body);
      },
      (erro) => (this.mensagemErro = 'Ocorreu um erro ao gerar arquivo.')
    );
  }

  gerarParameterizationFile() {
    this.service.gerarParameterizationFile().subscribe(
      (response) => {
        console.log('response');
        console.log(response.body);
      },
      (erro) => (this.mensagemErro = 'Ocorreu um erro ao gerar arquivo.')
    );
  }
}
