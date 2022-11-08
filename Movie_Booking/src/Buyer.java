import java.util.*;

public class Buyer {

    private int phoneNum;
    private UUID ticketNum;
    private List<String> bookedSeats;
    private Date bookingTime;
    private int showNum;


    public static void getAvailability() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Show ID number:");
        int showId = sc.nextInt();

        if (Main.movieHashmap.get(showId).getNumOfRows() == 0 || Main.movieHashmap.get(showId).getSeatsPerRow() == 0) {
            System.out.println("No Seats Available!");
        }
        for (int i = 0; i < Main.movieHashmap.get(showId).getNumOfRows(); i++) {
            char x = 'A';
            for (int j = 0; j < Main.movieHashmap.get(showId).getSeatsPerRow(); j++) {
                if (!Main.movieHashmap.get(showId).seatTaken[i][j]) {
                    System.out.print((char) (x + i) + "" + j + ", ");
                }
            }
            x += 1;
            System.out.println("");
        }
    }

    public void book() {
        ticketNum = UUID.randomUUID();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter phone number, followed by show number, followed by Seats to Book (Separated by a ', ':");
        phoneNum = sc.nextInt();
        showNum = sc.nextInt();
        sc.nextLine();
        String seatList = sc.nextLine();

        String[] bookingList = seatList.split(", ");

        List<String> successfulBookingList = new ArrayList<>();
        List<String> unsuccessfulBookingList = new ArrayList<>();
        for (String bookedSeat : bookingList) {
            int i = Integer.parseInt(String.valueOf(bookedSeat.charAt(1)));
            if (!Main.movieHashmap.get(showNum).seatTaken[bookedSeat.charAt(0) - 65][i]) {
                Main.movieHashmap.get(showNum).seatTaken[bookedSeat.charAt(0) - 65][i] = true;
                successfulBookingList.add(bookedSeat);
            } else {
                unsuccessfulBookingList.add(bookedSeat);
            }
        }

        // Checks if Unsuccessful Booking List contains any items
        if (!unsuccessfulBookingList.isEmpty()) {
            System.out.println("The following Seat(s) are not available to be booked: " + unsuccessfulBookingList + "\n");
        }
        // Checks if Successful Booking List contains any items
        if (!successfulBookingList.isEmpty()) {
            System.out.println("Ticket Booked.\nSeat Number(s) are " + successfulBookingList + "\nTicket UUID is: " + ticketNum);
            bookedSeats = successfulBookingList;
            bookingTime = new Date();
            System.out.println("Booking Time is:" + bookingTime);
        }

    }

    public static void cancel() {
        System.out.println("Enter Ticket Number, followed by Phone Number: ");
        Scanner sc = new Scanner(System.in);
        UUID cancellationTicketNum = UUID.fromString(sc.nextLine());
        int cancellationPhoneNum = sc.nextInt();
        Buyer buyer = Main.bookingsHashmap.get(cancellationTicketNum);

        Date currentTime = new Date();

        if (cancellationTicketNum.equals(buyer.ticketNum) &&
                (cancellationPhoneNum == buyer.phoneNum) &&
                ((currentTime.getTime() - buyer.bookingTime.getTime()) / 1000 < (60 * Main.movieHashmap.get(buyer.showNum).getCancelWindow()))) {
            for (String bookedSeat : buyer.bookedSeats) {
                Main.movieHashmap.get(buyer.showNum).seatTaken[bookedSeat.charAt(0) - 65][Integer.parseInt(String.valueOf(bookedSeat.charAt(1)))] = false;
            }
            System.out.println("Ticket successfully cancelled!");
        } else {
            System.out.println("Ticket unsuccessfully cancelled.");
        }

    }

    public UUID getTicketNum() {
        return ticketNum;
    }
}
