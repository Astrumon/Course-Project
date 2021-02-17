package main.model;

import java.util.ArrayList;
import java.util.List;

public class TrainSet {
    public static final String TABLE_NAME = "train_set";
    public static final String NAME_COLUMN = "name";
    public static final String ID_WAGON_COLUMN = "id_wagon";
    public static final String POS_WAGON_COLUMN = "pos_wagon";
    public static final String ID_COLUMN = "id";

    private int posWagon;
    private Long id, idWagon;
    private String name;
    private List<Wagon> wagons = new ArrayList<Wagon>(10);

    public TrainSet() {

    }

    public TrainSet( Long id_wagon, String name) {
        this.idWagon = id_wagon;
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
//        String result = "TrainSet[id= " + this.id + ", wagons{";
////        StringBuilder listWagons = new StringBuilder(" ");
////        for (Wagon wagon : wagons) {
////            listWagons.append(wagon.getId());
////        }
////        return result + listWagons + "}]";
        return "TrainSet[id= " + this.id + ", idWagon= " + this.idWagon
                + ", name= " + this.name + ", posWagon= " + this.posWagon;
    }
}
