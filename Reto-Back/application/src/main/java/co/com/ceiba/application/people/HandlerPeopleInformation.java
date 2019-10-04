package co.com.ceiba.application.people;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.domain.model.person.Person;
import co.com.ceiba.domain.service.person.ObtainPersonInformationService;
import co.com.ceiba.domain.service.person.ObtainPersonsInformationListService;

@Component
public class HandlerPeopleInformation {
	
	ObtainPersonInformationService obtainPersonInfoService;
	ObtainPersonsInformationListService obtainPersonsListService;
	
	@Autowired
	public HandlerPeopleInformation(ObtainPersonInformationService obtainPersonInfoService, ObtainPersonsInformationListService obtainPersonsListService) {
		this.obtainPersonInfoService = obtainPersonInfoService;
		this.obtainPersonsListService = obtainPersonsListService;
	}
	
	public Person execute(int identification) {
		return obtainPersonInfoService.execute(identification);
	}
	
	public List<Person> execute() {
		return obtainPersonsListService.execute();
	}
}
