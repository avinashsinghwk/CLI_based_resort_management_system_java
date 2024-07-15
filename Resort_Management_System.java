import java.util.Scanner;

public class Resort_Management_System {
    static String choice;
    static String answer;
    static String customerName;
    static int roomNumber = 0;
    static String[] hotel = new String[10];
    static Scanner input = new Scanner(System.in);

    // main function
    public static void main(String[] args) {

        initialize(hotel);
        menu();

    }

    private static void initialize(String hotelRef[]) {
        for (int x = 0; x < 10; x++) {
            hotelRef[x] = "e";
        }
    }

    public static void menu() {

        System.out.println("\n======================================================");
        System.out.println("|             Resort Management System               |");
        System.out.println("|            ( BY : Avinash Kumar Singh )            |");
        System.out.println("======================================================\n");
        System.out.println("* Option 'V'  - View all rooms");
        System.out.println("* Option 'CI' - Check in");
        System.out.println("* Option 'E'  - Display Empty rooms");
        System.out.println("* Option 'CO' - Check out");
        System.out.println("* Option 'F'  - Find room from customer name");
        System.out.println("* Option 'O'  - View rooms Ordered alphabetically by name");
        System.out.println("* Option 'Q'  - Quit Program\n");
        System.out.println("======================================================\n");

        do {
            System.out.println();
            System.out.print("Choose an option : ");
            choice = input.next();
            if (choice.equalsIgnoreCase("v")) {
                viewRooms();
            } else if (choice.equalsIgnoreCase("ci")) {
                checkIn();
            } else if (choice.equalsIgnoreCase("e")) {
                displayEmptyRooms();
            } else if (choice.equalsIgnoreCase("co")) {
                checkOut();
            } else if (choice.equalsIgnoreCase("f")) {
                findRoom();
            } else if (choice.equalsIgnoreCase("o")) {
                alphabeticalOrder();
            } else if (choice.equalsIgnoreCase("q")) {
                System.out.println("Thanks.......");
                System.exit(0);
            } else {
                System.out.println("Invalid input !!!");
            }

        } while (true);

    }

    private static void viewRooms() {

        while (roomNumber < 10) {
            for (int x = 0; x < 10; x++) {
                if (!(hotel[x].equals("e"))) {
                    System.out.println("Room No. " + x + " is occupied by Mr. " + hotel[x]);
                } else {
                    System.out.println("Room No. " + x + " is empty");
                }
            }
            break;
        }
        System.out.println("");
        menu();
    }

    private static void checkIn() {

        boolean invalidRoomNumber;

        do {
            invalidRoomNumber = false;
            try {

                System.out.print("\nEnter room number (0-9) : ");
                roomNumber = input.nextInt();

                if (!(hotel[roomNumber].equals("e"))) {
                    invalidRoomNumber = true;
                    System.out.println("\nThis room is occupied by: Mr. " + hotel[roomNumber]);
                    System.out.println("");

                } else if (roomNumber >= 0 && roomNumber < 10) {
                    invalidRoomNumber = false;

                } else {
                    invalidRoomNumber = true;
                    System.out.println("\nInvalid input! Please Enter a value between 0-9");
                    System.out.println("");
                }

            } catch (Exception e) {
                invalidRoomNumber = true;
                System.out.println("\nInvalid input! Please Enter a value between 0-9");
                System.out.println("");
                input.next();

            }
        } while (invalidRoomNumber);
        System.out.print("\nEnter the name of the customer :");
        customerName = input.next();
        hotel[roomNumber] = customerName;

        do {
            System.out.print("Do you want to continue adding records?(Y/N) : ");
            answer = input.next();
            if (answer.equalsIgnoreCase("y")) {
                checkIn();
            } else if (answer.equalsIgnoreCase("n")) {
                System.out.println("");
                menu();
            }

        } while (!(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n")));

    }

    private static void displayEmptyRooms() {
        for (int x = 0; x < 10; x++) {
            if (hotel[x].equals("e")) {
                System.out.println("room " + x + " is empty");
            }
        }
        System.out.println("");
        menu();
    }

    private static void checkOut() {

        boolean invalidInput;
        do {
            invalidInput = false;
            try {
                System.out.print("\nEnter the Room's number which you want to vacate : ");
                roomNumber = input.nextInt();

                if (!(hotel[roomNumber].equals("e"))) {
                    invalidInput = false;
                    hotel[roomNumber] = "e";

                } else {
                    invalidInput = true;
                    System.out.println("Room " + roomNumber + " is already Empty");
                    System.out.println("");
                }

            } catch (Exception e) {
                invalidInput = true;
                System.out.println("Invalid input! Please Enter a value between 0-9");
                System.out.println("");
                input.next();
            }

        } while (invalidInput);
        System.out.println("Room " + roomNumber + " has successfully been vacated");
        System.out.println("");
        menu();
    }

    private static void findRoom() {

        System.out.print("Enter the name of the customer : ");
        boolean found = false;
        String find = input.next();

        for (int n = 0; n < 10; n++) {
            if (hotel[n].equalsIgnoreCase(find)) {
                found = true;
                System.out.println("Mr. " + find + " is staying in room No. " + n);
                System.out.println("");
                menu();
            }
        }

        if (found == false) {
            System.out.println(find + " doesn't exist on our database");
            System.out.println("");
            menu();
        }

    }

    private static void alphabeticalOrder() {

        int index = 0;
        String[] names = new String[10];

        // copy hotel array data to names array
        for (int x = 0; x < 10; x++) {
            names[x] = hotel[x].toLowerCase();
        }

        // used Bubble sort
        for (int i = 0; i < names.length - 1; i++) {
            for (int j = i + 1; j < names.length; j++) {
                if (names[j].compareTo(names[i]) < 0) {
                    String temp = names[j];
                    names[j] = names[i];
                    names[i] = temp;
                }
            }
        }

        for (int x = 0; x < names.length; x++) {
            if (!(names[x].equals("e"))) {

                for (int i = 0; i < hotel.length; i++) {
                    if (hotel[i].toLowerCase().equals(names[x])) {
                        index = i;
                    }
                }
                System.out.println("Mr. " + names[x] + " is staying in room No. " + index);
            }
        }
        System.out.println("");
        menu();
    }
}
