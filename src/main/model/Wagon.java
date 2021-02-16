package main.model;

public class Wagon {
    public static final String TABLE_NAME = "wagon";
    public static final String ID_COLUMN_COLUMN = "id";
    public static final String ID_TRAIN_COLUMN = "id_train";
    public static final String ID_WAREHOUSE_COLUMN = "id_warehouse";
    public static final String POSITION_TRAIN_COLUMN = "pos_train";
    public static final String SEATING_CAPACITY_COLUMN = "seatingCapacity";
    public static final String TYPE_COLUMN = "type";

    public static final int PASSENGER_TYPE = 1;
    public static final int CARGO_TYPE = 2;

    private Long id;
    private int seatingCount;
    private Long idTrain;
    private Long idWarehouse;
    private int posTrain, type;

    public Wagon() {

    }

    public Wagon(Long id, int seatingCount, Long idTrain, Long idWarehouse, int posTrain, int type) {
        this.id = id;
        this.seatingCount = seatingCount;
        this.idTrain = idTrain;
        this.idWarehouse = idWarehouse;
        this.posTrain = posTrain;

        checkType(type);

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        checkType(type);
    }

    private void checkType(int type) {
       if (!(type > 2)) {
           this.type = type;
       } else {
           this.type = PASSENGER_TYPE;
       }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(Long idTrain) {
        this.idTrain = idTrain;
    }

    public Long getIdWarehouse() {
        return idWarehouse;
    }

    public void setIdWarehouse(Long idWarehouse) {
        this.idWarehouse = idWarehouse;
    }

    public int getPosTrain() {
        return posTrain;
    }

    public void setPosTrain(int posTrain) {
        this.posTrain = posTrain;
    }

    public int getSeatingCount() {
        return seatingCount;
    }

    public void setSeatingCount(int seatingCount) {
        this.seatingCount = seatingCount;
    }

    private String defineType(int type) {
        String result = "";
        switch (type) {
            case PASSENGER_TYPE :result = " passenger ";
            break;
            case CARGO_TYPE : result = " cargo ";
            break;
        }

        return result;
    }

    @Override
    public String toString() {
        return "Wagon [id= " + this.id + ", seatingCapacity= " + this.seatingCount
                + ", idWarehouse= " + this.idWarehouse + ", idTrain= " + this.idTrain
                + ", posTrain= " + this.posTrain + ", type= " + defineType(this.type)  +"]";
    }
}
