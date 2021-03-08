package main.dao.wagon_dao;

import main.model.wagon.TypePlace;

import java.util.List;

public interface TypePlaceDao {
    String SQL_FIND_ALL = "SELECT * FROM " + TypePlace.TABLE_NAME;
    String SQL_FIND_BY_ID = SQL_FIND_ALL + " WHERE " + TypePlace.ID_TYPE_PLACE_COLUMN + " = ?";
    String SQL_FIND_BY_ID_WAGON = SQL_FIND_ALL + " WHERE " + TypePlace.ID_WAGON_COLUMN + " = ?";
    String SQL_INSERT = "INSERT INTO " + TypePlace.TABLE_NAME
            + "(" + TypePlace.COUNT_VIP_COLUMN
            + "," + TypePlace.COUNT_MIDDLE_COLUMN
            + "," + TypePlace.COUNT_LOW_COLUMN
            + "," + TypePlace.COUNT_SEATS_COLUMN
            + "," + TypePlace.ID_WAGON_COLUMN
            + ") VALUES(?,?,?,?,?)";
    String SQL_UPDATE = "UPDATE " + TypePlace.TABLE_NAME + " SET "
            + TypePlace.COUNT_VIP_COLUMN + " = ?, "
            + TypePlace.COUNT_MIDDLE_COLUMN + " = ?, "
            + TypePlace.COUNT_LOW_COLUMN + " = ?, "
            + TypePlace.COUNT_SEATS_COLUMN + " = ? "
            + " WHERE " + TypePlace.ID_WAGON_COLUMN + " = ?";
    String SQL_DELETE = "DELETE FROM " + TypePlace.TABLE_NAME
            + " WHERE " + TypePlace.ID_TYPE_PLACE_COLUMN + " = ?";
    String SQL_DELETE_BY_ID_WAGON = "DELETE FROM " + TypePlace.TABLE_NAME
            + " WHERE " + TypePlace.ID_WAGON_COLUMN + " = ?";

    List<TypePlace> findAll();

    TypePlace findById(Long id);

    void delete(TypePlace typePlace);

    boolean deleteByIdWagon(Long id);

    Long insert(TypePlace typePlace);

    void update(TypePlace typePlace);

    TypePlace findByIdWagon(Long id);


}
