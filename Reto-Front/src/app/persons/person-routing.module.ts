import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CreatePersonComponent } from './createPerson/create-person.component';
import { EditPersonComponent } from './editPerson/edit-person.component';
import { PersonComponent } from './listPersons/person-list.component';

const routes: Routes = [
    {
      path: 'listPersons',
      component: PersonComponent
    },
    {
      path: 'createPerson',
      component: CreatePersonComponent
    },
    {
      path: 'editPerson/:identification',
      component: EditPersonComponent
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PersonRoutingModule {}