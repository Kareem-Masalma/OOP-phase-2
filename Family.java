
/*
 Kareem Masalma.
 1220535.
 Project phase2.
 */

import java.util.ArrayList;

public class Family implements Sortable, Cloneable {
	private String familyName;
	private ArrayList<Person> members = new ArrayList<>();
	private ArrayList<Person> parents = new ArrayList<>();
	private int numMart;

	// No argument constructor.
	public Family() {

	}

	// constructor with argument.
	public Family(String familyName) {
		this.familyName = familyName;
	}

	public Family(String familyName, int numMart) {
		this.familyName = familyName;
		this.numMart = numMart;
	}

	// return all the members in the ArrayList.
	public ArrayList<Person> getMembers() {
		return members;
	}

	// Setters and getters:
	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public ArrayList<Person> getParents() {
		return parents;
	}

	public int getNumMart() {
		return numMart;
	}

	public void setNumMart(int numMart) {
		this.numMart = numMart;
	}

	// A method to add a new member to a family.
	public boolean addMember(Person member, String role) throws FamilyRoleException {
		for (int i = 0; i < members.size(); i++) { // To check if the entered ID is already exist.
			if (members.get(i).getID().equals(member.getID())) {
				System.out.println("ID already exists.");
				return false;
			}
		}

		if (!role.equalsIgnoreCase("Father") && !role.equalsIgnoreCase("Mother") && !role.equalsIgnoreCase("Dad")
				&& !role.equalsIgnoreCase("Mom") && !role.equalsIgnoreCase("Son")
				&& !role.equalsIgnoreCase("Daughter")) {
			throw new FamilyRoleException();
		}

		if (parents.size() >= 2) {
			System.out.println("Parents are alread added you cannot add more than 2.");
			return false;
		}

		if (role.equalsIgnoreCase("Father") || role.equalsIgnoreCase("Mother") || role.equalsIgnoreCase("Dad")
				|| role.equalsIgnoreCase("Mom")) {
			members.add(member);
			parents.add(member);
			return true;
		} else {
			if (parents.isEmpty()) {
				System.out.println("Parents should be added first.");
				return false;
			}
			members.add(member);
			return true;
		}

	}

	// To add members from the files.
	public boolean addMember(Person member) {
	
		members.add(member);
		return true;
	}

	// A method to remove a member.
	public boolean removeMember(Person member) {
		if (member == null)
			return false;
		// A loop to search for the member.
		for (int i = 0; i < members.size(); i++) {
			// If the ID exists the member will be removed from the ArrayList members.
			if (members.get(i).getID().equals(member.getID())) {
				members.remove(i);
				return true; // return true if the member is successfully removed
			}
		}
		return false; // If the member was not found it will return false.
	}

	// To remove members from the file.
	public boolean removeMemberIO(Person member) {
		for (int i = 0; i < members.size(); i++) {
			if (member.getName().equalsIgnoreCase(members.get(i).getName())) {
				members.remove(i);
				return true;
			}
		}
		return false;
	}

	// A method to add a parent to the parents ArrayList.
	public void addParent(Person parent) throws ParentsOutOfBoundException {
		// A loop to check if the parent already added to parents ArrayList.
		for (int i = 0; i < parents.size(); i++) {
			if (parents.get(i).getID().equals(parent.getID())) {
				System.out.println("The parent with this ID already exists.");
				return;
			}
		}
		if (parents.size() >= 2) {
			throw new ParentsOutOfBoundException();
		}
		parents.add(parent);
		members.add(parent);
		System.out.println("Parent added successfully.");
	}

	// A method to add parents from the file.
	public void addParentIO(Person parent) throws ParentsOutOfBoundException {
		if (parents.size() >= 2) {
			throw new ParentsOutOfBoundException();
		}
		members.add(parent);
		parents.add(parent);
	}

	// A method to remove a parent.
	public boolean removeParent(Person parent) {
		boolean flag = false;
		if (parent == null)
			return false;
		// Remove the parent from parents ArrayList.
		for (int i = 0; i < parents.size(); i++) {
			if (parents.get(i).getID().equals(parent.getID())) {
				parents.remove(i);
				flag = true;
			}
		}

		// Remove the parent from parents ArrayList.
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getID().equals(parent.getID())) {
				members.remove(i);
				flag = true;
			}
		}
		return flag;
	}

	// A method to remove the parents.
	public boolean removeParentIO(Person parent) {
		boolean flag = false;

		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getName().equalsIgnoreCase(parent.getName())) {
				members.remove(i);
				flag = true;
			}
		}

		for (int i = 0; i < parents.size(); i++) {
			if (parents.get(i).getName().equalsIgnoreCase(parent.getName())) {
				parents.remove(i);
				flag = true;
			}
		}
		return flag;
	}

	// toString method to get the info about the family and its members.
	@Override
	public String toString() {
		return "Family [Family Name=" + familyName + ", members=" + members + "]";
	}

	// Overridden method to check if 2 families are equal by the number of the
	// martyrs.
	@Override
	public boolean equals(Object obj) {
		int mar = getNumMart();
		int marFam = 0;
//		

		if (obj instanceof Family) {
			marFam = ((Family) obj).getNumMart();
		}

		if (mar == marFam)
			return true;
		else
			return false;
	}

	@Override
	// A method to sort families descending by number of Martyrs.
	public ArrayList<Family> sortByMartyrs(ArrayList<Family> families) {
		ArrayList<Family> tempFam = (ArrayList<Family>) families.clone();
		int index = 0;
		for (int i = 0; i < tempFam.size(); i++) {
			Family max = tempFam.get(i);
			for (int j = index; j < tempFam.size(); j++) {
				if (max.getNumMart() < tempFam.get(j).getNumMart())
					max = tempFam.get(j);
			}
			tempFam.remove(max);
			tempFam.add(index, max);
			index++;
		}
		return tempFam;
	}

	@Override
	// A method to sort families descending by number of Orphans.
	public ArrayList<Family> sortByOrphans(ArrayList<Family> families) {
		ArrayList<Family> tempFam = (ArrayList<Family>) families.clone();
		int index = 0;
		for (int i = 0; i < tempFam.size(); i++) {
			Family max = tempFam.get(i);
			for (int j = index; j < tempFam.size(); j++) {
				if (max.numOfOrphans() < tempFam.get(j).numOfOrphans())
					max = tempFam.get(j);
			}
			tempFam.remove(max);
			tempFam.add(index, max);
			index++;
		}
		return tempFam;
	}

	// Method to get the numbers of martyrs in the family.
	public int numOfMartyrs() {
		int mar = 0;
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i) instanceof Martyr)
				mar++;
		}
		return mar;
	}

	public int numOfOrphans() {
		int orph = 0;
		if (parents.get(0) instanceof Martyr && parents.get(1) instanceof Martyr)
			orph = members.size() - parents.size();
		return orph;
	}

	// A method to deep copy a family.
	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException ex) {
			return null;
		}
	}
}