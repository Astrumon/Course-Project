package main.dao;

import main.model.Warehouse;

import java.util.List;

public interface WarehouseDao {
    String SQL_FIND_ALL = "SELECT * FROM " + Warehouse.TABLE_NAME;
    String SQL_FIND_BY_ID = SQL_FIND_ALL + " WHERE " + Warehouse.ID_COLUMN + " = ?";
    String SQL_INSERT = "INSERT INTO " + Warehouse.TABLE_NAME + "(" + Warehouse.WAGON_COLUMN +"," + Warehouse.NAME_WAREHOUSE_COLUMN + ") VALUES ( ?, ?, ?)";
    String SQL_UPDATE = "UPDATE " + Warehouse.TABLE_NAME + " SET " + Warehouse.WAGON_COLUMN +
            "= ?," + Warehouse.NAME_WAREHOUSE_COLUMN + "= ? " + " WHERE " + Warehouse.ID_COLUMN + " = ?";
    String SQL_DELETE = "DELETE FROM " + Warehouse.TABLE_NAME + " WHERE "  + Warehouse.ID_COLUMN + " = ?";

    List<Warehouse> findAll();

    Warehouse findById(Long id);

    void update(Warehouse warehouse);

    void delete(Warehouse warehouse);

    void insert(Warehouse warehouse);

}
