import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {path: 'persons',
  //loadChildren: () => import('./persons/listPersons/person.module').then(mod => mod.PersonModule)
   loadChildren: './persons/person.module#PersonModule'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

