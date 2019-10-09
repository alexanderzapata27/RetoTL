package co.com.ceiba.domain.dto.person;

public class PersonDTO {
	private long id;
	private int identification;
	private String name;
	private String lastname;
	private String dateOfBirth;
	
	public PersonDTO(int identification, String name, String lastname, String dateOfBirth) {
		super();
		this.identification = identification;
		this.name = name;
		this.lastname = lastname;
		this.dateOfBirth = dateOfBirth;
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
	public void setAge(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	@Override
	public String toString() {
		return "Person [id="+id+", identification="+identification+", name="+name+", lastname="+lastname+", age="+dateOfBirth+"]";
	}
}
