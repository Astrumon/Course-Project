package data_access.model.wagon;

public class Place {
    public static final String TABLE_NAME = "place";
    public static final String ID_PLACE_COLUMN = "id_place";
    public static final String ID_WAGON_COLUMN = "id_wagon";
    public static final String NUMBER_COLUMN = "number";
    public static final String TYPE_COLUMN = "type";
    public static final String STATUS_COLUMN = "status";
    public static final String ID_COUNT_TYPE_COLUMN = "id_count_type";

    private Long idPlace, idWagon, idCountType;
    private int number;
    private int type, status;
    public static final int TAKEN = 1;
    public static final int FREE = 0;

    public Long getIdCountType() {
        return idCountType;
    }

    public void setIdCountType(Long idCountType) {
        this.idCountType = idCountType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Place() {

    }
    public Place(Long idPlace, Long idWagon, int number, int type) {
        this.idPlace = idPlace;
        this.idWagon = idWagon;
        this.number = number;
        this.type = type;
    }

    public Long getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(Long idPlace) {
        this.idPlace = idPlace;
    }

    public Long getIdWagon() {
        return idWagon;
    }

    public void setIdWagon(Long idWagon) {
        this.idWagon = idWagon;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String toString() {
        return "Place[ "
                + " idPlace = " + this.idPlace
                + " idWagon = " + this.idWagon
                + " number = " + this.number
                + " type = " + this.type
                + " idCountType = " + this.idCountType
                + "]";
    }
}
