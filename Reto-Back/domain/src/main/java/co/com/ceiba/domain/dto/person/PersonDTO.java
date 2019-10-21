package co.com.ceiba.domain.dto.person;

public class PersonDTO {
	private long id;
	private int identification;
	private String name;
	private String lastname;
	private String dateOfBirth;
	private int age;
	private ErrorDTO error;
	
	public PersonDTO(int identification, String name, String lastname, String dateOfBirth, int age) {
		super();
		this.identification = identification;
		this.name = null==name?"":name;
		this.lastname = null==lastname?"":lastname;
		this.dateOfBirth = null==dateOfBirth?"":dateOfBirth;
		this.age = age;
	}
	
	public PersonDTO(ErrorDTO error) {
		this.setError(error);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public int getIdentification() {
		return identification;
	}
	public void setIdentification(int identification) {
		this.identification = identification;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public ErrorDTO getError() {
		return error;
	}

	public void setError(ErrorDTO error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "Person [id="+id+", identification="+identification+", name="+name+", lastname="+lastname+", age="+dateOfBirth+"]";
	}
}
