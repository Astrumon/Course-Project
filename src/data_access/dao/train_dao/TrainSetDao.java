package data_access.dao.train_dao;

import data_access.model.train.Train;
import data_access.model.train.TrainSet;
import data_access.model.wagon.Wagon;


import java.util.List;

public interface TrainSetDao {
    String SQL_FIND_ALL = "SELECT * FROM " + TrainSet.TABLE_NAME;
    String SQL_FIND_BY_ID = SQL_FIND_ALL + " WHERE " + TrainSet.ID_COLUMN + "= ?";
    String SQL_FIND_BY_NAME = SQL_FIND_ALL + " WHERE " + TrainSet.NAME_COLUMN + "= ?";
    String SQL_INSERT = "INSERT INTO " + TrainSet.TABLE_NAME
            + "(" + TrainSet.NAME_COLUMN + ", "
            + TrainSet.POS_WAGON_COLUMN + ", "
            + TrainSet.ID_TRAIN_COLUMN
            +") VALUES( ?,?, ?)";
    String SQL_ADD_WAGON = "UPDATE " + TrainSet.TABLE_NAME + " SET "
            + TrainSet.ID_WAGON_COLUMN +" = ? WHERE "
            + TrainSet.NAME_COLUMN + " = ? AND " + TrainSet.POS_WAGON_COLUMN + " = ?";
    String SQL_UPDATE = "UPDATE " + TrainSet.TABLE_NAME + " SET "
            + TrainSet.NAME_COLUMN + " = ?, "
            + TrainSet.ID_WAGON_COLUMN + " = ?, "
            + TrainSet.ID_TRAIN_COLUMN + " = ? "
            + " WHERE " + TrainSet.ID_COLUMN + " = ?";
    String SQL_DELETE = "DELETE FROM " + TrainSet.TABLE_NAME + " WHERE " + TrainSet.ID_COLUMN + " = ?";
    String SQL_DELETE_BY_TRAIN_NAME = "DELETE FROM " + TrainSet.TABLE_NAME + " WHERE " + TrainSet.NAME_COLUMN + " = ?";

    List<TrainSet> findAll();

    TrainSet findById(Long id);

    TrainSet findByName(String name);

    void delete(TrainSet trainSet);

    void deleteByTrainName(Train train);

    void insert(TrainSet trainSet);

    void update(TrainSet trainSet);

    boolean addWagon(String nameTrain, Wagon wagon, int position);
}
