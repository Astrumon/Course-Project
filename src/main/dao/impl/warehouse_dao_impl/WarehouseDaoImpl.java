package main.dao.impl.warehouse_dao_impl;

import main.DataSource;
import main.dao.warehouse_dao.WarehouseDao;
import main.model.wagon.Wagon;
import main.model.warehouse.Warehouse;
import main.model.warehouse.WarehouseSet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс WarehouseDaoImpl служит для создания наименований складов
 * взаимодействует с таблицами warehouse, warehouse_set.
 *
 */
public class WarehouseDaoImpl implements WarehouseDao {
    private DataSource dataSource;

    /**
     * Конструктор служит для установки подключения к базе данных
     * @param dataSource
     */
    public WarehouseDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Выборка всей информации из таблицы warehouse
     * @return
     */
    @Override
    public List<Warehouse> findAll() {
        Connection connection = null;
        List<Warehouse> warehouses = new ArrayList<Warehouse>();

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Warehouse warehouse = new Warehouse();
                warehouse.setId(rs.getLong(Wagon.ID_COLUMN));
                warehouse.setCapacity(rs.getInt(Warehouse.CAPACITY_COLUMN));
                warehouse.setName(rs.getString(Warehouse.NAME_WAREHOUSE_COLUMN));
                warehouse.setCountWagons(rs.getInt(Warehouse.COUNT_WAGONS_COLUMN));
                warehouses.add(warehouse);
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

        return warehouses;
    }

    /**
     * Выборка всей информации одной записи по заданому id из таблицы warehouse
     * @param id
     * @return
     */
    @Override
    public Warehouse findById(Long id) {
        Connection connection = null;
        Warehouse warehouse = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                warehouse = new Warehouse();
                warehouse.setId(rs.getLong(Warehouse.ID_COLUMN));
                warehouse.setCapacity(rs.getInt(Warehouse.CAPACITY_COLUMN));
                warehouse.setName(rs.getString(Warehouse.NAME_WAREHOUSE_COLUMN));
                warehouse.setCountWagons(rs.getInt(Warehouse.COUNT_WAGONS_COLUMN));
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
        return warehouse;
    }

    /**
     * Выборка всей информации одной записи по заданому name из таблицы warehouse
     * @param name
     * @return
     */
    @Override
    public Warehouse findByName(String name) {
        Connection connection = null;
        Warehouse warehouse = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                warehouse = new Warehouse();
                warehouse.setId(rs.getLong(Warehouse.ID_COLUMN));
                warehouse.setCapacity(rs.getInt(Warehouse.CAPACITY_COLUMN));
                warehouse.setName(rs.getString(Warehouse.NAME_WAREHOUSE_COLUMN));
                warehouse.setCountWagons(rs.getInt(Warehouse.COUNT_WAGONS_COLUMN));
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
        return warehouse;
    }

    /**
     * Обновляет запись в таблице train информацией об объекте warehouse
     * @param warehouse
     */
    @Override
    public void update(Warehouse warehouse) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setInt(1, warehouse.getCapacity());
            preparedStatement.setInt(2, warehouse.getCountWagons());
            preparedStatement.setString(3, warehouse.getName());
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
     * Удаление записи с таблицы warehouse по warehouse.id
     * @param warehouse
     */
    @Override
    public void delete(Warehouse warehouse) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setLong(1, warehouse.getId());
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
     * Создания в таблице warehouse_set записей с информацией про состав заданого склада
     * @param warehouse
     */
    private void createWarehousePositions(Warehouse warehouse) {
        WarehouseSetDaoImpl warehouseSetDao = new WarehouseSetDaoImpl(dataSource);
        for (int i = 1; i <= warehouse.getCapacity(); i++) {
            warehouseSetDao.insert(new WarehouseSet(warehouse.getName(), i, warehouse.getId()));
        }
    }

    /**
     * Вставка записи информации про склад в таблицу warehouse.
     * вызов функции createTrainSetPosition(Warehouse warehouse)
     * @param warehouse
     */
    @Override
    public void insert(Warehouse warehouse) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, warehouse.getName());
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                warehouse.setId(rs.getLong(1));
            }
            createWarehousePositions(warehouse);
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
     * Удаление записи с таблицы warehouse по warehouse.name
     * @param warehouse
     */
    @Override
    public void deleteByName(Warehouse warehouse) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_NAME);
            preparedStatement.setString(1, warehouse.getName());
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
    public void updateCountWagon(Warehouse warehouse) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_COUNT_WAGON);
            preparedStatement.setInt(1, warehouse.getCountWagons());
            preparedStatement.setString(2, warehouse.getName());
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
