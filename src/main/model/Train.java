package main.model;

public class Train {
    public static final String TABLE_NAME = "train";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";
    public static final String COUNT_WAGON_COLUMN = "count_wagon";

    private Long id;
    private String name;
    private int count_wagon;

    public Train() {

    }

    public Train(String name, int count_wagon) {
        this.name = name;
        this.count_wagon = count_wagon;
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

    public int getCount_wagon() {
        return count_wagon;
    }

    public void setCount_wagon(int count_wagon) {
        this.count_wagon = count_wagon;
    }

    @Override
    public String toString() {
        return "Train[id= " + this.id + ", name= " + this.name + ", countWagon= " + this.count_wagon + "]";
    }
}
