package data_access.model.train;

public class Train {
    public static final String TABLE_NAME = "train";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";
    public static final String COUNT_WAGON_COLUMN = "count_wagon";
    public static final String CAPACITY_COLUMN = "capacity";

    private Long id;
    private String name;
    private int countWagon, capacity;

    public Train() {

    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Train(String name, int countWagon) {
        this.name = name;
        this.countWagon = countWagon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountWagon() {
        return countWagon;
    }

    public void setCountWagon(int countWagon) {
        this.countWagon = countWagon;
    }

    @Override
    public String toString() {
        return "Train[name= "  +  this.name + ", countWagon= " + this.countWagon + "]";
    }
}
