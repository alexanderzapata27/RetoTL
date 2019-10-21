import { CUSTOM_ELEMENTS_SCHEMA,NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PersonRoutingModule } from './person-routing.module';
import { PersonService } from './shared/person.service';
import { CreatePersonComponent } from './createPerson/create-person.component';
import { FormsModule }   from '@angular/forms';
import { EditPersonComponent } from './editPerson/edit-person.component';
import { RegisterCacheService } from '../shared/interceptor/register-cache.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { InterceptorUriCache } from '../shared/interceptor/interceptor-uri-cache';
import { PersonComponent } from './listPersons/person-list.component';

@NgModule({
  declarations: [
    PersonComponent,
    CreatePersonComponent,
    EditPersonComponent
  ],
  imports: [
    PersonRoutingModule,
    CommonModule,
    FormsModule
  ],
  providers: [
    RegisterCacheService,
    { provide: HTTP_INTERCEPTORS, useClass: InterceptorUriCache, multi: true }
  ],
  bootstrap: [PersonComponent],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ]
})
export class PersonModule { }
