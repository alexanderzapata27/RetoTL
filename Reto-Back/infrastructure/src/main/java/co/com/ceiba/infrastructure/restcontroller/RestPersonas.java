package co.com.ceiba.infrastructure.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import co.com.ceiba.application.people.HandlerPeopleAdministration;
import co.com.ceiba.application.people.HandlerPeopleInformation;
import co.com.ceiba.domain.dto.person.PersonDTO;
import co.com.ceiba.domain.model.person.Person;

@RestController
@RequestMapping("/persons")
public class RestPersonas {
	
	private HandlerPeopleInformation handlerPeopleInformation;
	private HandlerPeopleAdministration handlerPeopleAdministration;
	
	@Autowired
	public RestPersonas(HandlerPeopleInformation peopleInformation, HandlerPeopleAdministration handlerPeopleAdministration) {
		this.handlerPeopleInformation = peopleInformation;
		this.handlerPeopleAdministration = handlerPeopleAdministration;
	}
	
	@GetMapping
	public List<PersonDTO> getPersonInformation() {
		return handlerPeopleInformation.execute();
	}
	
	@GetMapping(path = "/{identification}")
	public PersonDTO getPersonInformation(@PathVariable("identification") int identification) {
		return handlerPeopleInformation.execute(identification);
	}
	
	@PostMapping
	public Person savePersonInformation(@RequestBody String personJson) throws Exception{
		Gson gson = new Gson();
		PersonDTO person = gson.fromJson(personJson, PersonDTO.class);
		return handlerPeopleAdministration.executeCreation(person);
	}
	
	@DeleteMapping(path = "/{identification}")
	public void deletePerson(@PathVariable("identification") int identification) throws Exception {
		handlerPeopleAdministration.executeDelete(identification);
	}
}
