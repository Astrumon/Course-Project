package data_access.model.wagon;

public class TypePlace {

    public static final String TABLE_NAME = "count_type_place";
    public static final String ID_TYPE_PLACE_COLUMN = "id_count_type_place";
    public static final String ID_WAGON_COLUMN = "id_wagon";
    public static final String COUNT_VIP_COLUMN = "count_vip";
    public static final String COUNT_MIDDLE_COLUMN = "count_middle";
    public static final String COUNT_LOW_COLUMN = "count_low";
    public static final String COUNT_SEATS_COLUMN = "count_seats";

    public static final int VIP = 1;
    public static final int MIDDLE = 2;
    public static final int LOW = 3;
    public static final int SEATS = 4;

    private int countVip, countMiddle, countLow, countSeats;
    private Long idTypePlace, idWagon;

    public TypePlace() {

    }

    public TypePlace(int countVip, int countMiddle, int countLow,
                     int countSeats, Long idTypePlace, Long idWagon) {
        this.countVip = countVip;
        this.countMiddle = countMiddle;
        this.countLow = countLow;
        this.countSeats = countSeats;
        this.idTypePlace = idTypePlace;
        this.idWagon = idWagon;
    }

    public int defineType(int type) {
        switch (type) {

            case VIP:
                return countVip;
            case MIDDLE:
                return countMiddle;

            case LOW:
                return countLow;

            case SEATS:
                return countSeats;

            default:
                return countSeats;
        }
    }

    public int getAllPlace() {
        return this.countSeats + this.countLow + this.countMiddle + countVip;
    }

    public int getCountVip() {
        return countVip;
    }

    public void setCountVip(int countVip) {
        this.countVip = countVip;
    }

    public int getCountMiddle() {
        return countMiddle;
    }

    public void setCountMiddle(int countMiddle) {
        this.countMiddle = countMiddle;
    }

    public int getCountLow() {
        return countLow;
    }

    public void setCountLow(int countLow) {
        this.countLow = countLow;
    }

    public int getCountSeats() {
        return countSeats;
    }

    public void setCountSeats(int countSeats) {
        this.countSeats = countSeats;
    }

    public Long getIdTypePlace() {
        return idTypePlace;
    }

    public void setIdTypePlace(Long idTypePlace) {
        this.idTypePlace = idTypePlace;
    }

    public Long getIdWagon() {
        return idWagon;
    }

    public void setIdWagon(Long idWagon) {
        this.idWagon = idWagon;
    }

    public String toString() {
        return "CountTypePlace[ "
                + "idTypePlace = " + this.idTypePlace
                + ", idWagon = " + this.idWagon
                + ", countVIP = " + this.countVip
                + ", countMIDDLE = " + this.countMiddle
                + ", countLOW = " + this.countLow
                + ", countSEATS = " + this.countSeats
                + "]";

    }
}
