import java.util.ArrayList;
import java.util.Scanner;

public class Attendee extends User {
    private int choice;
    private ArrayList<Event> registeredEvents;
    private Wallet wallet;
    private ArrayList<Category> interests;
    //Methods 1
    public void showAllEvents() {
        if (Database.events == null || Database.events.isEmpty()) {
            System.out.println("No events available.");
            return;
        }

        System.out.println("Available Events:");
        for (int i = 0; i < Database.events.size(); i++) {
            Event event = Database.events.get(i);
            System.out.println((i + 1) + ". " + event.getName() + " - $" + event.getPrice());
        }
    }


    public void showEventsByInterest() {
        System.out.println("Events matching your interests:");
        for (int i = 0; i < Database.events.size(); i++) {
            Event event = Database.events.get(i);
            for (int j = 0; j < interests.size(); j++) {
                Category interest = interests.get(j);
                if (event.getCategory().equals(interest)) {
                    System.out.println((i + 1) + ". " + event.getName() + " - $" + event.getPrice());
                    break;
                }
            }
        }
    }
    //Method 3
    public void buyTicket(int eventIndex) {


        eventIndex--; // as it is 0 indexed

        if (eventIndex >= 0 && eventIndex < Database.events.size()) {
            Event selectedEvent = Database.events.get(eventIndex);
            if (wallet.withdraw(selectedEvent.getPrice())) {
                registeredEvents.add(selectedEvent);
                selectedEvent.addAttendee(this);
                System.out.println("Ticket purchased for: " + selectedEvent.getName());
            } else {
                System.out.println("Not enough balance.");
            }
        } else {
            System.out.println("Invalid event number.");
        }
    }
    //Method 4
    public void yourEvents(){
        for(int i = 0 ; i < registeredEvents.size(); i++)
        {
            Event event = registeredEvents.get(i);
            System.out.println((i + 1) + ". " + event.getName() + " - $" + event.getPrice());
        }
    }

    public void displayDashboard(){
        boolean found = false;

        for (int i = 0; i < Database.users.size(); i++) {
            User user = Database.users.get(i);
            if (user.getUsername().equals(username)) {
                found = true;
                Scanner scanner = new Scanner(System.in);

                for (int attempt = 0; attempt < 5; attempt++) {
                    //hna y5t el number el 3ayzo l7d ma yktb sah
                    System.out.println("Welcome " + username +
                            "\n1. View all available events" +
                            "\n2. Filter events by interest" +
                            "\n3. Buy a ticket" +
                            "\n4. View my event" +
                            "\n5. View wallet balance" +
                            "\n6. Add funds to wallet" +
                            "\n7. Log out" +
                            "\nEnter your choice:");

                    choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            showAllEvents();
                            break;
                        case 2:
                            showEventsByInterest();
                            break;
                        case 3:
                            showAllEvents();
                            System.out.println("Enter the number of the event you want to buy:");
                            Scanner input=new Scanner(System.in);
                            int eventNumber=input.nextInt();
                            buyTicket(eventNumber);
                            break;
                        case 4:
                            System.out.println("Your events are");

                            yourEvents();
                            break;
                        case 5:
                            System.out.println("Wallet balance: " + wallet.getBalance());
                            break;
                        case 6:
                            for (int attempts = 0; attempts < 5; attempts++) {
                                System.out.println("Please enter the amount of money you want to add to your balance");
                                Scanner Money = new Scanner(System.in);
                                int yourMoney = Money.nextInt();
                                if (yourMoney >= 0) {
                                    wallet.addFunds(yourMoney);
                                } else {
                                    System.out.println("The amount you entered isn't accepted please try again");
                                }
                                break;
                            }
                            break;
                        case 7:
                            System.out.println("Logging out...");
                            Database.login.start();
                            return; // exit the constructor
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }

                break; // after finding and processing user
            }
        }

        if (!found) {
            System.out.println("Username not found in the database.");
     }
}




}