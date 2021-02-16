package main.model;

import java.util.ArrayList;
import java.util.List;

public class Train {
    public static final String TABLE_NAME = "train";
    public static final String NAME_COLUMN = "name";
    public static final String COUNT_WAGONS_COLUMN = "count_wagons";

    public static final String ID_COLUMN = "id";

    private Long id;
    private List<Wagon> wagons = new ArrayList<Wagon>(10);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Wagon> getWagons() {
        return wagons;
    }

    public void setWagons(List<Wagon> wagons) {
        this.wagons = wagons;
    }

    @Override
    public String toString() {
        String result = "Train[id= " + this.id + ", wagons{";
        StringBuilder listWagons = new StringBuilder(" ");
        for (Wagon wagon : wagons) {
            listWagons.append(wagon.getId());
        }
        return result + listWagons + "}]";
    }
}
