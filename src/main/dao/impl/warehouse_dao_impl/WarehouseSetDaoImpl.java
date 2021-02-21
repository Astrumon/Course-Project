package main.dao.impl.warehouse_dao_impl;

import main.DataSource;
import main.dao.impl.train_dao_impl.TrainSetDaoImpl;
import main.dao.warehouse_dao.WarehouseDao;
import main.dao.warehouse_dao.WarehouseSetDao;
import main.model.Wagon;
import main.model.train.TrainSet;
import main.model.warehouse.Warehouse;
import main.model.warehouse.WarehouseSet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WarehouseSetDaoImpl implements WarehouseSetDao {

    private static int pos = 0;

    private DataSource dataSource;

    public WarehouseSetDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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

    @Override
    public WarehouseSet findById(Long id) {
        Connection connection = null;
        WarehouseSet warehouseSet = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                warehouseSet.setNameWarehouse(resultSet.getString(WarehouseSet.NAME_COLUMN));
                warehouseSet.setIdWagon(resultSet.getLong(WarehouseSet.ID_WAGON_COLUMN));
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

    @Override
    public WarehouseSet findByName(String name) {
        Connection connection = null;
        WarehouseSet warehouseSet = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_NAME);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                warehouseSet = new WarehouseSet();
                warehouseSet.setId(resultSet.getLong(WarehouseSet.ID_COLUMN));
                warehouseSet.setIdWagon(resultSet.getLong(WarehouseSet.ID_WAGON_COLUMN));
                warehouseSet.setNameWarehouse(resultSet.getString(WarehouseSet.NAME_COLUMN));
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

    @Override
    public void update(WarehouseSet warehouseSet) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, warehouseSet.getNameWarehouse());
            preparedStatement.setLong(2, warehouseSet.getIdWagon());
            preparedStatement.setLong(3, warehouseSet.getId());
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
    public void addWagon( WarehouseSet warehouseSet, Wagon wagon) {
        pos++;
        WarehouseDaoImpl warehouseDao = new WarehouseDaoImpl(dataSource);
       int capacity =  warehouseDao.findByName(warehouseSet.getNameWarehouse()).getCapacity();

       if (pos == capacity) {
           pos = 0;
       }
        System.out.println(pos);

        Connection connection = null;
        assert wagon != null;
        if (warehouseSet.getNameWarehouse().equals(wagon.getNameWarehouse()) || wagon.getNameWarehouse() == null) {
            wagon.setNameWarehouse(warehouseSet.getNameWarehouse());
            warehouseSet.setIdWagon(wagon.getIdWagon());
            System.out.println(warehouseSet.getIdWagon());
            try {
                connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_WAGON);
                preparedStatement.setLong(1, warehouseSet.getIdWagon());
                preparedStatement.setString(2, warehouseSet.getNameWarehouse());
                System.out.println(warehouseSet.getPosition());
                preparedStatement.setLong(3, pos);
                preparedStatement.execute();

                String SQL_UPDATE_WAREHOUSE_NAME = "UPDATE " + Wagon.TABLE_NAME + " SET "
                        + Wagon.NAME_WAREHOUSE_COLUMN + " = ?  WHERE "
                        + Wagon.ID_WAGON_COLUMN + " = ?";
                PreparedStatement preparedStatementWagon = connection.prepareStatement(SQL_UPDATE_WAREHOUSE_NAME);
                preparedStatementWagon.setString(1, warehouseSet.getNameWarehouse());
                preparedStatementWagon.setLong(2, warehouseSet.getIdWagon());
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
        } else {
            System.out.println("position is taken");
        }

    }

    @Override
    public void insert(WarehouseSet warehouseSet) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, warehouseSet.getNameWarehouse());
            preparedStatement.setInt(2, warehouseSet.getPosition());
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
