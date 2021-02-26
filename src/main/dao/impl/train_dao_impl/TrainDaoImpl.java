package main.dao.impl.train_dao_impl;

import main.DataSource;
import main.dao.train_dao.TrainDao;
import main.model.train.Train;
import main.model.train.TrainSet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс TrainDaoImpl служит для создания позиций в поезде
 * взаимодействует с таблицами train, train_set.
 *
 */
public class TrainDaoImpl implements TrainDao {
    private DataSource dataSource;

    /**
     * Конструктор служит для установки подключения к базе данных
     * @param dataSource
     */
    public TrainDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    /**
     * Выборка всей информации из таблицы train
     * @return
     */
    @Override
    public List<Train> findAll() {
        Connection connection = null;
        List<Train> trains = new ArrayList<Train>();

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Train train = new Train();
                train.setId(rs.getLong(Train.ID_COLUMN));
                train.setName(rs.getString(Train.NAME_COLUMN));
                train.setCount_wagon(rs.getInt(Train.COUNT_WAGON_COLUMN));
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

    /**
     * Выборка всей информации одной записи по заданому id из таблицы train
     * @param id
     * @return
     */
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
                train.setCount_wagon(rs.getInt(Train.COUNT_WAGON_COLUMN));
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

    /**
     * Удаление записи с таблицы train по train.id
     * @param train
     */
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

    /**
     * Удаление записи с таблицы train по train.name
     * @param train
     */
    @Override
    public void deleteByName(Train train) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_NAME);
            preparedStatement.setString(1, train.getName());
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


    /**
     * Создания в таблице train_set записей с информацией про состав заданого поезда
     * @param train
     */
    private void createTrainSetPosition(Train train) {
        TrainSetDaoImpl trainSetDao = new TrainSetDaoImpl(dataSource);
        for (int i = 1; i <= train.getCount_wagon(); i++) {
            trainSetDao.insert(new TrainSet(train.getName(), i, train.getId()));
        }
    }

    /**
     * Вставка записи информации про поезд в таблицу train.
     * вызов функции createTrainSetPosition(Train train)
     * @param train
     */
    @Override
    public void insert(Train train) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, train.getName());
            preparedStatement.setInt(2, train.getCount_wagon());
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();


            while (rs.next()) {
                train.setId(rs.getLong(1));
            }

            createTrainSetPosition(train);
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

    /**
     * Обновляет запись в таблице train информацией об объекте train
     * @param train
     */
    @Override
    public void update(Train train) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);

            preparedStatement.setString(1, train.getName());
            preparedStatement.setInt(2, train.getCount_wagon());
            preparedStatement.setLong(3, train.getId());
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
