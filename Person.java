
/*
 Kareem Masalma.
 1220535.
 Project phase2.
 */

public abstract class Person implements Cloneable {

	private String ID;
	private String name;
	private int age;
	private String gender;
	private String address;
	private String contactInfo;

	// No argument constructor.
	public Person() {

	}

	public Person(String name) {
		this.name = name;
	}

	// Arguments constructor.
	public Person(String iD, String name, int age, String gender, String address, String contactInfo)
			throws GenderInputException {
		this.ID = iD;
		this.name = name;
		this.age = age;
		setGender(gender);
		this.address = address;
		this.contactInfo = contactInfo;
	}

	// Setters and getters:
	public String getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public String getAddress() {
		return address;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setGender(String gender) throws GenderInputException {
		if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase("M")
				|| gender.equalsIgnoreCase("F"))
			this.gender = gender;
		else
			throw new GenderInputException();
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	// To String to print the info about a person.
	@Override
	public String toString() {
		return "Person [ID=" + ID + ", name=" + name + ", age=" + age + ", gender=" + gender + ", address=" + address
				+ ", contactInfo=" + contactInfo + "]";
	}

	// A method to deep copy a person (Live person or Martyr).
	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException ex) {
			return null;
		}
	}

}
