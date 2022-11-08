import java.util.Scanner;

public class Movie {

    private int showID;
    private String showTitle;
    private int numOfRows;
    private int seatsPerRow;
    private int cancelWindow;
    public boolean[][] seatTaken;


    // Sets up Seating Information.
    public void setup() {
        // Some nice ASCII art :)
        System.out.println("   _____            _   _                _____      _               \n" +
                "  / ____|          | | (_)              / ____|    | |              \n" +
                " | (___   ___  __ _| |_ _ _ __   __ _  | (___   ___| |_ _   _ _ __  \n" +
                "  \\___ \\ / _ \\/ _` | __| | '_ \\ / _` |  \\___ \\ / _ \\ __| | | | '_ \\ \n" +
                "  ____) |  __/ (_| | |_| | | | | (_| |  ____) |  __/ |_| |_| | |_) |\n" +
                " |_____/ \\___|\\__,_|\\__|_|_| |_|\\__, | |_____/ \\___|\\__|\\__,_| .__/ \n" +
                "                                 __/ |                       | |    \n" +
                "                                |___/                        |_|    ");

        System.out.println("Enter Show Number, Show Title, Number of Rows, Number of seats per row, Cancellation window in minutes:\n");

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Show ID number:");
        showID = sc.nextInt();

        //consumes dangling newline character
        sc.nextLine();

        System.out.println("Enter Show Title:");
        showTitle = sc.nextLine();

        System.out.println("Key in number of rows (between 1 and 26):");
        numOfRows = inputWithLimit(numOfRows, 1, 26);

        System.out.println("Key in number of seats per row (between 1 and 10):");
        seatsPerRow = inputWithLimit(seatsPerRow, 1, 10);

        System.out.println("Key in time window for cancellation (in minutes):");
        cancelWindow = sc.nextInt();

        seatTaken = new boolean[numOfRows][seatsPerRow];
    }

    // Asks for inputs with constraints on the input value, num is the variable in which the constraints are to be imposed on
    // Access modifier can optionally be put to public if usage in other classes is desired
    private int inputWithLimit(int num, int lower, int upper) {
        Scanner sc2 = new Scanner(System.in);
        num = sc2.nextInt();
        while (num > upper || num < lower) {
            if (num == -1) {
                return 0;
            }
            System.out.println("Error, please retry. Value should be integer between " + lower + " and " + upper + ". Type -1 to skip.");
            num = sc2.nextInt();
        }
        return num;
    }


    // Prints Seating Information
    public void statement() {
        System.out.println("\nMovie Seating Information \nShowID: " + showID + "\nShow title: " + showTitle);
        System.out.println("Seating consists of " + numOfRows + " rows and " + seatsPerRow + " seats per row");
        System.out.println("Cancellation window is " + cancelWindow + " minutes.\n");
    }

    public int getShowID() {
        return showID;
    }


    public int getNumOfRows() {
        return numOfRows;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public boolean[][] getSeatTaken() {
        return seatTaken;
    }

    public int getCancelWindow() {
        return cancelWindow;
    }


    // For Testing in MovieTest.java
    public void setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
    }

    public void setSeatsPerRow(int seatsPerRow) {
        this.seatsPerRow = seatsPerRow;
    }
}
