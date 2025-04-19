import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
 //made by Ahmed Ashraf 16/4/2025
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
    private ArrayList<Event> events;

   //excibation handling and constructors
   public Room(String name, int capacity, String location) {
       if (name == null || name.trim().isEmpty())
           throw new IllegalArgumentException("Room name cannot be null or empty");
       if (capacity <= 0)
           throw new IllegalArgumentException("Capacity must be positive");
       if (location == null || location.trim().isEmpty())
           throw new IllegalArgumentException("Room location cannot be null or empty");

       this.name = name.trim();
       this.capacity = capacity;
       this.location = location.trim();
       this.events = new ArrayList<>();
   }


    // Core functionality
    /**
     * Checks if the room is available on a specific date
     * @return true if available, false if already booked
     */

    public boolean isAvailableOn(LocalDate date) {
        if (date == null)
            throw new IllegalArgumentException("Date cannot be null");

        for (int i = 0; i < events.size(); i++) {
            if (date.equals(events.get(i).getDate())) {
                return false;
            }
        }
        return true;
    }

    public void bookEvent(Event event) {
        LocalDate date = event.getDate();
        if (isAvailableOn(date))
            events.add(event);
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

    public ArrayList<Event> getEvents() {
        ArrayList<Event> copy = new ArrayList<>();
        for (int i = 0; i < events.size(); i++) {
            copy.add(events.get(i));
        }
        return copy;
    }

     @Override
     public String toString() {
         return "Room: name= " + name + ", capacity= " + capacity + ", location= " + location;
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
