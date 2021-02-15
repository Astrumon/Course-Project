package main.dao.impl;

import main.DataSource;
import main.dao.WagonDao;
import main.model.Wagon;
import main.model.Warehouse;

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
                wagon.setId(rs.getLong(Wagon.ID_COLUMN));
                wagon.setSeatingCount(rs.getInt(Wagon.SEATING_CAPACITY));
                wagon.setIdTrain(rs.getLong(Wagon.ID_TRAIN));
                wagon.setIdWarehouse(rs.getLong(Wagon.ID_WAREHOUSE));
                wagon.setPosTrain(rs.getInt(Wagon.POSITION_TRAIN));
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
                wagon.setId(rs.getLong(Wagon.ID_COLUMN));
                wagon.setSeatingCount(rs.getInt(Wagon.SEATING_CAPACITY));
                wagon.setIdTrain(rs.getLong(Wagon.ID_TRAIN));
                wagon.setIdWarehouse(rs.getLong(Wagon.ID_WAREHOUSE));
                wagon.setPosTrain(rs.getInt(Wagon.POSITION_TRAIN));
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
            preparedStatement.setLong(1, wagon.getId());
            preparedStatement.setLong(2, wagon.getSeatingCount());
            preparedStatement.setLong(3, wagon.getIdTrain());
            preparedStatement.setLong(4, wagon.getIdWarehouse());
            preparedStatement.setLong(5, wagon.getPosTrain());
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
            preparedStatement.setLong(2, wagon.getIdTrain());
            preparedStatement.setLong(3, wagon.getIdWarehouse());
            preparedStatement.setLong(4, wagon.getPosTrain());
            preparedStatement.setLong(5, wagon.getId());
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
