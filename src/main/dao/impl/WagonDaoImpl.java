package main.dao.impl;

import main.DataSource;
import main.dao.WagonDao;
import main.dao.warehouse_dao.WarehouseSetDao;
import main.model.Wagon;
import main.model.warehouse.WarehouseSet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WagonDaoImpl implements WagonDao {
    private DataSource dataSource;

    public WagonDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Wagon> findAll() {
        Connection connection = null;
        List<Wagon> wagons = new ArrayList<Wagon>();

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Wagon wagon = new Wagon();
                wagon.setId(rs.getLong(Wagon.ID_COLUMN_COLUMN));
                wagon.setSeatingCount(rs.getInt(Wagon.SEATING_CAPACITY_COLUMN));
                wagon.setIdWagon(rs.getLong(Wagon.ID_WAGON_COLUMN));
                wagon.setNameWarehouse(rs.getString(Wagon.NAME_WAREHOUSE_COLUMN));
                wagon.setTrainName(rs.getString(Wagon.TRAIN_NAME_COLUMN));
                wagon.setPosTrain(rs.getInt(Wagon.POSITION_TRAIN_COLUMN));
                wagon.setType(rs.getInt(Wagon.TYPE_COLUMN));
                wagon.setIdTrainSet(rs.getLong(Wagon.ID_TRAIN_SET_COLUMN));
                wagon.setIdWarehouseSet(rs.getLong(Wagon.ID_WAREHOUSE_SET_COLUMN));
                wagons.add(wagon);
            }
        } catch (SQLException exc) {
            System.out.println(exc);
        } finally {
            try {
                connection.close();
            } catch (SQLException exc) {
                System.out.println(exc);
            }
        }

        return wagons;
    }

    @Override
    public Wagon findById(Long id) {
        Connection connection = null;
        Wagon wagon = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                wagon = new Wagon();
                wagon.setId(rs.getLong(Wagon.ID_COLUMN_COLUMN));
                wagon.setSeatingCount(rs.getInt(Wagon.SEATING_CAPACITY_COLUMN));
                wagon.setIdWagon(rs.getLong(Wagon.ID_WAGON_COLUMN));
                wagon.setNameWarehouse(rs.getString(Wagon.NAME_WAREHOUSE_COLUMN));
                wagon.setPosTrain(rs.getInt(Wagon.POSITION_TRAIN_COLUMN));
                wagon.setIdTrainSet(rs.getLong(Wagon.ID_TRAIN_SET_COLUMN));
                wagon.setIdWarehouseSet(rs.getLong(Wagon.ID_WAREHOUSE_SET_COLUMN));
                wagon.setType(rs.getInt(Wagon.TYPE_COLUMN));
            }
        } catch (SQLException exc) {
            System.out.println(exc);
        } finally {
            try {
                connection.close();
            } catch (SQLException exc) {
                System.out.println(exc);
            }
        }
        return wagon;
    }

    @Override
    public Wagon findByIdWagon(Long id) {
        Connection connection = null;
        Wagon wagon = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID_WAGON);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                wagon = new Wagon();
                wagon.setId(rs.getLong(Wagon.ID_COLUMN_COLUMN));
                wagon.setSeatingCount(rs.getInt(Wagon.SEATING_CAPACITY_COLUMN));
                wagon.setIdWagon(rs.getLong(Wagon.ID_WAGON_COLUMN));
                wagon.setTrainName(rs.getString(Wagon.TRAIN_NAME_COLUMN));
                wagon.setNameWarehouse(rs.getString(Wagon.NAME_WAREHOUSE_COLUMN));
                wagon.setPosTrain(rs.getInt(Wagon.POSITION_TRAIN_COLUMN));
                wagon.setIdTrainSet(rs.getLong(Wagon.ID_TRAIN_SET_COLUMN));
                wagon.setIdWarehouseSet(rs.getLong(Wagon.ID_WAREHOUSE_SET_COLUMN));
                wagon.setType(rs.getInt(Wagon.TYPE_COLUMN));
            }
        } catch (SQLException exc) {
            System.out.println(exc);
        } finally {
            try {
                connection.close();
            } catch (SQLException exc) {
                System.out.println(exc);
            }
        }
        return wagon;
    }






    @Override
    public void updateWarehouseSet(WarehouseSet warehouseSet, Long idWarehouseSet) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementWagon = connection.prepareStatement(SQL_UPDATE_WAREHOUSE_SET);
            preparedStatementWagon.setLong(1, idWarehouseSet);
            preparedStatementWagon.setString(2, warehouseSet.getNameWarehouse());
            preparedStatementWagon.setLong(3, warehouseSet.getIdWagon());
            preparedStatementWagon.execute();
        } catch (SQLException exc) {
            System.out.println(exc);
        } finally {
            try {
                connection.close();
            } catch (SQLException exc) {
                System.out.println(exc);
            }
        }
    }

    @Override
    public void delete(Wagon wagon) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);

            preparedStatement.setLong(1, wagon.getId());
            preparedStatement.execute();

//            preparedStatement = connection.prepareStatement("DELETE FROM "+ Warehouse.TABLE_NAME+ " WHERE " + Warehouse.WAGON_COLUMN + " = ?");
//            preparedStatement.setLong(1, wagon.getId());
//            preparedStatement.execute();
        } catch (SQLException exc) {
            System.out.println(exc);
        } finally {
            try {
                connection.close();
            } catch (SQLException exc) {
                System.out.println(exc);
            }
        }

    }

    @Override
    public void insert(Wagon wagon) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, wagon.getSeatingCount());
            preparedStatement.setLong(2, wagon.getIdWagon());
            preparedStatement.setInt(3, wagon.getType());
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                wagon.setId(rs.getLong(1));
            }

        } catch (SQLException exc) {
            System.out.println(exc);
        } finally {
            try {
                connection.close();
            } catch (SQLException exc) {
                System.out.println(exc);
            }
        }

    }

    @Override
    public void update(Wagon wagon) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setLong(1, wagon.getSeatingCount());
            preparedStatement.setString(2, wagon.getNameWarehouse());
            preparedStatement.setLong(3, wagon.getPosTrain());
            preparedStatement.setLong(4, wagon.getIdWagon());
            preparedStatement.setInt(5, wagon.getType());
            preparedStatement.setLong(6, wagon.getIdTrainSet());
            preparedStatement.setLong(7, wagon.getIdWarehouseSet());
            preparedStatement.setLong(8, wagon.getId());
            preparedStatement.execute();

        } catch (SQLException exc) {
            System.out.println(exc);
        } finally {
            try {
                connection.close();
            } catch (SQLException exc) {
                System.out.println(exc);
            }
        }

    }
}
