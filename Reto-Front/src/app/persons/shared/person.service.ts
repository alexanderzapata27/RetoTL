import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { environment } from 'src/environments/environment';
import { Person } from 'src/app/shared/model/person';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  url = environment.baseUrl;

  constructor(private http: HttpClient) { }

  obtainPersonsList(): Observable<Person[]> {
    return this.http.get<Person[]>(this.url + '/persons');
  }

  savePerson(person:Person): Observable<Person> {
    return this.http.post<Person>(this.url + '/persons',person);
  }

  updatePerson(person:Person): Observable<Person> {
    return this.http.put<Person>(this.url + '/persons',person);
  }

  getPersonByIdentification(identification:number): Observable<Person> {
    return this.http.get<Person>(this.url + '/persons/'+identification)
  }

  deletePerson(identification:number){
    return this.http.delete(this.url+'/persons/'+identification);
  }
}
