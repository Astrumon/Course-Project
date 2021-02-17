package main.dao;

import main.model.TrainSet;
import main.model.Wagon;


import java.util.List;

public interface TrainSetDao {
    String SQL_FIND_ALL = "SELECT * FROM " + TrainSet.TABLE_NAME;
    String SQL_FIND_BY_ID = SQL_FIND_ALL + " WHERE " + TrainSet.ID_COLUMN + "= ?";
    String SQL_INSERT = "INSERT INTO " + TrainSet.TABLE_NAME + "("+ TrainSet.ID_COLUMN  + ", " + TrainSet.NAME_COLUMN
            + ") VALUES(?, ?, ?)";
    String SQL_INSERT_WAGON = "INSERT INTO " + TrainSet.TABLE_NAME + "(" + TrainSet.ID_WAGON_COLUMN +") VALUES(?)";
    String SQL_UPDATE = "UPDATE " + TrainSet.TABLE_NAME + " SET "
            + TrainSet.NAME_COLUMN + " = ?, "
            + TrainSet.ID_WAGON_COLUMN + " = ? "
            + " WHERE " + TrainSet.ID_COLUMN + " = ?";
    String SQL_DELETE = "DELETE FROM " + TrainSet.TABLE_NAME + " WHERE " + TrainSet.ID_COLUMN + " = ?";

    List<TrainSet> findAll();

    TrainSet findById(Long id);

    void delete(TrainSet trainSet);

    void insert(TrainSet trainSet);

    void update(TrainSet trainSet);

    boolean addWagon(Wagon wagon);
}
