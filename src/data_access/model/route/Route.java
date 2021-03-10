package data_access.model.route;

public class Route {
    public static final String TABLE_NAME = "route";
    public static final String FROM_TOWN_COLUMN = "from_town";
    public static final String TO_TOWN_COLUMN = "to_town";
    public static final String ID_COLUMN = "id";
    public static final String PRICE_COLUMN = "price";
    public static final String TIME_START_COLUMN = "time_start";
    public static final String TIME_END_COLUMN = "time_end";
    public static final String ALL_TICKETS_COLUMN = "all_tickets";
    public static final String SOLD_TICKETS_COLUMN = "sold_tickets";

    private Long id;
    private String toTown, fromTown, timeStart, timeEnd;
    private int price;
    private int allTickets, soldTickets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToTown() {
        return toTown;
    }

    public void setToTown(String toTown) {
        this.toTown = toTown;
    }

    public String getFromTown() {
        return fromTown;
    }

    public void setFromTown(String fromTown) {
        this.fromTown = fromTown;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAllTickets() {
        return allTickets;
    }

    public void setAllTickets(int allTickets) {
        this.allTickets = allTickets;
    }

    public int getSoldTickets() {
        return soldTickets;
    }

    public void setSoldTickets(int soldTickets) {
        this.soldTickets = soldTickets;
    }

    public String toString() {
        return "Route[ id= " + this.id
                + ", toTown= " + this.toTown
                + ", fromTown= " + this.fromTown
                + ", timeStart= " + this.timeStart
                + ", timeEnd= " + this.timeEnd
                + ", price= " + this.price
                + ", allTickets= " + this.allTickets
                + ", soldTickets= " + this.soldTickets
                + "]";
    }
}
