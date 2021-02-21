package main.dao.warehouse_dao;

import main.model.warehouse.Warehouse;

import java.util.List;

public interface WarehouseDao {
    String SQL_FIND_ALL = "SELECT * FROM " + Warehouse.TABLE_NAME;
    String SQL_FIND_BY_ID = SQL_FIND_ALL + " WHERE " + Warehouse.ID_COLUMN + " = ?";
    String SQL_FIND_BY_NAME = SQL_FIND_ALL + " WHERE " + Warehouse.NAME_WAREHOUSE_COLUMN + " = ?";
    String SQL_INSERT = "INSERT INTO " + Warehouse.TABLE_NAME + "("  + Warehouse.NAME_WAREHOUSE_COLUMN +","+ Warehouse.CAPACITY_COLUMN + ") VALUES ( ?, ?)";
    String SQL_UPDATE = "UPDATE " + Warehouse.TABLE_NAME + " SET "
            + Warehouse.CAPACITY_COLUMN + "= ?,"
            + Warehouse.NAME_WAREHOUSE_COLUMN + "= ? "
            + " WHERE " + Warehouse.ID_COLUMN + " = ?";
    String SQL_DELETE = "DELETE FROM " + Warehouse.TABLE_NAME + " WHERE "  + Warehouse.ID_COLUMN + " = ?";
    String SQL_DELETE_BY_NAME = "DELETE FROM " + Warehouse.TABLE_NAME + " WHERE "  + Warehouse.NAME_WAREHOUSE_COLUMN + " = ?";

    List<Warehouse> findAll();

    Warehouse findById(Long id);
    Warehouse findByName(String name);

    void update(Warehouse warehouse);

    void delete(Warehouse warehouse);

    void deleteByName(Warehouse warehouse);

    void insert(Warehouse warehouse);

}
