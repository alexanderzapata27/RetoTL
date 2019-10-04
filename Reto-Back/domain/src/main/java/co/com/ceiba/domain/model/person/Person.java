package co.com.ceiba.domain.model.person;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;

public class Person {
	private int identification;
	private String name;
	private String lastname;
	private LocalDate dateOfBirth;
	
	public Person(int identification, String name, String lastname, LocalDate dateOfBirth) {
		this.identification = identification;
		this.name = name;
		this.lastname = lastname;
		this.dateOfBirth = dateOfBirth;
	}
	
	public LocalDate getDateOfBirth() {
		return this.dateOfBirth;
	}
	
	public int getIdentification() {
		return identification;
	}

	public String getName() {
		return name;
	}

	public String getLastname() {
		return lastname;
	}

	@Override
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		return String.format("[Person: Identification=%s, name=%s, lastName=%s, dateOfBirth=%s", this.identification,this.name, this.lastname, format.format(this.dateOfBirth));
	}
	
	public int getAgeInYears() {
		LocalDate fechaNac = this.dateOfBirth;
		LocalDate ahora = LocalDate.now();
		Period periodo = Period.between(fechaNac, ahora);
		return periodo.getYears();
	}
	
}
