package data_access.dao.impl.warehouse_dao_impl;

import data_access.DataSource;
import data_access.dao.impl.wagon_dao_impl.WagonDaoImpl;
import data_access.dao.warehouse_dao.WarehouseSetDao;
import data_access.model.wagon.Wagon;
import data_access.model.warehouse.Warehouse;
import data_access.model.warehouse.WarehouseSet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс WarehouseSetDaoImpl служит для создания позиций для вагонов определенного склада
 * Взаимодействует с таблицами warehouse, warehouse_set, wagon
 */
public class WarehouseSetDaoImpl implements WarehouseSetDao {

    private DataSource dataSource;

    /**
     * Конструктор для подключения к базе данных
     *
     * @param dataSource
     */
    public WarehouseSetDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Выборка всей информации из таблицы warehouse_set
     *
     * @return
     */
    @Override
    public List<WarehouseSet> findAll() {
        Connection connection = null;
        List<WarehouseSet> warehouseSets = new ArrayList<WarehouseSet>();
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                WarehouseSet warehouseSet = new WarehouseSet();
                warehouseSet.setId(resultSet.getLong(WarehouseSet.ID_COLUMN));
                warehouseSet.setNameWarehouse(resultSet.getString(WarehouseSet.NAME_COLUMN));
                warehouseSet.setIdWagon(resultSet.getLong(WarehouseSet.ID_WAGON_COLUMN));
                warehouseSet.setIdWarehouse(resultSet.getLong(WarehouseSet.ID_WAREHOUES_COLUMN));
                warehouseSet.setPosition(resultSet.getInt(WarehouseSet.POSITION_COLUMN));
                warehouseSets.add(warehouseSet);
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
        return warehouseSets;
    }

    /**
     * Выборка всей информации одной записи по заданому id из таблицы warehouse_set
     *
     * @param id
     * @return
     */
    @Override
    public WarehouseSet findById(Long id) {
        Connection connection = null;
        WarehouseSet warehouseSet = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                warehouseSet.setNameWarehouse(resultSet.getString(WarehouseSet.NAME_COLUMN));
                warehouseSet.setIdWagon(resultSet.getLong(WarehouseSet.ID_WAGON_COLUMN));
                warehouseSet.setIdWarehouse(resultSet.getLong(WarehouseSet.ID_WAREHOUES_COLUMN));
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
        return warehouseSet;
    }

    /**
     * Выборка всей информации одной записи по заданому названию склада из таблицы warehouse_set
     *
     * @param name
     * @return
     */
    @Override
    public WarehouseSet findByName(String name) {
        Connection connection = null;
        WarehouseSet warehouseSet = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_NAME);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                warehouseSet = new WarehouseSet();
                warehouseSet.setId(resultSet.getLong(WarehouseSet.ID_COLUMN));
                warehouseSet.setIdWagon(resultSet.getLong(WarehouseSet.ID_WAGON_COLUMN));
                warehouseSet.setNameWarehouse(resultSet.getString(WarehouseSet.NAME_COLUMN));
                warehouseSet.setIdWarehouse(resultSet.getLong(WarehouseSet.ID_WAREHOUES_COLUMN));
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
        return warehouseSet;
    }

    /**
     * Удаление записи с таблицы warehouseSet по warehouseSet.id
     *
     * @param warehouseSet
     */
    @Override
    public void delete(WarehouseSet warehouseSet) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setLong(1, warehouseSet.getId());
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
     * Удаление записи с таблицы warehouse_set по warehouse.name
     *
     * @param warehouse
     */
    @Override
    public void deleteByWarehouseName(Warehouse warehouse) {
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

    /**
     * Обновляет запись в таблице warehouse_set информацией об объекте warehouseSet
     *
     * @param warehouseSet
     */
    @Override
    public void update(WarehouseSet warehouseSet) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, warehouseSet.getNameWarehouse());
            preparedStatement.setLong(2, warehouseSet.getIdWagon());
            preparedStatement.setLong(3, warehouseSet.getIdWarehouse());
            preparedStatement.setLong(4, warehouseSet.getId());
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
     * Метод для проверки названия склада в котором находится вагон
     *
     * @param nameWarehouse
     * @return
     */
    private boolean isEmptyWarehouseName(String nameWarehouse) {
        return nameWarehouse == null;
    }

    /**
     * Метод для проверки позиции вагона на складе
     *
     * @param warehouseSet
     * @param position
     * @return
     */
    private boolean samePosition(WarehouseSet warehouseSet, int position) {
        return warehouseSet.getPosition() == position;
    }


    private void counterWagons(String warehouseName) {

        WarehouseSetDaoImpl warehouseSetDao = new WarehouseSetDaoImpl(dataSource);

        int count = 0;

        for (WarehouseSet warehouseSet : warehouseSetDao.findAll()) {
            if (warehouseSet.getIdWagon() != 0 && warehouseSet.getNameWarehouse().equals(warehouseName)) {
                count++;
            }
        }

        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehouseName);
        warehouse.setCountWagons(count);

        WarehouseDaoImpl warehouseDao = new WarehouseDaoImpl(dataSource);
        warehouseDao.updateCountWagon(warehouse);
    }

    private WarehouseSet getFilledWarehouseSet(Wagon wagon, int position) {

        WarehouseSetDaoImpl warehouseSetDao = new WarehouseSetDaoImpl(dataSource);
        WarehouseSet wSet = new WarehouseSet();
        for (WarehouseSet warehouseSet : warehouseSetDao.findAll()) {

            if (warehouseSet.getNameWarehouse().equals(wagon.getNameWarehouse()) && samePosition(warehouseSet, position) && warehouseSet.getIdWagon() == 0) {
                wSet = warehouseSet;
                wSet.setIdWagon(wagon.getIdWagon());
                wSet.setPosition(position);
                break;
            }
        }

        return wSet;
    }

    private void updateWagonWarehouseSetInfo(WarehouseSet warehouseSet) {
        WagonDaoImpl wagonDao = new WagonDaoImpl(dataSource);
        wagonDao.updateWarehouseSet(warehouseSet, warehouseSet.getId());
    }
    /**
     * Метод который служит для добавления информации о вагоне в таблицу warehouse_set
     * Информация о складе так же записывается в таблицу wagon
     *
     * @param warehouseName
     * @param wagon
     * @param position
     * @return
     */
    @Override
    public boolean addWagon(String warehouseName, Wagon wagon, int position) {

        Connection connection = null;

        String warehouseNameOfWagon = wagon.getNameWarehouse();

        wagon.setNameWarehouse(warehouseName);
        WarehouseSet warehouseSet = getFilledWarehouseSet(wagon, position);

        if (isEmptyWarehouseName(warehouseNameOfWagon) && warehouseSet.getIdWarehouse() != null) {
            try {
                connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_WAGON);
                preparedStatement.setLong(1, warehouseSet.getIdWagon());
                preparedStatement.setString(2, warehouseSet.getNameWarehouse());
                preparedStatement.setLong(3, warehouseSet.getPosition());
                preparedStatement.execute();

                updateWagonWarehouseSetInfo(warehouseSet);
                counterWagons(warehouseName);
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
        } else {
            System.out.println("position is taken");
            return false;
        }
    }

    /**
     * Вставка записи информации про поезд в таблицу warehouse_set.
     *
     * @param warehouseSet
     */
    @Override
    public void insert(WarehouseSet warehouseSet) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, warehouseSet.getNameWarehouse());
            preparedStatement.setInt(2, warehouseSet.getPosition());
            preparedStatement.setLong(3, warehouseSet.getIdWarehouse());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                warehouseSet.setId(resultSet.getLong(1));
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
