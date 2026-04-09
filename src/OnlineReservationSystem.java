/*username =admin
  password 1234*/
import java.util.*;

class Reservation {
    String pnr;
    String name;
    int trainNo;
    String trainName;
    String classType;
    String date;
    String from;
    String to;

    Reservation(String pnr, String name, int trainNo, String trainName,
                String classType, String date, String from, String to) {
        this.pnr = pnr;
        this.name = name;
        this.trainNo = trainNo;
        this.trainName = trainName;
        this.classType = classType;
        this.date = date;
        this.from = from;
        this.to = to;
    }
}

public class OnlineReservationSystem {

    static Scanner sc = new Scanner(System.in);

    // Central database
    static HashMap<String, Reservation> database = new HashMap<>();

    // Login credentials
    static final String USERNAME = "admin";
    static final String PASSWORD = "1234";

    // Train data
    static HashMap<Integer, String> trains = new HashMap<>();

    public static void main(String[] args) {

        trains.put(101, "Rajdhani Express");
        trains.put(102, "Shatabdi Express");
        trains.put(103, "Duronto Express");

        if (login()) {
            menu();
        } else {
            System.out.println("❌ Invalid Login! Program Terminated.");
        }
    }

    // LOGIN MODULE
    static boolean login() {
        System.out.println("===== LOGIN FORM =====");
        System.out.print("Enter Username: ");
        String user = sc.next();
        System.out.print("Enter Password: ");
        String pass = sc.next();

        return user.equals(USERNAME) && pass.equals(PASSWORD);
    }

    // MAIN MENU
    static void menu() {
        int choice;
        do {
            System.out.println("\n===== ONLINE RESERVATION SYSTEM =====");
            System.out.println("1. Reservation");
            System.out.println("2. Cancellation");
            System.out.println("3. Exit");
            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> reservation();
                case 2 -> cancellation();
                case 3 -> System.out.println("Thank You for Using the System!");
                default -> System.out.println("Invalid Choice!");
            }
        } while (choice != 3);
    }

    // RESERVATION MODULE
    static void reservation() {
        System.out.println("\n===== RESERVATION FORM =====");

        sc.nextLine();
        System.out.print("Enter Passenger Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Train Number: ");
        int trainNo = sc.nextInt();

        String trainName = trains.get(trainNo);
        if (trainName == null) {
            System.out.println("Invalid Train Number!");
            return;
        }

        System.out.println("Train Name: " + trainName);

        System.out.print("Enter Class Type (Sleeper/AC): ");
        String classType = sc.next();

        System.out.print("Enter Date of Journey (DD/MM/YYYY): ");
        String date = sc.next();

        System.out.print("From: ");
        String from = sc.next();

        System.out.print("To: ");
        String to = sc.next();

        String pnr = generatePNR();
        Reservation r = new Reservation(pnr, name, trainNo, trainName,
                classType, date, from, to);

        database.put(pnr, r);

        System.out.println("\n✅ Reservation Successful!");
        System.out.println("Your PNR Number: " + pnr);
    }

    // CANCELLATION MODULE
    static void cancellation() {
        System.out.println("\n===== CANCELLATION FORM =====");
        System.out.print("Enter PNR Number: ");
        String pnr = sc.next();

        Reservation r = database.get(pnr);
        if (r == null) {
            System.out.println("❌ PNR Not Found!");
            return;
        }

        System.out.println("\nReservation Details:");
        System.out.println("Name: " + r.name);
        System.out.println("Train: " + r.trainName);
        System.out.println("Class: " + r.classType);
        System.out.println("Date: " + r.date);
        System.out.println("From: " + r.from + " To: " + r.to);

        System.out.print("\nConfirm Cancellation (yes/no): ");
        String confirm = sc.next();

        if (confirm.equalsIgnoreCase("yes")) {
            database.remove(pnr);
            System.out.println("✅ Ticket Cancelled Successfully!");
        } else {
            System.out.println("Cancellation Aborted.");
        }
    }

    // PNR GENERATOR
    static String generatePNR() {
        return "PNR" + (100000 + new Random().nextInt(900000));
    }
}
