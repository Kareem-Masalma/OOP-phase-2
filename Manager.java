
/*
 Kareem Masalma.
 1220535.
 Project phase2.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
	private ArrayList<Family> families = new ArrayList<>();

	// Add a new Family to the families ArrayList.
	public boolean addFamily(Family family) {
		// A loop to check if the family already exists.
		for (int i = 0; i < families.size(); i++) {
			if (families.get(i).getFamilyName().equalsIgnoreCase(family.getFamilyName()))
				return false;
		}
		families.add(family);
		return true;
	}

	// A method to update a family.
	public boolean updateFamily(String familyName, Family updatedFamily) {
		// A loop to search a family.
		for (int i = 0; i < families.size(); i++) {
			if (families.get(i).getFamilyName().equalsIgnoreCase(familyName)) {
				families.set(i, updatedFamily);
				return true;
			}
		}
		// The method will return false if the family don't exist.
		return false;
	}

	// Delete a family from the ArrayList.
	public boolean deleteFamily(String familyName) {
		// A loop to search the family.
		for (int i = 0; i < families.size(); i++) {
			if (families.get(i).getFamilyName().equalsIgnoreCase(familyName)) {
				families.remove(i);
				return true;
			}
		}
		// The method will return false if the family don't exist.
		return false;
	}

	// A method to search for a family by its name.
	public Family searchByName(String familyName) {
		// A loop to search for the family and return the Family if it's found.
		for (int i = 0; i < families.size(); i++) {
			if (families.get(i).getFamilyName().equalsIgnoreCase(familyName))
				return families.get(i);
		}
		// The method will return null if the family was not found.
		return null;
	}

	// A method to search a Person by their ID.
	public Person searchPersonByID(String ID) {
		// A loop to search the family that contains the person in the families
		// ArrayList.
		for (int i = 0; i < families.size(); i++) {
			// A nested loop to search for the Person inside every family until it's found.
			for (int j = 0; j < families.get(i).getMembers().size(); j++) {
				// To check if the ID's are equal between the inserted ID and the Person's IDs.
				if (families.get(i).getMembers().get(j).getID().equalsIgnoreCase(ID)) {
					return families.get(i).getMembers().get(j);
				}
			}
		}
		return null;
	}

	// This method to calculate the total number of martyrs in all families.
	public int calculateTotalMatyrs() {
		int n = 0; // A counter.
		// A loop to go through all families.
		for (int i = 0; i < families.size(); i++) {
			// A nested loop to go through all members.
			for (int j = 0; j < families.get(i).getMembers().size(); j++) {
				// To check if the person found is a Martyr or alive.
				if (families.get(i).getMembers().get(j) instanceof Martyr)
					n++;
			}
		}
		return n;
	}

	// A method to calculate the total number of orphans in all families.
	public int calculateTotalOrphans() {
		int orph = 0; // A counter.
		// A loop to go through all families.
		for (int i = 0; i < families.size(); i++) {
			// A nested loop to check all parents.
			for (int j = 0; j < families.get(i).getParents().size() - 1; j++) {
				// To check if both parents are martyrs to add all the members as orphans.
				if (families.get(i).getParents().get(j) instanceof Martyr
						&& families.get(i).getParents().get(j + 1) instanceof Martyr) {
					orph += (families.get(i).getMembers().size() - 2);
				}
			}
		}
		return orph;
	}

	// A method to calculate the total number of alive persons in all families.
	public int calculateTotalLivePersons() {
		int liv = 0; // A counter.
		// A loop to go through all families.
		for (int i = 0; i < families.size(); i++) {
			// A nested loop to go through all members.
			for (int j = 0; j < families.get(i).getMembers().size(); j++) {
				// To check if the person is a live person.
				if (families.get(i).getMembers().get(j) instanceof LivePerson)
					liv++;
			}
		}
		return liv;
	}

	// A method to calculate the martyrs, orphans and alive people in a certain
	// family.
	public ArrayList<Integer> calculateFamilyStatistics(String familyName) {
		if (searchByName(familyName) == null) {
			return null;
		}
		// Defining an ArrayList of Integers to save the statistics inside it.
		ArrayList<Integer> stat = new ArrayList<>();
		int mar = 0; // Martyrs counter.
		int orph = 0; // Orphans counter.
		int liv = 0; // Alive persons counter.
		// A loop to go through all families,
		for (int i = 0; i < families.size(); i++) {
			// To check if the inserted family is found.
			if (families.get(i).getFamilyName().equalsIgnoreCase(familyName)) {
				// A nested loop to calculate the martyrs inside the family.
				for (int j = 0; j < families.get(i).getMembers().size(); j++) {
					if (families.get(i).getMembers().get(j) instanceof Martyr) {
						mar++;
					}
				}

				// A nested loop to calculate the number of orphans inside the family.
				for (int j = 0; j < families.get(i).getParents().size() - 1; j++) {
					if (families.get(i).getParents().get(j) instanceof Martyr
							&& families.get(i).getParents().get(j + 1) instanceof Martyr) {
						orph += families.get(i).getMembers().size();
						for (int k = 0; k < families.get(i).getMembers().size(); k++) {
							// If any of the children are Martyrs it won't be included as orphan.
							if (families.get(i).getMembers().get(k) instanceof Martyr)
								orph--;
						}
					}
				}

				// A nested loop to calculate the number of alive persons inside the family.
				for (int j = 0; j < families.get(i).getMembers().size(); j++) {
					if (families.get(i).getMembers().get(j) instanceof LivePerson) {
						liv++;
					}
				}
			}
		}
		// Adding the number of martyrs, orphans and alive people to the ArrayList.
		stat.add(mar);
		stat.add(orph);
		stat.add(liv);
		// Returning the ArrayList.
		return stat;
	}

	// A method to calculate the number of martyrs, orphans and alive people in all
	// families.
	public ArrayList<Integer> calculateGlobalStatistics() {
		// Invoking methods that calculated total martyrs, orphans and alive people and
		// store them in variables.
		int mar = calculateTotalMatyrs();
		int orph = calculateTotalOrphans();
		int liv = calculateTotalLivePersons();
		// Defining an ArrayList to store the statistics.
		ArrayList<Integer> stat = new ArrayList<>();
		// Adding the numbers to the ArrayList.
		stat.add(mar);
		stat.add(orph);
		stat.add(liv);
		// Returning the ArrayList.
		return stat;
	}

	// toString method to return a family info.
	@Override
	public String toString() {
		return "Manager [families=" + families + "]";
	}

	public ArrayList<Family> getFamilies() {
		return families;
	}

	// A method to read the data from the file.
	public void readFile() {
		File file = new File("data.txt");
		Scanner input = new Scanner(System.in);
		Scanner in = null;
		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Problem occured.");
			return;
		}
		while (in.hasNextLine()) {
			String line = in.nextLine();
			String[] splittedLine = line.split(": ");
			String familyName = splittedLine[0];
			int numMart = Integer.parseInt(splittedLine[1]);
			Family family = new Family(familyName, numMart);
			families.add(family);

			String dots = in.nextLine();
			String line2 = in.nextLine();
			String[] splittedLine2 = line2.split(", ");
			System.out.println("Is the parent " + splittedLine2[0] + " " + familyName + " Alive or Martyr? (A/M)");
			String life = input.next();
			try {
				if (life.equalsIgnoreCase("A")) {

					family.addParentIO(new LivePerson(splittedLine2[0]));

				} else if (life.equalsIgnoreCase("M")) {

					family.addParentIO(new Martyr(splittedLine2[0]));

				}
			} catch (ParentsOutOfBoundException ex) {
				System.out.println("Can not add more than two parents.");
			}
			System.out.println("Is the parent " + splittedLine2[1] + " " + familyName + " Alive or Martyr? (A/M)");
			life = input.next();
			try {
				if (life.equalsIgnoreCase("A")) {

					family.addParentIO(new LivePerson(splittedLine2[1]));

				} else if (life.equalsIgnoreCase("M")) {

					family.addParentIO(new Martyr(splittedLine2[1]));
				}
			} catch (ParentsOutOfBoundException ex) {
				System.out.println("Can not add more than two parents.");
			}
			String line3 = in.nextLine();
			String[] splittedLine3 = line3.split(", ");
			for (int i = 0; i < splittedLine3.length; i++) {
				family.addMember(new LivePerson(splittedLine3[i]));
			}

			if (in.hasNextLine()) {
				String empty = in.nextLine();
			}

		}
		in.close();
	}

	// A method to write on the file.
	public void writeFile() {
		if (families.isEmpty()) {
			System.out.println("No families, to write on file.");
			return;
		}
		try {
			File file = new File("data.txt");
			PrintWriter pw = null;
			Family fam = new Family();
			try {
				pw = new PrintWriter(file);

			} catch (FileNotFoundException e1) {
				System.out.println("Problem occured.");
				return;
			}

			ArrayList<Family> family = fam.sortByMartyrs(families);

			for (int i = 0; i < family.size(); i++) {
				if (family.get(i).getParents().isEmpty())
					continue;
				removeDuplicate(family.get(i));
			}

			for (int i = 0; i < family.size(); i++) {
				pw.print(family.get(i).getFamilyName() + ": ");
				pw.println(family.get(i).getNumMart());
				pw.println("..........");
				if (family.get(i).getParents().isEmpty())
					continue;
				pw.println(family.get(i).getParents().get(0).getName() + ", "
						+ family.get(i).getParents().get(1).getName());
				for (int j = 0; j < family.get(i).getMembers().size(); j++) {
					if (family.get(i).getMembers().get(j).getName()
							.equalsIgnoreCase(family.get(i).getParents().get(0).getName())
							|| family.get(i).getMembers().get(j).getName()
									.equalsIgnoreCase(family.get(i).getParents().get(1).getName())) {
						continue;
					}
					if (j == family.get(i).getMembers().size() - 1) {
						pw.println(family.get(i).getMembers().get(j).getName());
						break;
					}
					pw.print(family.get(i).getMembers().get(j).getName() + ", ");

				}
				pw.println();
			}
			pw.close();
		} catch (NullPointerException ex) {
			System.out.println("Problem occured.");
		} catch (Exception ex) {
			System.out.println("ERROR: Problem occured while writing on file.");
		}
	}

	public Family removeDuplicate(Family fam) {
		try {
			if (fam.getParents().size() == 1) {
				if (fam.getMembers().contains(fam.getParents().get(0))) {
					fam.getMembers().remove(fam.getParents().get(0));
				}
				return fam;
			}
			if (fam.getMembers().contains(fam.getParents().get(0))) {
				fam.getMembers().remove(fam.getParents().get(0));
			}
			if (fam.getMembers().contains(fam.getParents().get(1))) {
				fam.getMembers().remove(fam.getParents().get(1));
			}
			return fam;
		} catch (Exception ex) {
			System.out.println("ERROR: Problem occured.");
			return null;
		}
	}

	// To calculate family statistics from the file.
	public ArrayList<Integer> calculateFamilyStatisticsFile(String familyName) {
		ArrayList<Integer> stat = new ArrayList<>();
		Family family = searchByName(familyName);
		if (family == null) {
			return null;
		}
		if (family.getMembers().size() < family.getNumMart()) {
			System.out.println("ERROR: The number of the family members less than the number of the martyrs.");
			return null;
		}

		int mart = family.getNumMart();
		int live = family.getMembers().size() - mart;
		int orph = 0;
		if (family.getParents().get(0) instanceof Martyr && family.getParents().get(1) instanceof Martyr
				&& !(family.getParents().isEmpty())) {
			orph = family.getMembers().size() - 2;
		}
		stat.add(mart);
		stat.add(orph);
		stat.add(live);
		return stat;
	}

	// Calculate global statistics from the file.
	public ArrayList<Integer> calculateGlobalFileStatistics() {
		ArrayList<Integer> stat = new ArrayList<>();
		int mart = 0;
		int orph = 0;
		int live = 0;
		for (int i = 0; i < families.size(); i++) {
			if (families.get(i).getParents().isEmpty())
				continue;
			mart += families.get(i).getNumMart();
			live += (families.get(i).getMembers().size() - families.get(i).getNumMart());
			if (families.get(i).getParents().get(0) instanceof Martyr
					&& families.get(i).getParents().get(1) instanceof Martyr) {
				orph += (families.get(i).getMembers().size() - 2);
			}
		}
		stat.add(mart);
		stat.add(orph);
		stat.add(live);
		return stat;
	}
}
