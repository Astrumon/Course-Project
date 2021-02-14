package main.dao;

import main.model.Wagon;

import java.util.List;

public interface WagonDao {
    String SQL_FIND_ALL = "SELECT * FROM " + Wagon.TABLE_NAME;
    String SQL_FIND_BY_ID = SQL_FIND_ALL + " WHERE " + Wagon.ID_COLUMN + "= ?";
    String SQL_INSERT = "INSERT INTO " + Wagon.TABLE_NAME + "("+ Wagon.ID_COLUMN + "," + Wagon.SEATING_CAPACITY + ") VALUES(?,?)";
    String SQL_UPDATE = "UPDATE " + Wagon.TABLE_NAME + " SET " + Wagon.SEATING_CAPACITY + " = ? WHERE " + Wagon.ID_COLUMN + " = ?";
    String SQL_DELETE = "DELETE FROM " + Wagon.TABLE_NAME + " WHERE " + Wagon.ID_COLUMN + " = ?";

    List<Wagon> findAll();

    Wagon findById(Long id);

    void delete(Wagon wagon);

    void insert(Wagon wagon);

    void update(Wagon wagon);
}
