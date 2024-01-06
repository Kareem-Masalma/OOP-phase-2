
/*
 Kareem Masalma.
 1220535.
 Project phase2.
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) throws ParentsOutOfBoundException, GenderInputException {
		Scanner in = new Scanner(System.in);
		Manager manager = new Manager();
		Family family = new Family();
//		Family family1 = new Family("A");
//		manager.addFamily(family1);
//		Family family2 = new Family("B");
//		manager.addFamily(family2);
//		family1.addParent(new Martyr("Bombing", "27/10/2023", "Gaza", "1", "a", 45, "Male", "Gaza", "0591"));
//		System.out.println(manager.calculateTotalMatyrs());
//		System.out.println(family1.equals(family2));

		while (true) {
			menu();
			int ope = 0;
			while (true) {
				try {
					ope = in.nextInt();
					break;
				} catch (InputMismatchException ex) {
					System.out.println("The option should be a number, try again:");
					in.next();
				}
			}
			if (ope == 1) { // To read and print on the console.
				while (true) {
					consoleMainMenu();
					while (true) {
						try {
							ope = in.nextInt();
							break;
						} catch (InputMismatchException ex) {
							System.out.println("The option should be a number, try again:");
							in.next();
						}
					}
					if (ope == 1) { // To edit on manager.
						while (true) {
							managerMenu();
							while (true) {
								try {
									ope = in.nextInt();
									break;
								} catch (InputMismatchException ex) {
									System.out.println("The option should be a number, try again:");
									in.next();
								}
							}
							if (ope == 1) { // To add a family.
								System.out.println("Enter the name of the Family:");
								String name = in.next();
								if (manager.addFamily(new Family(name)))
									System.out.println("Family " + name + " is added successfully.");
								else
									System.out.println("Family " + name + " was not added, problem occured.");
								System.out.println(
										"----------------------------------------------------------------------------------------------------");
							} else if (ope == 2) { // To delete a family.
								System.out.println("Enter the name of the Family:");
								String name = in.next();
								if (manager.deleteFamily(name))
									System.out.println("Family " + name + " is deleted successfully.");
								else
									System.out.println("Family " + name + " was not found.");
								System.out.println(
										"----------------------------------------------------------------------------------------------------");
							} else if (ope == 3) { // To update a family.
								System.out.println("Enter the name you want to update:");
								String name = in.next();
								System.out.println("Enter the new Family name:");
								String newName = in.next();
								if (manager.updateFamily(name, new Family(newName)))
									System.out.println("Family " + name + " is updated to " + newName + ".");
								else
									System.out.println("Family" + name + " was not found.");
								System.out.println(
										"----------------------------------------------------------------------------------------------------");
							} else if (ope == 4) { // Search a person by their ID.
								System.out.println("Enter the ID of the person you want to search:");
								String ID = in.next();
								try {
									System.out.println(manager.searchPersonByID(ID));
								} catch (NullPointerException ex) {
									System.out.println("Person with ID " + ID + " was not found.");
								}
								System.out.println(
										"----------------------------------------------------------------------------------------------------");
							} else if (ope == 5) { // Search a family by name.
								System.out.println("Enter the name of the family you want to search:");
								String name = in.next();
								try {
									System.out.println(manager.searchByName(name));
								} catch (NullPointerException ex) {
									System.out.println("Family " + name + " was not found.");
								}
								System.out.println(
										"----------------------------------------------------------------------------------------------------");
							} else if (ope == 6) { // Calculate the martyrs.
								System.out.println("The total number of Martyrs for all families = "
										+ manager.calculateTotalMatyrs());
								System.out.println(
										"----------------------------------------------------------------------------------------------------");
							} else if (ope == 7) { // Calculate the alive people.
								System.out.println("The total number of alive people for all families = "
										+ manager.calculateTotalLivePersons());
								System.out.println(
										"----------------------------------------------------------------------------------------------------");
							} else if (ope == 8) { // Calculate the orphans.
								System.out.println("The total number of Orphans for all families = "
										+ manager.calculateTotalOrphans());
								System.out.println(
										"----------------------------------------------------------------------------------------------------");
							} else if (ope == 9) { // Calculate the global statistics.
								System.out.println("Martyrs = " + manager.calculateGlobalStatistics().get(0)
										+ ", Live people = " + manager.calculateGlobalStatistics().get(2)
										+ ", Orphans = " + manager.calculateGlobalStatistics().get(1));
								System.out.println(
										"----------------------------------------------------------------------------------------------------");
							} else if (ope == 10) { // Sort a family by the number of martyrs.
								try {
									ArrayList<Family> fam = family.sortByMartyrs(manager.getFamilies());
									System.out.println(fam);
								} catch (NullPointerException ex) {
									System.out.println("There is no families.");
								}
								System.out.println(
										"----------------------------------------------------------------------------------------------------");
							} else if (ope == 11) { // Sort a family by the number of orphans.
								try {
									ArrayList<Family> fam = family.sortByOrphans(manager.getFamilies());
									System.out.println(fam);
								} catch (NullPointerException ex) {
									System.out.println("There is no families.");
								}
								System.out.println(
										"----------------------------------------------------------------------------------------------------");
							} else if (ope == 12) {
								break;
							} else {
								System.out.println("Invalid option.");
								System.out.println(
										"----------------------------------------------------------------------------------------------------");
							}
						}
					} else if (ope == 2) { // To edit on family.
						System.out.println("Enter the name of the family you want to edit on:");
						String name = in.next();
						Family fam = manager.searchByName(name);
						if (fam == null) {
							System.out.println("Family " + name + " was not found.");
							continue;
						}
						while (true) {
							fam.setNumMart(manager.calculateFamilyStatistics(fam.getFamilyName()).get(0));
							familyMenu();
							while (true) {
								try {
									ope = in.nextInt();
									break;
								} catch (InputMismatchException ex) {
									System.out.println("The option should be a number, try again:");
									in.next();
								}
							}

							if (ope == 1) { // Add a new member.
								try {
									System.out.println("Enter the ID for the member:");
									String ID = in.next();
									System.out.println("Enter the name for the member:");
									name = in.next();
									System.out.println("Enter the age for the member:");
									int age = in.nextInt();
									System.out.println("Enter the gender for the member:");
									String gender = in.next();
									System.out.println("Enter the address for the member");
									String address = in.next();
									System.out.println("Enter the contact information for the member");
									String contactInfo = in.next();
									System.out.println("Enter the role of the member in the family:");
									String role = in.next();
									System.out.println("Is the member alive or martyr? (A/M)");
									String life = in.next();
									if (life.equalsIgnoreCase("M")) {
										System.out.println("Enter the cause of death:");
										String cause = in.next();
										System.out.println("Enter the date of martydom:");
										String date = in.next();
										System.out.println("Enter the place of death:");
										String place = in.next();
										if (fam.addMember(new Martyr(date, cause, place, ID, name, age, gender, address,
												contactInfo), role)) {
											System.out.println("Member " + name + " is added successfully.");

										} else {
											System.out.println("Member " + name + " is not added, problem occured.");

										}
									} else if (life.equalsIgnoreCase("A")) {
										if (fam.addMember(new LivePerson(ID, name, age, gender, address, contactInfo),
												role)) {
											System.out.println("Member " + name + " is added successfully.");
										} else {
											System.out.println("Member " + name + " is not added, problem occured.");
										}

									} else {
										System.out.println("Invalid choise, member was not added.");
									}
								} catch (InputMismatchException ex) {
									System.out.println("Invalid input.");
									in.next();
								} catch (GenderInputException ex) {
									System.out.println("Invalid gender.");
								} catch (FamilyRoleException ex) {
									System.out.println("Invalid role.");
								}
								System.out.println(
										"----------------------------------------------------------------------------------------------------");
							} else if (ope == 2) { // Remove a member.
								System.out.println("Enter the ID for the person you want to remove:");
								String ID = in.next();
								try {
									if (fam.removeMember(manager.searchPersonByID(ID))) {
										System.out.println("Member is deleted successfully.");
									} else
										System.out.println(
												"Member with ID = " + ID + " was not deleted, problem occured.");
								} catch (NullPointerException ex) {
									System.out.println("Person with ID = " + ID + " was not found.");
								}
								System.out.println(
										"----------------------------------------------------------------------------------------------------");
							} else if (ope == 3) { // Add a new parent.
								try {
									System.out.println("Enter the ID for the parent:");
									String ID = in.next();
									System.out.println("Enter the name for the parent:");
									name = in.next();
									System.out.println("Enter the age for the parent:");
									int age = in.nextInt();
									System.out.println("Enter the gender for the parent:");
									String gender = in.next();
									System.out.println("Enter the address for the parent:");
									String address = in.next();
									System.out.println("Enter the contact information for the parent:");
									String contactInfo = in.next();
									System.out.println("Is the parent alive or martyr? (A/M)");
									String life = in.next();
									if (life.equalsIgnoreCase("M")) {
										System.out.println("Enter the cause of death:");
										String cause = in.next();
										System.out.println("Enter the date of martydom:");
										String date = in.next();
										System.out.println("Enter the place of death:");
										String place = in.next();
										fam.addParent(new Martyr(date, cause, place, ID, name, age, gender, address,
												contactInfo));
									} else if (life.equalsIgnoreCase("A")) {
										fam.addParent(new LivePerson(ID, name, age, gender, address, contactInfo));
									}
								} catch (InputMismatchException ex) {
									System.out.println("Invalid input.");
									in.next();
								} catch (GenderInputException ex) {
									System.out.println("Invalid gender.");
								} catch (ParentsOutOfBoundException ex) {
									System.out.println("The family already has two parents, parent was not added.");
								}
								System.out.println(
										"----------------------------------------------------------------------------------------------------");
							} else if (ope == 4) { // Remove a parent.
								System.out.println("Enter the ID for the parent you want to remove:");
								String ID = in.next();
								try {
									if (fam.removeParent(manager.searchPersonByID(ID)))
										System.out.println("Parent is deleted successfully.");
									else
										System.out.println(
												"Member with ID = " + ID + " was not deleted, problem occured.");
								} catch (NullPointerException ex) {
									System.out.println("Person with ID = " + ID + " was not found.");
								}
								System.out.println(
										"----------------------------------------------------------------------------------------------------");
							} else if (ope == 5) { // Check the equality of two families.
								System.out.println("Enter the name of the first family:");
								String fam1 = in.next();
								System.out.println("Enter the name of the second family:");
								String fam2 = in.next();
								try {
									Family family1 = manager.searchByName(fam1);
									Family family2 = manager.searchByName(fam2);
									if (family1.equals(family2))
										System.out.println("The two families are equals.");
									else
										System.out.println("The two families are not equal.");
								} catch (NullPointerException ex) {
									System.out.println("Family was not found.");
								}
								System.out.println(
										"----------------------------------------------------------------------------------------------------");
							} else if (ope == 6) { // Calculate family statistics.
								try {
									ArrayList<Integer> list = manager.calculateFamilyStatistics(fam.getFamilyName());
									System.out
											.println("The number of martyrs = " + list.get(0) + ", number of orphans = "
													+ list.get(1) + ", number of live people = " + list.get(2));
								} catch (NullPointerException ex) {
									System.out.println("Problem occured.");
								}
								System.out.println(
										"----------------------------------------------------------------------------------------------------");
							} else if (ope == 7) { // Copy a Martyr.
								System.out.println("Enter the martyr's ID:");
								String ID = in.next();
								if (ID == null) {
									System.out.println("Person was not found.");
									continue;
								}
								Person mart = manager.searchPersonByID(ID);
								if (!(mart instanceof Martyr)) {
									System.out.println("The person is not Martyr.");
									continue;
								}
								Martyr copy = (Martyr) mart.clone();
								System.out.println(copy);
							} else if (ope == 8) { // Copy a live person.
								System.out.println("Enter the person's ID");
								String ID = in.next();
								if (ID == null) {
									System.out.println("Person was not found.");
									continue;
								}
								Person liv = manager.searchPersonByID(ID);
								if (!(liv instanceof LivePerson)) {
									System.out.println("The person is not a live person.");
									continue;
								}
								LivePerson temp = (LivePerson) liv.clone();
								System.out.println(temp);
							} else if (ope == 9) {
								break;
							} else {
								System.out.println("Invalid input.");
							}
						}

					} else if (ope == 3) {
						break;
					} else {
						System.out.println("Invalid input.");
					}
				}
			} else if (ope == 2) { // To read from file and write on it.
				manager.readFile();
				while (true) {
					fileMenu();
					while (true) {
						try {
							ope = in.nextInt();
							break;
						} catch (InputMismatchException ex) {
							System.out.println("The option should be a number, try again:");
							in.next();
						}
					}
					if (ope == 1) { // To edit on manager.
						while (true) {
							fileManagerMenu();
							while (true) {
								try {
									ope = in.nextInt();
									break;
								} catch (InputMismatchException ex) {
									System.out.println("The option should be a number, try again:");
									in.next();
								}
							}
							if (ope == 1) { // Add a family.
								try {
									System.out.println("Enter the name of the family:");
									String name = in.next();
									System.out.println("Enter the number of martyrs:");
									int mart = in.nextInt();
									if (manager.addFamily(new Family(name, mart))) {
										System.out.println("Family added successfully.");
									} else {
										System.out.println("Family already exists.");
									}
								} catch (InputMismatchException ex) {
									System.out.println("Invalid input.");
									in.next();
								}
							} else if (ope == 2) { // Remove a family.
								System.out.println("Enter the name of the family you want to remove:");
								String name = in.next();
								if (manager.deleteFamily(name)) {
									System.out.println("Family deleted successfully.");
								} else {
									System.out.println("Family was not found.");
								}
							} else if (ope == 3) { // Update a family.
								try {
									System.out.println("Enter the name of the family you want to update:");
									String name = in.next();
									System.out.println("Enter the new family name:");
									String newName = in.next();
									System.out.println("Enter the number of martyrs:");
									int mart = in.nextInt();
									if (manager.updateFamily(name, new Family(newName, mart))) {
										System.out.println("Family updated successfully.");
									} else {
										System.out.println("Family was not found.");
									}
								} catch (InputMismatchException ex) {
									System.out.println("Invalid input.");
									in.next();
								}
							} else if (ope == 4) { // Calculate global statistics.
								ArrayList<Integer> list = manager.calculateGlobalFileStatistics();
								System.out.println("Martyrs = " + list.get(0) + ", orphans = " + list.get(1)
										+ ", live people = " + list.get(2));
							} else if (ope == 5) {
								break;
							} else {
								System.out.println("Invalid input.");
							}
						}
					} else if (ope == 2) { // To edit on family.
						System.out.println("Enter the name of the family:");
						String name = in.next();
						Family fam = manager.searchByName(name);
						while (true) {
							fileFamilyMenu();
							while (true) {
								try {
									ope = in.nextInt();
									break;
								} catch (InputMismatchException ex) {
									System.out.println("The option should be a number, try again:");
									in.next();
								}
							}
							if (ope == 1) { // Add a new member.
								try {
									System.out.println("Enter the ID for the member:");
									String ID = in.next();
									System.out.println("Enter the name for the member:");
									name = in.next();
									System.out.println("Enter the age for the member:");
									int age = in.nextInt();
									System.out.println("Enter the gender for the member:");
									String gender = in.next();
									System.out.println("Enter the address for the member");
									String address = in.next();
									System.out.println("Enter the contact information for the member");
									String contactInfo = in.next();
									System.out.println("Enter the role of the member in the family:");
									String role = in.next();
									System.out.println("Is the member alive or martyr? (A/M)");
									String life = in.next();
									if (life.equalsIgnoreCase("M")) {
										System.out.println("Enter the cause of death:");
										String cause = in.next();
										System.out.println("Enter the date of martydom:");
										String date = in.next();
										System.out.println("Enter the place of death:");
										String place = in.next();
										if (fam.addMember(new Martyr(date, cause, place, ID, name, age, gender, address,
												contactInfo), role)) {
											System.out.println("Member " + name + " is added successfully.");

										} else {
											System.out.println("Member " + name + " is not added, problem occured.");

										}
									} else if (life.equalsIgnoreCase("A")) {
										if (fam.addMember(new LivePerson(ID, name, age, gender, address, contactInfo),
												role)) {
											System.out.println("Member " + name + " is added successfully.");
										} else {
											System.out.println("Member " + name + " is not added, problem occured.");
										}

									} else {
										System.out.println("Invalid choise, member was not added.");
									}
								} catch (InputMismatchException ex) {
									System.out.println("Invalid input.");
									in.next();
								} catch (GenderInputException ex) {
									System.out.println("Invalid gender.");
								} catch (FamilyRoleException ex) {
									System.out.println("Invalid role.");
								}
								System.out.println(
										"----------------------------------------------------------------------------------------------------");
							} else if (ope == 2) { // Remove a member.
								System.out.println("Enter the name of the person you want to remove:");
								name = in.next();
								for (int i = 0; i < fam.getMembers().size(); i++) {
									if (name.equalsIgnoreCase(fam.getMembers().get(i).getName())) {
										if (fam.removeMemberIO(fam.getMembers().get(i))) {
											System.out.println("Member deleted successfully.");
											break;
										} else {
											System.out.println("Member was not found.");
										}
									}
								}
							} else if (ope == 3) { // Add a parent.
								try {
									System.out.println("Enter the ID for the parent:");
									String ID = in.next();
									System.out.println("Enter the name for the parent:");
									name = in.next();
									System.out.println("Enter the age for the parent:");
									int age = in.nextInt();
									System.out.println("Enter the gender for the parent:");
									String gender = in.next();
									System.out.println("Enter the address for the parent:");
									String address = in.next();
									System.out.println("Enter the contact information for the parent:");
									String contactInfo = in.next();
									System.out.println("Is the parent alive or martyr? (A/M)");
									String life = in.next();
									if (life.equalsIgnoreCase("M")) {
										System.out.println("Enter the cause of death:");
										String cause = in.next();
										System.out.println("Enter the date of martydom:");
										String date = in.next();
										System.out.println("Enter the place of death:");
										String place = in.next();
										fam.addParent(new Martyr(date, cause, place, ID, name, age, gender, address,
												contactInfo));
									} else if (life.equalsIgnoreCase("A")) {
										fam.addParent(new LivePerson(ID, name, age, gender, address, contactInfo));
									}
								} catch (InputMismatchException ex) {
									System.out.println("Invalid input.");
									in.next();
								} catch (GenderInputException ex) {
									System.out.println("Invalid gender.");
								} catch (ParentsOutOfBoundException ex) {
									System.out.println("The family already has two parents, parent was not added.");
								}
								System.out.println(
										"----------------------------------------------------------------------------------------------------");
							} else if (ope == 4) { // Remove a parent.
								System.out.println("Enter the name for the parent you want to remove:");
								name = in.next();
								for (int i = 0; i < fam.getParents().size(); i++) {
									if (name.equalsIgnoreCase(fam.getParents().get(i).getName())) {
										if (fam.removeParentIO(fam.getParents().get(i))) {
											System.out.println("Parent deleted successfully.");
											break;
										} else {
											System.out.println("Parent was not found.");
										}
									}
								}
							} else if (ope == 5) { // Check the equality of two families.
								System.out.println("Enter the name of the first family:");
								name = in.next();
								Family f1 = manager.searchByName(name);
								System.out.println("Enter the name of the second family:");
								String name2 = in.next();
								Family f2 = manager.searchByName(name2);
								if (f1.equals(f2)) {
									System.out.println("The two families are equal.");
								} else {
									System.out.println("Families are not equal.");
								}
							} else if (ope == 6) { // Calculate family statistics.
								try {
									ArrayList<Integer> list = manager
											.calculateFamilyStatisticsFile(fam.getFamilyName());
									System.out.println("Martyrs = " + list.get(0) + ", orphans = " + list.get(1)
											+ ", Live people = " + list.get(2));
								} catch (NullPointerException ex) {
									System.out.println("Problem occured.");
								}
								// Copy an object.
							} else if (ope == 7) {
								System.out.println("Enter the name of the person you want to copy:");
								name = in.next();
								boolean flag = false;
								for (int i = 0; i < fam.getMembers().size(); i++) {
									if (fam.getMembers().get(i).getName().equalsIgnoreCase(name)) {
										Person per = fam.getMembers().get(i);
										Person temp = (Person) per.clone();
										flag = true;
										System.out.println(temp);
										break;
									}
								}
								if (flag == false) {
									System.out.println("Person not found.");
									break;
								}
							} else if (ope == 8) {
								break;
							} else {
								System.out.println("Invalid input.");
							}
						}
					} else if (ope == 3) {
						manager.writeFile(); // Write on the file before closing.
						break;
					} else {
						System.out.println("Invalid input.");
					}
				}
			} else if (ope == 3) {
				System.out.println("EXITING THE PROGRAM.");
				break;
			} else {
				System.out.println("Invalid input.");
			}
		}
		in.close();
	}

	// Menus:
	public static void menu() {
		System.out.println("1. Read from console.");
		System.out.println("2. Read from file.");
		System.out.println("3. Exit.");
		System.out.println("Enter your option:");
	}

	public static void consoleMainMenu() {
		System.out.println("1. Edit on manager.");
		System.out.println("2. Edit on Family.");
		System.out.println("3. Exit to Menu.");
		System.out.println("Enter your option:");
	}

	public static void managerMenu() {
		System.out.println("1. Add a new Family.");
		System.out.println("2. Delete a Family.");
		System.out.println("3. Update a Family.");
		System.out.println("4. Search a member by ID.");
		System.out.println("5. Search a Family by name.");
		System.out.println("6. Calculate total Martyrs.");
		System.out.println("7. Calculate total Live people.");
		System.out.println("8. Calculate total Orphans.");
		System.out.println("9. Calculate global statistics.");
		System.out.println("10. Sort families by number of martyrs.");
		System.out.println("11. SOrt families by number of orphans.");
		System.out.println("12. Exit to main menu.");
		System.out.println("Enter your option:");
	}

	public static void familyMenu() {
		System.out.println("1. Add a member.");
		System.out.println("2. Remove a member.");
		System.out.println("3. Add a parent.");
		System.out.println("4. Remove a parent.");
		System.out.println("5. Check the equality of two families.");
		System.out.println("6. Calculate family statistics.");
		System.out.println("7. Copy a martyr object.");
		System.out.println("8. Copy a live person object");
		System.out.println("9. Exit to main menu.");
		System.out.println("Enter your option:");
	}

	public static void fileMenu() {
		System.out.println("1. Edit on manager.");
		System.out.println("2. Edit on Family.");
		System.out.println("3. Exit to main menu.");
		System.out.println("Enter your option:");
	}

	public static void fileManagerMenu() {
		System.out.println("1. Add Family.");
		System.out.println("2. Remove Family.");
		System.out.println("3. Update Family.");
		System.out.println("4. Calculate global statistics.");
		System.out.println("5. Exit ti main menu.");
		System.out.println("Enter your option:");
	}

	public static void fileFamilyMenu() {
		System.out.println("1. Add member.");
		System.out.println("2. Remove member.");
		System.out.println("3. Add parent.");
		System.out.println("4. Remove parent.");
		System.out.println("5. Check the equality of two families.");
		System.out.println("6. Calculate family statistics.");
		System.out.println("7. Copy a person object.");
		System.out.println("8. Exit to main menu.");
		System.out.println("Enter your option:");
	}
}
