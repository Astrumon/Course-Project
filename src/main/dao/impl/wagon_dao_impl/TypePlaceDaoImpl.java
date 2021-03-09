package main.dao.impl.wagon_dao_impl;

import main.DataSource;
import main.dao.wagon_dao.TypePlaceDao;
import main.model.wagon.Place;
import main.model.wagon.TypePlace;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс TypePlaceDaoImpl служит для создания количества определенных типов мест в вагоне
 * взаимодействует с таблицей wagon
 */
public class TypePlaceDaoImpl implements TypePlaceDao {

    private DataSource dataSource;

    /**
     * Конструктор служит для установки подключения к базе данных
     *
     * @param dataSource
     */
    public TypePlaceDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Выборка всей информации из таблицы count_type_place
     *
     * @return
     */
    @Override
    public List<TypePlace> findAll() {
        Connection connection = null;
        List<TypePlace> typePlaces = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                TypePlace typePlace = new TypePlace();
                typePlace.setIdTypePlace(rs.getLong(TypePlace.ID_TYPE_PLACE_COLUMN));
                typePlace.setIdWagon(rs.getLong(TypePlace.ID_WAGON_COLUMN));
                typePlace.setCountVip(rs.getInt(TypePlace.COUNT_VIP_COLUMN));
                typePlace.setCountMiddle(rs.getInt(TypePlace.COUNT_MIDDLE_COLUMN));
                typePlace.setCountLow(rs.getInt(TypePlace.COUNT_LOW_COLUMN));
                typePlace.setCountSeats(rs.getInt(TypePlace.COUNT_SEATS_COLUMN));
                typePlaces.add(typePlace);
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
        return typePlaces;
    }

    /**
     * Выборка всей информации одной записи по заданому id из таблицы count_type_place
     *
     * @param id
     * @return
     */
    @Override
    public TypePlace findById(Long id) {
        Connection connection = null;
        TypePlace typePlace = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                typePlace = new TypePlace();
                typePlace.setIdTypePlace(rs.getLong(TypePlace.ID_TYPE_PLACE_COLUMN));
                typePlace.setIdWagon(rs.getLong(TypePlace.ID_WAGON_COLUMN));
                typePlace.setCountVip(rs.getInt(TypePlace.COUNT_VIP_COLUMN));
                typePlace.setCountMiddle(rs.getInt(TypePlace.COUNT_MIDDLE_COLUMN));
                typePlace.setCountLow(rs.getInt(TypePlace.COUNT_LOW_COLUMN));
                typePlace.setCountSeats(rs.getInt(TypePlace.COUNT_SEATS_COLUMN));
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
        return typePlace;
    }

    /**
     * Удаление записи с таблицы count_type_place по typePlace.id
     *
     * @param typePlace
     */
    @Override
    public void delete(TypePlace typePlace) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setLong(1, typePlace.getIdTypePlace());
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
    public boolean deleteByIdWagon(Long id) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID_WAGON);
            preparedStatement.setLong(1, id);
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

        return true;
    }



    /**
     * Вставка записи информации о typePlace в таблицу count_type_place.
     *
     * @param typePlace
     */
    @Override
    public Long insert(TypePlace typePlace) {
        Connection connection = null;
        Long id = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, typePlace.getCountVip());
            preparedStatement.setInt(2, typePlace.getCountMiddle());
            preparedStatement.setInt(3, typePlace.getCountLow());
            preparedStatement.setInt(4, typePlace.getCountSeats());
            preparedStatement.setLong(5, typePlace.getIdWagon());
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                id = resultSet.getLong(1);
                typePlace.setIdTypePlace(id);
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

        return id;

    }

    /**
     * Обновляет запись в таблице count_type_place информацией об объекте typePlace
     * @param typePlace
     */
    @Override
    public boolean update(TypePlace typePlace) {
        Connection connection = null;
        WagonDaoImpl wagonDao = new WagonDaoImpl(dataSource);

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);


            preparedStatement.setInt(1, typePlace.getCountVip());
            preparedStatement.setInt(2, typePlace.getCountMiddle());
            preparedStatement.setInt(3, typePlace.getCountLow());
            preparedStatement.setInt(4, typePlace.getCountSeats());
            preparedStatement.setLong(5, typePlace.getIdWagon());
            preparedStatement.execute();

            wagonDao.setCountSeats(typePlace);

            PlaceDaoImpl placeDao = new PlaceDaoImpl(dataSource);
            Place place = new Place();
            place.setIdWagon(typePlace.getIdWagon());
            placeDao.delete(place);

            typePlace.setIdTypePlace(findByIdWagon(typePlace.getIdWagon()).getIdTypePlace());
            wagonDao.createPlace(typePlace.getIdTypePlace());
            return true;
        } catch (SQLException exc) {
            System.out.println(exc);
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException exc) {
                System.out.println(exc);
            }
        }

    }

    @Override
    public TypePlace findByIdWagon(Long id) {
        Connection connection = null;
        TypePlace typePlace = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID_WAGON);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                typePlace = new TypePlace();
                typePlace.setIdTypePlace(rs.getLong(TypePlace.ID_TYPE_PLACE_COLUMN));
                typePlace.setIdWagon(rs.getLong(TypePlace.ID_WAGON_COLUMN));
                typePlace.setCountVip(rs.getInt(TypePlace.COUNT_VIP_COLUMN));
                typePlace.setCountMiddle(rs.getInt(TypePlace.COUNT_MIDDLE_COLUMN));
                typePlace.setCountLow(rs.getInt(TypePlace.COUNT_LOW_COLUMN));
                typePlace.setCountSeats(rs.getInt(TypePlace.COUNT_SEATS_COLUMN));
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
        return typePlace;
    }
}
