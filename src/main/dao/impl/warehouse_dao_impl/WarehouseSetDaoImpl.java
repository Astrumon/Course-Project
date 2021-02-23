package main.dao.impl.warehouse_dao_impl;

import main.DataSource;
import main.dao.impl.WagonDaoImpl;
import main.dao.warehouse_dao.WarehouseSetDao;
import main.model.Wagon;
import main.model.warehouse.Warehouse;
import main.model.warehouse.WarehouseSet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WarehouseSetDaoImpl implements WarehouseSetDao {

    private static int number = 0;

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
                warehouseSet.setIdWarehouse(resultSet.getLong(WarehouseSet.ID_WAREHOUES_COLUMN));
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


            WagonDaoImpl wagonDao = new WagonDaoImpl(dataSource);



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

    private static void numbering(int maxPosition) {
        number++;
        if (number == maxPosition) {
            number = 0;
        }
    }

    private boolean isSameWarehouse(WarehouseSet warehouseSet, Wagon wagon) {
        return warehouseSet.getNameWarehouse().equals(wagon.getNameWarehouse());
    }

    private boolean isEmptyWarehouseName(Wagon wagon) {
        return wagon.getNameWarehouse() == null;
    }

    private boolean isEmptyPosition(WarehouseSet warehouseSet) {
        return warehouseSet.getIdWagon() == 0;
    }

    private boolean isWagonNull(Wagon wagon) {
        try {

        } catch (NullPointerException exc) {
            System.out.println(exc);
        }
        return wagon == null;
    }

    private boolean isWarehouseSetNull(WarehouseSet warehouseSet) {
        try {

        } catch (NullPointerException exc) {
            System.out.println(exc);
        }
        return warehouseSet == null;
    }
    @Override
    public void addWagon( WarehouseSet warehouseSet, Wagon wagon, int position) {

        Connection connection = null;

        if (isWagonNull(wagon)) {
            System.out.println("wagon does not exist");
        }

        if (isWarehouseSetNull(warehouseSet)) {
            System.out.println("warehouse does not exist");
        }

        WarehouseSetDaoImpl warehouseSetDao = new WarehouseSetDaoImpl(dataSource);
        List<WarehouseSet> wSetsDao = warehouseSetDao.findAll();

        numbering(wSetsDao.size());
        Long idWagon = -1l;
        try {
            idWagon = wagon.getIdWagon();
            warehouseSet.setIdWagon(idWagon);
        } catch (NullPointerException exc) {
            System.out.println(exc);
        }
        wSetsDao.add(position, warehouseSet);

        if (isSameWarehouse(warehouseSet, wagon)
                || isEmptyWarehouseName(wagon)
                && isEmptyPosition(wSetsDao.get(position-1))) {
            try {
                connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_WAGON);
                preparedStatement.setLong(1, idWagon);
                preparedStatement.setString(2, warehouseSet.getNameWarehouse());
                preparedStatement.setLong(3, position);
                preparedStatement.execute();

                WagonDaoImpl wagonDao = new WagonDaoImpl(dataSource);
                warehouseSet.setIdWagon(idWagon);
                wagonDao.updateWarehouseSet(warehouseSet, wSetsDao.get(position-1).getId());

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
