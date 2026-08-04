public class CinemaShow {
    private String title;
    private int seatsAvailable;
    private final int capacity;
    private static int totalBooked = 0;

    CinemaShow(String title, int capacity) {
        this.title = title;
        this.capacity = capacity;
        this.seatsAvailable = capacity;
    }

    CinemaShow(String title) {
        this(title, 100);
    }

    public boolean book(int n) {
        if (this.seatsAvailable >= n) {
            this.seatsAvailable -= n;
            totalBooked += n;
            return true;
        }
        else {
            return false;
        }
    }

    public void cancel(int n) {
        this.seatsAvailable += n;
        if (this.seatsAvailable > capacity) {
            this.seatsAvailable = capacity;
        }
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public static int getTotalBooked() {
        return totalBooked;
    }

    public static void main(String[] args) {
        CinemaShow show = new CinemaShow("Avengers", 200);
        System.out.println("Initial available seats: " + show.getSeatsAvailable());

        boolean booked = show.book(50);
        System.out.println("Booking 50 seats: " + booked);
        System.out.println("Available seats after booking: " + show.getSeatsAvailable());

        booked = show.book(160);
        System.out.println("Booking 160 seats: " + booked);
        System.out.println("Available seats after booking: " + show.getSeatsAvailable());

        show.cancel(30);
        System.out.println("Available seats after canceling 30: " + show.getSeatsAvailable());

        System.out.println("Total booked seats: " + CinemaShow.getTotalBooked());
    }
}