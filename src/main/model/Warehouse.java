package main.model;

import java.util.HashSet;
import java.util.Set;

public class Warehouse {
    public static final String TABLE_NAME = "warehouse";
    public static final String ID_COLUMN = "id";
    public static final String WAGON_COLUMN = "wagon_id";
    public static final String NAME_WAREHOUSE_COLUMN = "name";

    private Long id, idWagon;
    private String name;
    private Set<Wagon> wagons = new HashSet<Wagon>();

    public Warehouse() {

    }
    public Warehouse(Long id, Long idWagon, String name) {
        this.id = id;
        this.name = name;
        this.idWagon = idWagon;
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

    public Set<Wagon> getWagons() {
        return wagons;
    }

    public void setWagons(Set<Wagon> wagons) {
        this.wagons = wagons;
    }

    public Long getIdWagon() {
        return idWagon;
    }

    public void setIdWagon(Long idWagon) {
        this.idWagon = idWagon;
    }

    @Override
    public String toString() {
        return "Warehouse[id= " + this.id + " id_wagon = " + this.idWagon + " name= " + this.name + "]";
    }
}
