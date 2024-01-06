
public class GenderInputException extends Exception {

	private String message;

	public GenderInputException() {
		this.message = "Invalid gender.";
	}

	public GenderInputException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
