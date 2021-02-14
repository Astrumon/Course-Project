package main.model;

import java.util.ArrayList;
import java.util.List;

public class Train {
    public static final String TABLE_NAME = "train";

    public static final String FIRST_WAGON_COLUMN = "firstWagon_id";
    public static final String SECOND_WAGON_COLUMN = "secondWagon_id";
    public static final String THIRD_WAGON_COLUMN = "thirdWagon_id";
    public static final String FOURTH_WAGON_COLUMN = "fourthWagon_id";
    public static final String FIFTH_WAGON_COLUMN = "fifthWagon_id";
    public static final String SIXTH_WAGON_COLUMN = "sixthWagon_id";
    public static final String SEVENTH_WAGON_COLUMN = "seventhWagon_id";
    public static final String EIGHTH_WAGON_COLUMN = "eighthWagon_id";
    public static final String NINTH_WAGON_COLUMN = "ninthWagon_id";
    public static final String TENTH_WAGON_COLUMN = "tenthWagon_id";

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
