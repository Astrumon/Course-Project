package main.dao;

import main.model.Train;
import main.model.TrainSet;

import java.util.List;

public interface TrainDao {
    String SQL_FIND_ALL = "SELECT * FROM " + Train.TABLE_NAME;
    String SQL_FIND_BY_ID = SQL_FIND_ALL + " WHERE " + Train.ID_COLUMN + "= ?";
    String SQL_INSERT = "INSERT INTO " + Train.TABLE_NAME + "(" + Train.NAME_COLUMN + ", "
            + Train.COUNT_WAGON_COLUMN
            + ") VALUES( ?, ?)";
    String SQL_UPDATE = "UPDATE " + Train.TABLE_NAME + " SET "
            + Train.NAME_COLUMN + " = ?, "
            + Train.COUNT_WAGON_COLUMN + " = ? "
            + " WHERE " + Train.ID_COLUMN + " = ?";
    String SQL_DELETE = "DELETE FROM " + Train.TABLE_NAME + " WHERE " + Train.ID_COLUMN + " = ?";

    List<Train> findAll();

    Train findById(Long id);

    void delete(Train train);

    void insert(Train train);

    void update(Train train);
}
