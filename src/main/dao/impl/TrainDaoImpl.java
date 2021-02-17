package main.dao.impl;

import main.DataSource;
import main.dao.TrainDao;
import main.model.Train;
import main.model.Wagon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainDaoImpl implements TrainDao {

    private DataSource dataSource;
    private List<Train> trains;

    public TrainDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean addWagon(Wagon wagon) {
        Connection connection = null;
        trains = findAll();

        for (Train train : trains) {

        }
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_WAGON);

        } catch (SQLException exc) {
            System.out.println(exc);
        } finally {
            try {
                connection.close();
            } catch (SQLException exc) {
                System.out.println(exc);
            }
        }
        return false;
    }
    @Override
    public List<Train> findAll() {
        Connection connection = null;
         trains = new ArrayList<Train>();

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Train train = new Train();
                train.setId(rs.getLong(Train.ID_COLUMN));
                train.setName(rs.getString(Train.NAME_COLUMN));
                train.setIdWagon(rs.getLong(Train.ID_WAGON_COLUMN));
                train.setCountWagons(rs.getInt(Train.COUNT_WAGONS_COLUMN));
                trains.add(train);
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

        return trains;
    }

    @Override
    public Train findById(Long id) {
        Connection connection = null;
        Train train = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                train = new Train();
                train.setId(rs.getLong(Train.ID_COLUMN));
                train.setName(rs.getString(Train.NAME_COLUMN));
                train.setIdWagon(rs.getLong(Train.ID_WAGON_COLUMN));
                train.setCountWagons(rs.getInt(Train.COUNT_WAGONS_COLUMN));
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
        return train;
    }

    @Override
    public void delete(Train train) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setLong(1, train.getId());
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

    @Override
    public void insert(Train train) {
        Connection connection = null;
        trains = findAll();
        //TODO занести в метод
        if (trains != null) {
            int countWagons = 0;
            for (Train value : trains) {
                if (value.getName().equals(train.getName()) && value.getPosWagon() != 0) {
                    countWagons = value.getPosWagon();
                    if (countWagons != 0) {
                        countWagons--;
                        train.setPosWagon(countWagons);
                    }
                }
            }
        }
        if (train.getPosWagon() != 0) {
            try {
                connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);


                preparedStatement.setLong(1, train.getId());
                preparedStatement.setInt(2, train.getCountWagons());
                preparedStatement.setString(3, train.getName());
                preparedStatement.execute();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                while (rs.next()) {
                    train.setId(rs.getLong(1));
                }

            } catch (SQLException exc) {
                System.out.println(exc);
            } finally {
                try {
                    connection.close();
//                System.out.println("in method insert: size trains" + trains.size());
                } catch (SQLException exc) {
                    System.out.println(exc);
                }
            }
        } else {
            showError(train.getName() + " - мест больше нет");
        }
    }

    @Override
    public void update(Train train) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setInt(1, train.getCountWagons());
            preparedStatement.setString(2, train.getName());
            preparedStatement.setLong(3, train.getIdWagon());
            preparedStatement.setLong(4, train.getId());
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

    private void showError(String text) {
        System.out.println(text);
    }
}
