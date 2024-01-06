
public class FamilyRoleException extends Exception {

	private String message;

	public FamilyRoleException() {
		this.message = "Invalid famliy role.";
	}

	public FamilyRoleException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
