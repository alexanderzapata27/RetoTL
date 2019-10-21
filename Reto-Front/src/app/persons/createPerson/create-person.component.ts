import { Component, OnInit } from '@angular/core';
import { PersonService } from '../shared/person.service';
import { Person } from '../../shared/model/person';
//import * as $ from 'jquery';


@Component({
  selector: 'app-create-person',
  templateUrl: './create-person.component.html',
  styleUrls: ['./create-person.component.css']
})
export class CreatePersonComponent implements OnInit {
  title: String = 'Creación de personas';
  savedSuccessMessage: String = 'La persona ha sido guardada exitosamente!';
  errorPersonMessage: String = 'La persona se encuentra repetida, por favor editala';
  personSaved: Person;
  personToSave: Person;
  found:boolean;
  showSuccess: boolean;
  showFail: boolean;
  timeForMessages: number = 2000;
  timeForErrorMessages: number = 4000;
  personIdentification: number;
  showModalUserRepeated:boolean;
  constructor(private personService: PersonService) { }

  ngOnInit() {
    this.showModalUserRepeated = false;
    this.personSaved = new Person();
    this.personToSave = new Person();
    this.showSuccess = false;
    this.showFail = false;
  }

  savePerson(){
    this.personService.savePerson(this.personToSave).subscribe(
      res => {
        this.personSaved = res;
        this.validateResponse();
      }
    );
  }

  validateResponse(){
    if(null!=this.personSaved.error){
      this.errorPersonMessage = this.personSaved.error.message;
      this.showFailMessage();
    }else{
      this.cleanInputs();
      this.showSuccessMessage();
    }
  }

  async showSuccessMessage(){
    this.showSuccess = true;
    await this.delay(this.timeForMessages);
    this.showSuccess = false;
  }

  async showFailMessage(){
    this.showFail = true;
    await this.delay(this.timeForErrorMessages);
    this.showFail = false;
  }

  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }

  cleanInputs(){
    this.personToSave.identification = null;
    this.personToSave.name = "";
    this.personToSave.lastname = "";
    this.personToSave.dateOfBirth = null;
  }

  findPersonByIdentification(identification:number){
    this.personService.getPersonByIdentification(identification).subscribe(
      res => {
        this.personToSave = res;
        if(null!=this.personToSave.identification){
          this.showModalUserRepeated = true;
          //$('#modalUser').fadeIn();
        }
      }
    );
  }

}