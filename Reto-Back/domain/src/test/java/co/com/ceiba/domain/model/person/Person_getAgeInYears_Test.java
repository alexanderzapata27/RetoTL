package co.com.ceiba.domain.model.person;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;


public class Person_getAgeInYears_Test {

	Person person;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAgeInYears() {
		//Arrange
		int identification = 98989898;
		String name = "Pepito";
		String lastname = "Perez";
		LocalDate dateOfBirth = LocalDate.of(1980, 05, 22);
		person = new Person(identification, name, lastname, dateOfBirth);
		//Act
		int age = person.getAgeInYears();
		//Assert
		Assert.assertEquals(39, age);
	}

}
