import { NgModule } from '@angular/core';

import { PersonRoutingModule } from './person-routing.module';
import { PersonComponent } from './listPersons/person.component';

@NgModule({
  declarations: [
    PersonComponent
  ],
  imports: [
    PersonRoutingModule
  ],
  providers: [],
  bootstrap: [PersonComponent]
})
export class PersonModule { }
