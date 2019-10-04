package co.com.ceiba.infrastructure.dto;

public class PersonDTO {
	private long id;
	private int identification;
	private String name;
	private String lastname;
	private int age;
	
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [id="+id+", identification="+identification+", name="+name+", lastname="+lastname+", age="+age+"]";
	}
}
