class TicketBooking {
    private boolean[] seats;  // Array representing the availability of seats

    public TicketBooking(int numSeats) {
        seats = new boolean[numSeats];  // Initialize all seats as unbooked (false)
    }

    // Synchronized method to book a seat
    public synchronized boolean bookSeat(int seatNumber, String customerType) {
        if (seatNumber < 0 || seatNumber >= seats.length) {
            System.out.println("Invalid seat number.");
            return false;
        }
        
        // Check if the seat is already booked
        if (seats[seatNumber]) {
            System.out.println(customerType + " booking failed: Seat " + seatNumber + " is already booked.");
            return false;
        } else {
            // Book the seat
            seats[seatNumber] = true;
            System.out.println(customerType + " successfully booked Seat " + seatNumber + ".");
            return true;
        }
    }

    // Method to display available seats
    public synchronized void showAvailableSeats() {
        System.out.print("Available Seats: ");
        for (int i = 0; i < seats.length; i++) {
            if (!seats[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}

class BookingThread extends Thread {
    private TicketBooking ticketBooking;
    private int seatNumber;
    private String customerType;

    public BookingThread(TicketBooking ticketBooking, int seatNumber, String customerType) {
        this.ticketBooking = ticketBooking;
        this.seatNumber = seatNumber;
        this.customerType = customerType;
    }

    @Override
    public void run() {
        // Try to book a seat
        ticketBooking.bookSeat(seatNumber, customerType);
    }
}

public class TicketBookingSystem {
    public static void main(String[] args) {
        // Create a ticket booking system with 5 seats
        TicketBooking ticketBooking = new TicketBooking(5);

        // Create and start regular booking threads (priority 5)
        BookingThread regularBooking1 = new BookingThread(ticketBooking, 0, "Regular");
        BookingThread regularBooking2 = new BookingThread(ticketBooking, 1, "Regular");

        regularBooking1.setPriority(Thread.NORM_PRIORITY);  // Regular priority
        regularBooking2.setPriority(Thread.NORM_PRIORITY);  // Regular priority

        // Create and start VIP booking threads (priority 10)
        BookingThread vipBooking1 = new BookingThread(ticketBooking, 2, "VIP");
        BookingThread vipBooking2 = new BookingThread(ticketBooking, 3, "VIP");

        vipBooking1.setPriority(Thread.MAX_PRIORITY);  // High priority (VIP)
        vipBooking2.setPriority(Thread.MAX_PRIORITY);  // High priority (VIP)

        // Start all threads
        vipBooking1.start();
        vipBooking2.start();
        regularBooking1.start();
        regularBooking2.start();

        // Wait for all threads to complete
        try {
            vipBooking1.join();
            vipBooking2.join();
            regularBooking1.join();
            regularBooking2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Show available seats after bookings
        ticketBooking.showAvailableSeats();
    }
}
