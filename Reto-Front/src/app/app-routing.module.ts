import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { LogoComponent } from './shared/logo/logo.component';
import { DescriptionComponent } from './information/description/description.component';
import { ResultsComponent } from './information/results/results.component';

const routes: Routes = [
  {path: '', component: LogoComponent
  },
  {path: 'persons',
   loadChildren: './persons/person.module#PersonModule'
  },
  {
    path: 'description', component: DescriptionComponent
  },
  {
    path: 'results', component: ResultsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

