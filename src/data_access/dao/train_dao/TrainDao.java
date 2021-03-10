package data_access.dao.train_dao;

import data_access.model.train.Train;
import data_access.model.train.TrainSet;
import data_access.model.wagon.Wagon;

import java.util.List;

public interface TrainDao {
    String SQL_FIND_ALL = "SELECT * FROM " + Train.TABLE_NAME;
    String SQL_FIND_BY_ID = SQL_FIND_ALL + " WHERE " + Train.ID_COLUMN + "= ?";
    String SQL_INSERT = "INSERT INTO " + Train.TABLE_NAME + "("+ Train.NAME_COLUMN + ", "
            + Train.CAPACITY_COLUMN
            + ") VALUES( ?, ?)";
    String SQL_UPDATE = "UPDATE " + Train.TABLE_NAME + " SET "
            + Train.NAME_COLUMN + " = ?, "
            +Train.CAPACITY_COLUMN + " = ?, "
            + Train.COUNT_WAGON_COLUMN + " = ? "
            + " WHERE " + Train.NAME_COLUMN + " = ?";
    String SQL_UPDATE_COUNT_WAGON = "UPDATE " + Train.TABLE_NAME + " SET "
            + Train.COUNT_WAGON_COLUMN + " = ? WHERE "
            + Train.NAME_COLUMN + " = ?";
    String SQL_UPDATE_TRAIN_SET = "UPDATE " + Wagon.TABLE_NAME + " SET "
            + Wagon.ID_TRAIN_SET_COLUMN + " = ?, "
            + Wagon.TRAIN_NAME_COLUMN + " = ?  WHERE "
            + Wagon.ID_WAGON_COLUMN + " = ?";
    String SQL_DELETE = "DELETE FROM " + Train.TABLE_NAME + " WHERE " + Train.ID_COLUMN + " = ?";
    String SQL_DELETE_BY_NAME = "DELETE FROM " + Train.TABLE_NAME + " WHERE " + Train.NAME_COLUMN + " = ?";

    List<Train> findAll();

    Train findById(Long id);

    boolean delete(Train train);

    boolean deleteByName(Train train);

    boolean insert(Train train);

    boolean update(Train train);

    void updateCountWagon(Train train);

    void updateTrainSet(TrainSet trainSet, Long idTrainSet);
}
