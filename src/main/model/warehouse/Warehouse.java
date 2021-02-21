package main.model.warehouse;

import main.model.Wagon;

import java.util.HashSet;
import java.util.Set;

public class Warehouse {
    public static final String TABLE_NAME = "warehouse";
    public static final String ID_COLUMN = "id";
    public static final String CAPACITY_COLUMN = "capacity";
    public static final String NAME_WAREHOUSE_COLUMN = "name";

    private Long id;
    private int capacity;
    private String name;

    public Warehouse() {

    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Warehouse(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Warehouse[id= " + this.id + " capacity= " + this.capacity + " name= " + this.name + "]";
    }
}
