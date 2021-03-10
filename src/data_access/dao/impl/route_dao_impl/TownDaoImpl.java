package data_access.dao.impl.route_dao_impl;

import data_access.DataSource;
import data_access.dao.route_dao.TownDao;
import data_access.model.route.Town;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Данный класс служит для создания/удаления/обновление городов
 */
public class TownDaoImpl implements TownDao {
    private DataSource dataSource;

    /**
     * Конструктор служит для установки подключения к базе данных
     *
     * @param dataSource
     */
    public TownDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Выборка всей информации из таблицы town
     *
     * @return
     */
    @Override
    public List<Town> findByAll() {
        Connection connection = null;
        List<Town> towns = new ArrayList<Town>();
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Town town = new Town();
                town.setId(resultSet.getLong(Town.ID_COLUMN));
                town.setName(resultSet.getString(Town.NAME_COLUMN));

                towns.add(town);
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
        return towns;
    }

    /**
     * Выборка всей информации одной записи по заданому id из таблицы town
     *
     * @param id
     * @return
     */
    @Override
    public Town findById(Long id) {
        Connection connection = null;
        Town town = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                town = new Town();
                town.setId(resultSet.getLong(Town.ID_COLUMN));
                town.setName(resultSet.getString(Town.NAME_COLUMN));

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
        return town;
    }

    /**
     * Выборка всей информации одной записи по заданому name из таблицы town
     *
     * @param name
     * @return
     */
    @Override
    public Town findByName(String name) {
        Connection connection = null;
        Town town = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                town = new Town();
                town.setId(resultSet.getLong(Town.ID_COLUMN));
                town.setName(resultSet.getString(Town.NAME_COLUMN));
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
        return town;
    }

    /**
     * Удаление записи с таблицы town по town.name
     *
     * @param town
     */
    @Override
    public void delete(Town town) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);

            preparedStatement.setString(1, town.getName());
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
     * Обновляет запись в таблице town информацией об объекте town
     *
     * @param town
     */
    @Override
    public void update(Town town) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setLong(1, town.getId());
            preparedStatement.setString(2, town.getName());
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
     * Добавление нового города в таблицу town.
     *
     * @param town
     */
    @Override
    public void insert(Town town) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, town.getName());
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                town.setId(resultSet.getLong(1));
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
}
