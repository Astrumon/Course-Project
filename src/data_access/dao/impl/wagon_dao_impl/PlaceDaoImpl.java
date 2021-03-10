package data_access.dao.impl.wagon_dao_impl;

import data_access.DataSource;
import data_access.dao.wagon_dao.PlaceDao;
import data_access.model.wagon.Place;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс PlaceDaoImpl служит для создания мест в вагоне
 * взаимодействует с таблицей wagon, count_type_place
 */
public class PlaceDaoImpl implements PlaceDao {

    private DataSource dataSource;

    /**
     * Конструктор служит для установки подключения к базе данных
     *
     * @param dataSource
     */
    public PlaceDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Выборка всей информации из таблицы place
     *
     * @return
     */
    @Override
    public List<Place> findAll() {
        Connection connection = null;
        List<Place> places = new ArrayList<Place>();
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Place place = new Place();
                place.setIdPlace(resultSet.getLong(Place.ID_PLACE_COLUMN));
                place.setIdWagon(resultSet.getLong(Place.ID_WAGON_COLUMN));
                place.setNumber(resultSet.getInt(Place.NUMBER_COLUMN));
                place.setType(resultSet.getInt(Place.TYPE_COLUMN));
                place.setStatus(resultSet.getInt(Place.STATUS_COLUMN));
                place.setIdCountType(resultSet.getLong(Place.ID_COUNT_TYPE_COLUMN));
                places.add(place);
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
        return places;
    }

    /**
     * Выборка всей информации одной записи по заданому id из таблицы cplace
     *
     * @param id
     * @return
     */
    @Override
    public Place findById(Long id) {
        Connection connection = null;
        Place place = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                place = new Place();
                place.setIdPlace(resultSet.getLong(Place.ID_PLACE_COLUMN));
                place.setIdWagon(resultSet.getLong(Place.ID_WAGON_COLUMN));
                place.setNumber(resultSet.getInt(Place.NUMBER_COLUMN));
                place.setType(resultSet.getInt(Place.TYPE_COLUMN));
                place.setStatus(resultSet.getInt(Place.STATUS_COLUMN));
                place.setIdCountType(resultSet.getLong(Place.ID_COUNT_TYPE_COLUMN));
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
        return place;
    }

    /**
     * Удаление записи с таблицы place по place.id
     *
     * @param place
     */
    @Override
    public void delete(Place place) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setLong(1, place.getIdWagon());
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
     * Обновляет запись в таблице place информацией об объекте place
     *
     * @param place
     */
    @Override
    public void update(Place place) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);

            preparedStatement.setLong(1, place.getIdWagon());
            preparedStatement.setInt(2, place.getNumber());
            preparedStatement.setInt(3, place.getType());
            preparedStatement.setInt(4, place.getStatus());
            preparedStatement.setLong(5, place.getIdPlace());
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
     * Вставка записи информации о place в таблицу place.
     *
     * @param place
     */
    @Override
    public void insert(Place place) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, place.getIdWagon());
            preparedStatement.setInt(2, place.getNumber());
            preparedStatement.setInt(3, place.getType());
            preparedStatement.setLong(4, place.getIdCountType());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                place.setIdPlace(resultSet.getLong(1));
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

    private boolean checkStatus(int status) {
        return !(status < 0 || status > 1);
    }

    private boolean checkNumber(int number) {
        return (number > 0);
    }

    /**
     * Обновляет статус места в вагоне (0 - свободно, 1 - занято)
     * @param number
     * @param status
     */
    @Override
    public void updateStatus(int number, int status) {
        Connection connection = null;
        if (checkStatus(status) && checkNumber(number)) {

            try {
                connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_STATUS);
                preparedStatement.setLong(1, status);
                preparedStatement.setLong(2, number);
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
        } else {
            System.out.println("invalid data: number = " + number + " status = " + status);
        }
    }
}
