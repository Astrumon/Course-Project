package data_access.model.warehouse;

public class WarehouseSet {
    public static final String TABLE_NAME = "warehouse_set";
    public static final String NAME_COLUMN = "name_warehouse";
    public static final String ID_WAGON_COLUMN = "id_wagon";
    public static final String ID_COLUMN = "id";
    public static final String POSITION_COLUMN = "position";
    public static final String ID_WAREHOUES_COLUMN = "id_warehouse";


    private String nameWarehouse;
    private Long idWagon, idWarehouse;
    private Long id;
    private int position;

    public Long getIdWarehouse() {
        return idWarehouse;
    }

    public void setIdWarehouse(Long idWarehouse) {
        this.idWarehouse = idWarehouse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WarehouseSet() {

    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public WarehouseSet(String nameWarehouse, int position, Long idWarehouse) {
        this.nameWarehouse = nameWarehouse;
        this.position = position;
        this.idWarehouse = idWarehouse;
    }

    public WarehouseSet(String nameWarehouse, Long idWagon) {
        this.nameWarehouse = nameWarehouse;
        this.idWagon = idWagon;
    }

    public String getNameWarehouse() {
        return nameWarehouse;
    }

    public void setNameWarehouse(String nameWarehouse) {
        this.nameWarehouse = nameWarehouse;
    }

    public Long getIdWagon() {
        return idWagon;
    }

    public void setIdWagon(Long idWagon) {
        this.idWagon = idWagon;
    }

    @Override
    public String toString() {
     return "WarehouseSet[ warehouseName= " + this.nameWarehouse
             + ", idWagon= " + this.idWagon
             + ", №" + this.position
             + ", id_warehouse= " + this.idWarehouse
             +" ]";
    }
}
