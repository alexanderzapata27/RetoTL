package co.com.ceiba.infrastructure.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import co.com.ceiba.application.people.HandlerPeopleInformation;
import co.com.ceiba.domain.model.person.Person;

@RestController
@RequestMapping("/persons")
public class RestPersonas {
	
	private HandlerPeopleInformation handlerPeopleInformation;
	
	@Autowired
	public RestPersonas(HandlerPeopleInformation peopleInformation) {
		this.handlerPeopleInformation = peopleInformation;
	}
	
	@GetMapping
	public List<Person> getPersonInformation() {
		return handlerPeopleInformation.execute();
	}
	
	@GetMapping(path = "/{identification}")
	public Person getPersonInformation(@PathVariable("identification") int identification) {
		return handlerPeopleInformation.execute(identification);
	}
	
	@PostMapping
	public void savePersonInformation(@RequestBody String personJson){
		Gson gson = new Gson();
		Person person = gson.fromJson(personJson, Person.class);
//		personInformationService.saveDataFromPerson(person);
	}
}
