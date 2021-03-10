package data_access.dao.route_dao;

import data_access.model.route.Town;

import java.util.List;

public interface TownDao {
    String SQL_FIND_ALL = "SELECT * FROM " + Town.TABLE_NAME;
    String SQL_FIND_BY_ID = SQL_FIND_ALL + " WHERE " + Town.ID_COLUMN + " = ?";
    String SQL_FIND_BY_NAME = SQL_FIND_ALL + " WHERE " + Town.NAME_COLUMN + " = ?";
    String SQL_INSERT = "INSERT INTO " + Town.TABLE_NAME + "(" + Town.NAME_COLUMN + ") VALUES(?)";
    String SQL_DELETE = "DELETE FROM " + Town.TABLE_NAME + " WHERE " + Town.NAME_COLUMN + " = ?";
    String SQL_UPDATE = "UPDATE " + Town.TABLE_NAME + " SET "
            + Town.NAME_COLUMN + " = ? WHERE " + Town.ID_COLUMN + " = ?";

    List<Town> findByAll();

    Town findById(Long id);

    Town findByName(String name);

    void delete(Town town);

    void update(Town town);

    void insert(Town town);
}
