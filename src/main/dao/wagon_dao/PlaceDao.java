package main.dao.wagon_dao;

import main.model.wagon.Place;

import java.util.List;

public interface PlaceDao {
    String SQL_FIND_ALL = "SELECT * FROM " + Place.TABLE_NAME;
    String SQL_FIND_BY_ID = SQL_FIND_ALL + " WHERE " + Place.ID_PLACE_COLUMN;
    String SQL_INSERT = "INSERT INTO " + Place.TABLE_NAME
            + "(" + Place.ID_WAGON_COLUMN
            + "," + Place.NUMBER_COLUMN
            + "," + Place.TYPE_COLUMN
            + "," + Place.ID_COUNT_TYPE_COLUMN
            + ") VALUES(?,?,?,?) ";
    String SQL_UPDATE = "UPDATE " + Place.TABLE_NAME + " SET "
            + Place.ID_WAGON_COLUMN + " = ?, "
            + Place.NUMBER_COLUMN + " = ?, "
            + Place.TYPE_COLUMN + " ?, "
            + Place.STATUS_COLUMN + " ? "
            + " WHERE " + Place.ID_PLACE_COLUMN + " = ?";
    String SQL_UPDATE_STATUS = "UPDATE " + Place.TABLE_NAME + " SET "
            + Place.STATUS_COLUMN + " = ? "
            + " WHERE " + Place.ID_PLACE_COLUMN + " = ?";
    String SQL_DELETE = "DELETE FROM " + Place.TABLE_NAME
            + " WHERE " + Place.ID_WAGON_COLUMN + " = ?";

    List<Place> findAll();

    Place findById(Long id);

    void delete(Place place);

    void update(Place place);

    void insert(Place place);

    void updateStatus(int number, int status);


}
