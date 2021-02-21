package main.model.train;

import main.model.Wagon;

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

    public TrainSet( String name, int posWagon) {
        this.posWagon = posWagon;
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
        return "TrainSet[ trainName= " + this.name + ", idWagon= " + this.idWagon + ", posWagon=" + this.posWagon +"]";
    }
}