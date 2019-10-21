import { Component, OnInit } from '@angular/core';
import { PersonService } from '../shared/person.service';
import { Person } from 'src/app/shared/model/person';

@Component({
  selector: 'person-list-component',
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.css']
})
export class PersonComponent implements OnInit{
  public title: String = 'Listado de personas';
  deleteMessage: String = 'La persona ha sido eliminada exitosamente!';
  persons: Person[];
  showMessageBool: any = false;
  timeForMessages: number = 2000;
  constructor(private personService: PersonService){}

  ngOnInit(){
    this.obtainPersonsList();
  }

  obtainPersonsList(){
    this.personService.obtainPersonsList().subscribe(
      res => {
        this.persons = res;
      }
    );
  }

  deletePerson(identification:number){
    this.personService.deletePerson(identification).subscribe(
      res => {
        this.showMessage();
      } ,(err) => {
        this.deleteMessage = "Ha ocurrido un error eliminando la persona";
        this.showMessage();
        this.obtainPersonsList();
      });
  }

  async showMessage(){
    this.showMessageBool = true;
    await this.delay(this.timeForMessages);
    this.showMessageBool = false;
  }

  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }
}
