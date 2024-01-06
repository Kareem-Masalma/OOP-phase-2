
public class ParentsOutOfBoundException extends Exception {

	private String message;

	public ParentsOutOfBoundException() {
		this.message = "No parents were added to the family.";
	}

	public ParentsOutOfBoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
