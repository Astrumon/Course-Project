package main.model;

import java.util.ArrayList;
import java.util.List;

public class Train {
    public static final String TABLE_NAME = "train";
    public static final String NAME_COLUMN = "name";
    public static final String COUNT_WAGONS_COLUMN = "count_wagons";
    public static final String ID_WAGON_COLUMN = "id_wagon";
    public static final String POS_WAGON_COLUMN = "pos_wagon";


    public static final String ID_COLUMN = "id";

    private int posWagon;
    private Long id, idWagon;
    private String name;
    private int countWagons;
    private List<Wagon> wagons = new ArrayList<Wagon>(10);

    public Train() {

    }

    public Train(Long id, Long id_wagon, int countWagons, String name) {
        this.id = id;
        this.idWagon = id_wagon;
        this.countWagons = countWagons;
        this.name = name;
    }

    public int getPosWagon() {
        return posWagon;
    }

    public void setPosWagon(int posWagon) {
        this.posWagon = posWagon;
    }

    public Long getIdWagon() {
        return idWagon;
    }

    public void setIdWagon(Long idWagon) {
        this.idWagon = idWagon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountWagons() {
        return countWagons;
    }

    public void setCountWagons(int countWagons) {
        this.countWagons = countWagons;
    }

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
//        String result = "Train[id= " + this.id + ", wagons{";
////        StringBuilder listWagons = new StringBuilder(" ");
////        for (Wagon wagon : wagons) {
////            listWagons.append(wagon.getId());
////        }
////        return result + listWagons + "}]";
        return "Train[id= " + this.id + ", idWagon= " + this.idWagon + ", countsWagon= "
                + this.countWagons + ", name= " + this.name + ", posWagon= " + this.posWagon;
    }
}
