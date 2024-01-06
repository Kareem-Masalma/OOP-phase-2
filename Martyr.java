
/*
 Kareem Masalma.
 1220535.
 Project phase2.
 */

public class Martyr extends Person {
	private String dateOfMartydom;
	private String causeOfDeath;
	private String placeOfDeath;

	// No argument constructor.
	public Martyr() {

	}

	public Martyr(String name) {
		super(name);
	}
	
	// constructor with argument for super class and subclass.
	public Martyr(String dateOfMartydom, String causeOfDeath, String placeOfDeath, String iD, String name, int age,
			String gender, String address, String contactInfo) throws GenderInputException {
		super(iD, name, age, gender, address, contactInfo);
		this.dateOfMartydom = dateOfMartydom;
		this.causeOfDeath = causeOfDeath;
		this.placeOfDeath = placeOfDeath;
	}

	// Setters and getters:
	public String getDateOfMartydom() {
		return dateOfMartydom;
	}

	public String getCauseOfDeath() {
		return causeOfDeath;
	}

	public String getPlaceOfDeath() {
		return placeOfDeath;
	}

	public void setDateOfMartydom(String dateOfMartydom) {
		this.dateOfMartydom = dateOfMartydom;
	}

	public void setCauseOfDeath(String causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
	}

	public void setPlaceOfDeath(String placeOfDeath) {
		this.placeOfDeath = placeOfDeath;
	}

	@Override
	public String toString() {
		return "Martyr [Name=" + getName() + ", ID=" + getID() + ", Age=" + getAge() + ", Gender=" + getGender()
				+ ", Address=" + getAddress() + ", Contact Info=" + getContactInfo() + "Date of Martydom="
				+ dateOfMartydom + ", Cause of Death=" + causeOfDeath + ", Place of Death=" + placeOfDeath + "]";
	}

}
