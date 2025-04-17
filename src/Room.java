import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
 //maded by Ahmed Ashraf 16/4/2025
 // don't forget to make thesse
// ERROR HANDLING TO IMPLEMENT:
// 1. Constructor: Validate name (non-empty), capacity (positive)
// 2. bookDate(): Check for null/duplicate dates
// 3. getBookedDates(): Return defensive copy
// 4. equals()/hashCode(): Keep sync for collections
// 5. Add null checks for critical methods

public class Room {

    private final String name;
    private final int capacity;
    private final String location;
    private final List<LocalDate> bookedDates;

   //excibation handling and constructors
    public Room(String name, int capacity, String location) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Room name cannot be empty");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Room location cannot be empty or null!");
        }


        this.name = name.trim();
        this.capacity = capacity;
        this.location = location.trim();
        this.bookedDates = new ArrayList<>();
    }


    // Core functionality
    /**
     * Checks if the room is available on a specific date
     * @return true if available, false if already booked
     */

    public boolean isAvailableOn(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        return !bookedDates.contains(date);
    }

    /**
     * Books the room for a specific date
     * @throws IllegalStateException if room is already booked on that date
     */
    public void bookDate(LocalDate date) {
        if (!isAvailableOn(date)) {
            throw new IllegalStateException("Room already booked on " + date);
        }
        bookedDates.add(date);
    }

    // Getters (no setters - rooms are immutable after creation)
    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getLocation() {
        return location;
    }

    /**
     * return Defensive copy of booked dates to prevent external modification
     */
    public List<LocalDate> getBookedDates() {
        return new ArrayList<>(bookedDates);
    }

     @Override
     public String toString() {
         return "Room[name=" + name + ", capacity=" + capacity + ", location=" + location + "]";
     }
    // Equality check (important for database operations)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return name.equalsIgnoreCase(room.name);
    }


}
