package no.ntnu.idata.iir.Joachiam.oblig3;

import java.time.LocalDate;
import java.util.Scanner;


/**
 * Represents the user interface for bonusMembers.
 *
 *
 *
 *
 *
 *
 */
public class BonusMemberUI {
    private MemberArchive members;

    private final int ADD_BONUS_MEMBER = 1;
    private final int LIST_ALL_MEMBERS = 2;
    private final int UPGRADE_QUALIFIED_MEMBERS = 3;
    private final int REGISTER_BONUS_POINTS = 4;
    private final int LIST_BY_POINTS = 5;
    private final int QUIT = 6;

    public BonusMemberUI() { members = new MemberArchive();}

    /**
     * Handles user input to designed functions of the application.
     *
     *
     * @return
     */
    private int menuChoice(){
        int menuChoice = 0;
        Scanner sc = new Scanner(System.in);
        menuChoice = sc.nextInt();
        sc.nextLine();

        return menuChoice;
    }

    private void start() {
        boolean running = true;

        while (running) {
            System.out.println("Welcome the Bonus Member UI");
            System.out.println("1. Add bonus member");
            System.out.println("2. List all members");
            System.out.println("3. Upgrade qualified members");
            System.out.println("4. Register bonus points");
            System.out.println("5. List all members by points");
            System.out.println("6. Quit");

            int menuChoice = menuChoice();

            switch (menuChoice) {
                case ADD_BONUS_MEMBER:
                    addBonusMember();
                    break;

                case LIST_ALL_MEMBERS:
                    listAllMembers();
                    break;

                case UPGRADE_QUALIFIED_MEMBERS:
                    upgradeQualifiedMembers();
                    break;

                case REGISTER_BONUS_POINTS:
                    registerBonusPoints();
                    break;

                case LIST_BY_POINTS:
                    listAllMembersByPoints();
                    break;

                case QUIT:
                    System.out.println("Thanks for using the app!");
                    running = false;
            }
        }
    }

    /**
     * Users sorted in order of amount of points gained
     *
     *
     */
    private void listAllMembersByPoints() {
        members.stream()
                    .sorted()
                    .forEach(this::printMemberDetails);

    }

    /**
     * Handles the adding of points to a desired user
     *
     *
     */
    private void registerBonusPoints() {
        int memberNumber = 0;
        int points = 0;

        System.out.println("Register Bonus Points:");
        System.out.println("Enter the member number of the desired member");
        Scanner sc = new Scanner(System.in);
        memberNumber = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter the amount of points to add");
        points = sc.nextInt();
        sc.nextLine();

        members.registerPoints(memberNumber, points);
        // members.registerPoints(int memberNumber, int bonusPoints);

    }

    /**
     * Upgrades the members withing the required range of points
     * for either silver or Gold.
     *
     */
    private void upgradeQualifiedMembers() {
        int year = 0;
        int month = 0;
        int day = 0;

        System.out.println("Upgrade qualified members");
        System.out.println("Enter the date");
        System.out.println("Year");

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the year");
        year = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter the month");
        month = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter the day");
        day = sc.nextInt();
        sc.nextLine();

        members.checkMembers(LocalDate.of(year, month, day));

    }

    /**
     * Prints a list of all the members in the collection
     *
     */
    private void listAllMembers(){
    members.forEach(this::printMemberDetails);

}

    /**
     * Adds Bonus member to the archive
     *
     *
     */
    private void addBonusMember() {
        String firstName = "";
        String lastName = "";
        String emailAddress = "";
        String password = "";
        int year = 0;
        int month = 0;
        int day = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter first name");
        firstName = sc.nextLine();

        System.out.println("Please enter last name");
        lastName = sc.nextLine();

        System.out.println("Please enter email address");
        emailAddress = sc.nextLine();

        System.out.println("Please enter password");
        password = sc.nextLine();

        System.out.println("Please enter current year");
        year = sc.nextInt();
        sc.nextLine();

        System.out.println("Please enter current month");
        month = sc.nextInt();
        sc.nextLine();

        System.out.println("Please enter current day");
        day = sc.nextInt();
        sc.nextLine();

        Personals newPerson = new Personals(firstName, lastName, emailAddress, password);

        members.addMember(newPerson, LocalDate.of(year, month, day));
    }

    /**
     * Prints the member details to the console
     *
     * @param bonusMember
     */
    private void printMemberDetails(BonusMember bonusMember){
        System.out.println("Name:"  + bonusMember.getPersonals().getFirstname()
        + " " + bonusMember.getPersonals().getSurname());
        System.out.println("Points: " + bonusMember.getPoints());
        System.out.println("Member number: " + bonusMember.getMemberNo());
        System.out.println("Member type: " + bonusMember.getClass());
        System.out.println("");
    }

    /**
     * Go baby go!
     *
     * @param args
     */
    public static void main(String[] args) {
        BonusMemberUI bui = new BonusMemberUI();
        bui.start();
    }

}
