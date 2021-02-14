package main.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Warehouse {
    public static final String TABLE_NAME = "warehouse";
    public static final String ID_COLUMN = "id";
    public static final String WAGON_COLUMN = "wagon_id";

    private Long id, idWagon;
    private Set<Wagon> wagons = new HashSet<Wagon>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Wagon> getWagons() {
        return wagons;
    }

    public Long getIdWagon() {
        return idWagon;
    }

    public void setIdWagon(Long idWagon) {
        this.idWagon = idWagon;
    }

    public void setWagons(Set<Wagon> wagons) {
        this.wagons = wagons;
    }

    @Override
    public String toString() {
        return "Warehouse[id= " + this.id + " id_wagon = " +this.idWagon + "]";
    }
}
