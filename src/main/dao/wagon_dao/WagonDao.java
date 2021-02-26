package main.dao.wagon_dao;

import main.model.wagon.TypePlace;
import main.model.wagon.Wagon;
import main.model.train.TrainSet;
import main.model.warehouse.WarehouseSet;

import java.util.List;

public interface WagonDao {
    String SQL_FIND_ALL = "SELECT * FROM " + Wagon.TABLE_NAME;
    String SQL_FIND_BY_ID = SQL_FIND_ALL + " WHERE " + Wagon.ID_COLUMN_COLUMN + "= ?";
    String SQL_FIND_BY_ID_WAGON = SQL_FIND_ALL + " WHERE " + Wagon.ID_WAGON_COLUMN + "= ?";
    String SQL_INSERT = "INSERT INTO " + Wagon.TABLE_NAME
            + "("  + Wagon.ID_WAGON_COLUMN
            + "," + Wagon.TYPE_COLUMN
            + "," + Wagon.POSITION_TRAIN_COLUMN
            + ") VALUES(?,?,?)";
    String SQL_UPDATE = "UPDATE " + Wagon.TABLE_NAME + " SET "
            + Wagon.NAME_WAREHOUSE_COLUMN + " = ?, "
            + Wagon.POSITION_TRAIN_COLUMN + " = ?, "
            + Wagon.TYPE_COLUMN + " = ?, " + Wagon.ID_WAGON_COLUMN + " = ?, "
            + Wagon.ID_TRAIN_SET_COLUMN + " = ?, "
            + Wagon.ID_WAREHOUSE_SET_COLUMN + " = ?, "
            + Wagon.ID_COUNT_TYPE_PLACE_COLUMN + " = ? "
            +" WHERE " + Wagon.ID_COLUMN_COLUMN + " = ?";
    String SQL_UPDATE_WAREHOUSE_SET = "UPDATE " + Wagon.TABLE_NAME + " SET "
            + Wagon.ID_WAREHOUSE_SET_COLUMN + " = ?, "
            + Wagon.NAME_WAREHOUSE_COLUMN + " = ?  WHERE "
            + Wagon.ID_WAGON_COLUMN + " = ?";
    String SQL_UPDATE_TRAIN_SET = "UPDATE " + Wagon.TABLE_NAME + " SET "
            + Wagon.ID_TRAIN_SET_COLUMN + " = ?, "
            + Wagon.TRAIN_NAME_COLUMN + " = ?, "
            + Wagon.POSITION_TRAIN_COLUMN + " = ?  WHERE "
            + Wagon.ID_WAGON_COLUMN + " = ?";
    String SQL_DELETE = "DELETE FROM " + Wagon.TABLE_NAME + " WHERE " + Wagon.ID_COLUMN_COLUMN + " = ?";
    String SQL_UPDATE_ID_TYPE_PLACE = " UPDATE " + Wagon.TABLE_NAME + " SET "
            + Wagon.ID_COUNT_TYPE_PLACE_COLUMN + " = ? "
            + " WHERE " + Wagon.ID_WAGON_COLUMN + " = ?";

    List<Wagon> findAll();

    Wagon findById(Long id);

    Wagon findByIdWagon(Long id);

    void updateWarehouseSet(WarehouseSet warehouseSet, Long idWarehouseSet);

    void updateTrainSet(TrainSet trainSet, Long idTrainSet);

    void delete(Wagon wagon);

    Long insert(Wagon wagon);

    void update(Wagon wagon);

    void setTypePlace(Wagon wagon, TypePlace typePlace);

}
