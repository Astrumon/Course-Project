package main.model;

public class Wagon {
    public static final String TABLE_NAME = "wagon";
    public static final String ID_COLUMN = "id";
    public static final String SEATING_CAPACITY = "seatingCapacity";

    private Long id;
    private int seatingCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSeatingCount() {
        return seatingCount;
    }

    public void setSeatingCount(int seatingCount) {
        this.seatingCount = seatingCount;
    }

    @Override
    public String toString() {
        return "Wagon [id= " + this.id + ", seatingCapacity= " + this.seatingCount + "]";
    }
}
