import { Component } from '@angular/core';

@Component({
  selector: 'person-component',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent {
  public title: String = 'Componente de la lista de personas';
}
