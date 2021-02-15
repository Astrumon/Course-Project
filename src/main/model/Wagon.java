package main.model;

public class Wagon {
    public static final String TABLE_NAME = "wagon";
    public static final String ID_COLUMN = "id";
    public static final String ID_TRAIN = "id_train";
    public static final String ID_WAREHOUSE = "id_warehouse";
    public static final String POSITION_TRAIN = "pos_train";
    public static final String SEATING_CAPACITY = "seatingCapacity";

    private Long id;
    private int seatingCount;
    private Long idTrain;
    private Long idWarehouse;
    private int posTrain;

    public Wagon() {

    }

    public Wagon(Long id, int seatingCount, Long idTrain, Long idWarehouse, int posTrain) {
        this.id = id;
        this.seatingCount = seatingCount;
        this.idTrain = idTrain;
        this.idWarehouse = idWarehouse;
        this.posTrain = posTrain;
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

    @Override
    public String toString() {
        return "Wagon [id= " + this.id + ", seatingCapacity= " + this.seatingCount
                + ", idWarehouse= " + this.idWarehouse + ", idTrain= " + this.idTrain
                + ", posTrain= " + this.posTrain + "]";
    }
}
