package co.com.ceiba.domain.dto.person;

public class ErrorDTO {
	private int code;
	private String message;
	
	public ErrorDTO(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Person [code="+code+", message="+message+"]";
	}
}
