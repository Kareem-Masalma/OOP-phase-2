
/*
 Kareem Masalma.
 1220535.
 Project phase2.
 */

public class LivePerson extends Person {

	// No argument constructor.
	public LivePerson() {

	}
	
	public LivePerson(String name) {
		super(name);
	}

	// constructor with argument for super class and subclass.
	public LivePerson(String iD, String name, int age, String gender, String address, String contactInfo)
			throws GenderInputException {
		super(iD, name, age, gender, address, contactInfo);
	}

	// toString method to show the info about an alive person.
	@Override
	public String toString() {
		return "LivePerson [ID=" + getID() + ", Name=" + getName() + ", Age=" + getAge() + ", Gender=" + getGender()
				+ ", Address=" + getAddress() + ", ContactInfo=" + getContactInfo() + "]";
	}

}
