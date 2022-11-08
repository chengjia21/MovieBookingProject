import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    static Map<Integer, Movie> movieHashmap = new HashMap<>();
    static Map<UUID, Buyer> bookingsHashmap = new HashMap<>();

    public static void main(String[] args) {
        // The command variable allows admins and buyers to access features regarding show booking such as:
        // setup, view, check availability, book, and cancel
        String command;
        boolean isRunning = true;

        Scanner sc = new Scanner(System.in);
        Movie movie = new Movie();


        while (isRunning) {
            System.out.println("Enter Command (Setup, View, Buy, Availability, Book, Cancel, Exit etc):");
            command = sc.nextLine().toLowerCase();

            switch (command) {
                case "setup":
                    // Setup seating plans for a show. Seating information would be displayed after setup completion
                    movie.setup();
                    movie.statement();
                    movieHashmap.put(movie.getShowID(), movie);
                    break;
                case "view":
                    // Display seating information
                    movie.statement();
                    break;
                case "exit":
                    // Exit
                    isRunning = false;
                    break;
                case "availability":
                    // Display seat availability
                    Buyer.getAvailability();
                    break;
                case "book":
                    // Book seats
                    Buyer buyer = new Buyer();
                    buyer.book();
                    bookingsHashmap.put(buyer.getTicketNum(), buyer);
                    break;
                case "cancel":
                    // Cancel Booking
                    Buyer.cancel();
                    break;
                default:
                    System.out.println("Command not found");
            }
        }
    }

}


