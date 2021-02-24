package main.dao.impl.warehouse_dao_impl;

import main.DataSource;
import main.dao.warehouse_dao.WarehouseDao;
import main.model.Wagon;
import main.model.warehouse.Warehouse;
import main.model.warehouse.WarehouseSet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDaoImpl implements WarehouseDao {
    private DataSource dataSource;

    public WarehouseDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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
                warehouse.setId(rs.getLong(Wagon.ID_COLUMN_COLUMN));
                warehouse.setCapacity(rs.getInt(Warehouse.CAPACITY_COLUMN));
                warehouse.setName(rs.getString(Warehouse.NAME_WAREHOUSE_COLUMN));
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

    @Override
    public void update(Warehouse warehouse) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setInt(1, warehouse.getCapacity());
            preparedStatement.setString(2, warehouse.getName());
            preparedStatement.setLong(3, warehouse.getId());
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

    private void createWarehousePositions(Warehouse warehouse) {
        WarehouseSetDaoImpl warehouseSetDao = new WarehouseSetDaoImpl(dataSource);
        for (int i = 1; i <= warehouse.getCapacity(); i++) {
            warehouseSetDao.insert(new WarehouseSet(warehouse.getName(), i, warehouse.getId()));
        }
    }

    @Override
    public void insert(Warehouse warehouse) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, warehouse.getName());
            preparedStatement.setInt(2, warehouse.getCapacity());
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

    @Override
    public void deleteByName(Warehouse warehouse) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_NAME);
            preparedStatement.setString(1, warehouse.getName());
            preparedStatement.execute();

            //deleteWarehouseSet(warehouse);

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

    private void deleteWarehouseSet(Warehouse warehouse) {
        WarehouseSetDaoImpl warehouseSetDao = new WarehouseSetDaoImpl(dataSource);
        warehouseSetDao.deleteByWarehouseName(warehouse);
    }
}
