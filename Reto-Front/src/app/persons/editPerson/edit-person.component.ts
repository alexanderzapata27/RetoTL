import { Component, OnInit } from '@angular/core';
import { PersonService } from '../shared/person.service';
import { Person } from '../../shared/model/person';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-edit-person',
  templateUrl: './edit-person.component.html',
  styleUrls: ['./edit-person.component.css']
})
export class EditPersonComponent implements OnInit {
  title: String = 'Actualización de personas';
  savedSuccessMessage: String = 'La persona ha sido actualizada exitosamente!';
  errorPersonMessage: String = 'La persona ha sido actualizada exitosamente!';
  personSaved: Person;
  personToSave: Person;
  showSuccess: boolean;
  showFail: boolean;
  timeForMessages: number = 2000;
  timeForErrorMessages: number = 4000;
  personIdentification: number;
  constructor(private personService: PersonService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.personIdentification = this.route.snapshot.params['identification'];
    this.findPersonByIdentification(this.personIdentification);
    this.personSaved = new Person();
    this.personToSave = new Person();
    this.showSuccess = false;
    this.showFail = false;
  }

  savePerson(){
    this.personService.updatePerson(this.personToSave).subscribe(
      res => {
        this.personSaved = res;
        this.cleanInputs();
        this.showSuccessMessage();
      },(err) => {
        this.showFailMessage();
      }
    );
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
    this.personService.getPersonByIdentification(this.personIdentification).subscribe(
      res => {
        this.personToSave = res;
      }
    );
  }
}