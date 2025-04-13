import java.util.List;
public class Attendee extends User {
    private String address;
    private Wallet wallet;
    private List<String> interests;

    public Attendee(String username, String password, String dateOfBirth, Gender gender,
                    String address, Wallet wallet, List<String> interests) {
        super(username, password, dateOfBirth, gender);
        this.address = address;
        this.wallet = wallet;
        this.interests = interests;
    }
    // setters and getters
    public String getAddress() {
        return address;
    }

    public double getBalance() {
        return wallet.getBalance();
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addInterest(String interest){
        interests.add(interest);
    }

    // financial functions
    public void addFunds(double amount) {
        wallet.addFunds(amount);
    }


    public boolean buyTicket(double ticketPrice) {
        return wallet.withdraw(ticketPrice);
    }
}