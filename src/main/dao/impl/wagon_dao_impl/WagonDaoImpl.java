package main.dao.impl.wagon_dao_impl;

import main.DataSource;
import main.dao.wagon_dao.WagonDao;
import main.model.train.TrainSet;
import main.model.wagon.Place;
import main.model.wagon.TypePlace;
import main.model.wagon.Wagon;
import main.model.warehouse.WarehouseSet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WagonDaoImpl implements WagonDao {
    private DataSource dataSource;

    public WagonDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public List<Wagon> findAll() {
        Connection connection = null;
        List<Wagon> wagons = new ArrayList<Wagon>();

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Wagon wagon = new Wagon();
                wagon.setId(rs.getLong(Wagon.ID_COLUMN_COLUMN));
                wagon.setIdWagon(rs.getLong(Wagon.ID_WAGON_COLUMN));
                wagon.setNameWarehouse(rs.getString(Wagon.NAME_WAREHOUSE_COLUMN));
                wagon.setTrainName(rs.getString(Wagon.TRAIN_NAME_COLUMN));
                wagon.setPosTrain(rs.getInt(Wagon.POSITION_TRAIN_COLUMN));
                wagon.setType(rs.getInt(Wagon.TYPE_COLUMN));
                wagon.setIdTrainSet(rs.getLong(Wagon.ID_TRAIN_SET_COLUMN));
                wagon.setIdWarehouseSet(rs.getLong(Wagon.ID_WAREHOUSE_SET_COLUMN));
                wagon.setIdCountTypePlace(rs.getLong(Wagon.ID_COUNT_TYPE_PLACE_COLUMN));
                wagons.add(wagon);
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

        return wagons;
    }

    @Override
    public Wagon findById(Long id) {
        Connection connection = null;
        Wagon wagon = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                wagon = new Wagon();
                wagon.setId(rs.getLong(Wagon.ID_COLUMN_COLUMN));
                wagon.setIdWagon(rs.getLong(Wagon.ID_WAGON_COLUMN));
                wagon.setNameWarehouse(rs.getString(Wagon.NAME_WAREHOUSE_COLUMN));
                wagon.setPosTrain(rs.getInt(Wagon.POSITION_TRAIN_COLUMN));
                wagon.setIdTrainSet(rs.getLong(Wagon.ID_TRAIN_SET_COLUMN));
                wagon.setIdWarehouseSet(rs.getLong(Wagon.ID_WAREHOUSE_SET_COLUMN));
                wagon.setIdCountTypePlace(rs.getLong(Wagon.ID_COUNT_TYPE_PLACE_COLUMN));
                wagon.setType(rs.getInt(Wagon.TYPE_COLUMN));
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
        return wagon;
    }

    @Override
    public Wagon findByIdWagon(Long id) {
        Connection connection = null;
        Wagon wagon = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID_WAGON);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                wagon = new Wagon();
                wagon.setId(rs.getLong(Wagon.ID_COLUMN_COLUMN));
                wagon.setIdWagon(rs.getLong(Wagon.ID_WAGON_COLUMN));
                wagon.setTrainName(rs.getString(Wagon.TRAIN_NAME_COLUMN));
                wagon.setNameWarehouse(rs.getString(Wagon.NAME_WAREHOUSE_COLUMN));
                wagon.setPosTrain(rs.getInt(Wagon.POSITION_TRAIN_COLUMN));
                wagon.setIdTrainSet(rs.getLong(Wagon.ID_TRAIN_SET_COLUMN));
                wagon.setIdWarehouseSet(rs.getLong(Wagon.ID_WAREHOUSE_SET_COLUMN));
                wagon.setIdCountTypePlace(rs.getLong(Wagon.ID_COUNT_TYPE_PLACE_COLUMN));
                wagon.setType(rs.getInt(Wagon.TYPE_COLUMN));
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
        return wagon;
    }

    @Override
    public void updateWarehouseSet(WarehouseSet warehouseSet, Long idWarehouseSet) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementWagon = connection.prepareStatement(SQL_UPDATE_WAREHOUSE_SET);
            preparedStatementWagon.setLong(1, idWarehouseSet);
            preparedStatementWagon.setString(2, warehouseSet.getNameWarehouse());
            preparedStatementWagon.setLong(3, warehouseSet.getIdWagon());
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
    }

    @Override
    public void updateTrainSet(TrainSet trainSet, Long idTrainSet) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementWagon = connection.prepareStatement(SQL_UPDATE_TRAIN_SET);
            preparedStatementWagon.setLong(1, idTrainSet);
            preparedStatementWagon.setString(2, trainSet.getName());
            preparedStatementWagon.setInt(3, trainSet.getPosWagon());
            preparedStatementWagon.setLong(4, trainSet.getIdWagon());
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
    }

    @Override
    public void delete(Wagon wagon) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);

            preparedStatement.setLong(1, wagon.getId());
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

    private void createTypePlace(TypePlace typePlace, int type) {
        PlaceDaoImpl placeDao = new PlaceDaoImpl(dataSource);
        Place place = new Place();
        place.setType(type);
        place.setIdWagon(typePlace.getIdWagon());
        place.setIdCountType(typePlace.getIdTypePlace());
        System.out.println("sum = " + typePlace.getAllPlace());
        if (place.getIdWagon().equals(typePlace.getIdWagon()) && placeDao.findAll().size() + 1 <= typePlace.getAllPlace()) {
            for (int i = 0; i < typePlace.defineType(type); i++) {
                placeDao.insert(place);
            }
        } else {
            System.out.println("places set");
        }

    }

    private void createPlace(Long idCountTypePlace) {
        TypePlaceDaoImpl typePlaceDao = new TypePlaceDaoImpl(dataSource);
        TypePlace typePlace = typePlaceDao.findById(idCountTypePlace);

        createTypePlace(typePlace, TypePlace.VIP);
        createTypePlace(typePlace, TypePlace.MIDDLE);
        createTypePlace(typePlace, TypePlace.LOW);
        createTypePlace(typePlace, TypePlace.SEATS);
    }

    @Override
    public void setTypePlace(Wagon wagon, TypePlace typePlace) {
        Connection connection = null;
        if (wagon.getType() == 1) {
            TypePlaceDaoImpl typePlaceDao = new TypePlaceDaoImpl(dataSource);
            typePlace.setIdWagon(wagon.getIdWagon());
            typePlaceDao.update(typePlace);

            try {
                connection = dataSource.getConnection();
                PreparedStatement preparedStatementWagon = connection.prepareStatement(SQL_UPDATE_ID_TYPE_PLACE);
                preparedStatementWagon.setLong(1, typePlace.getIdTypePlace());
                preparedStatementWagon.setLong(2, typePlace.getIdWagon());
                preparedStatementWagon.execute();

                createPlace(typePlace.getIdTypePlace());

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
            System.out.println("CARGO");
        }
    }

    @Override
    public Long insert(Wagon wagon) {
        Connection connection = null;
        Long id = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, wagon.getIdWagon());
            preparedStatement.setInt(2, wagon.getType());
            preparedStatement.setInt(3, wagon.getPosTrain());
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                id = rs.getLong(1);
                wagon.setId(id);

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

    @Override
    public void update(Wagon wagon) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, wagon.getNameWarehouse());
            preparedStatement.setLong(2, wagon.getPosTrain());
            preparedStatement.setLong(3, wagon.getIdWagon());
            preparedStatement.setInt(4, wagon.getType());
            preparedStatement.setLong(5, wagon.getIdTrainSet());
            preparedStatement.setLong(6, wagon.getIdWarehouseSet());
            preparedStatement.setLong(7, wagon.getId());
            preparedStatement.setLong(8, wagon.getIdCountTypePlace());
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
