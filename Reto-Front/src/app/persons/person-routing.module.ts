import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {PersonComponent} from './listPersons/person.component';

const routes: Routes = [
    {
        path: 'listPersons',
        component: PersonComponent
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PersonRoutingModule {}