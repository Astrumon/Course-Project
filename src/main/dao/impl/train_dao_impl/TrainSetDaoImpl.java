package main.dao.impl.train_dao_impl;

import main.DataSource;
import main.dao.impl.WagonDaoImpl;
import main.dao.impl.warehouse_dao_impl.WarehouseSetDaoImpl;
import main.dao.train_dao.TrainSetDao;
import main.model.train.Train;
import main.model.train.TrainSet;
import main.model.Wagon;
import main.model.warehouse.WarehouseSet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainSetDaoImpl implements TrainSetDao {

    private DataSource dataSource;


    public TrainSetDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private boolean isSameTrain(TrainSet trainSet, Wagon wagon) {
        System.out.println("trainSetName = " + trainSet.getName()
        + " wagonTrainName = " + wagon.getTrainName());
        return trainSet.getName().equals(wagon.getTrainName());
    }

    private boolean isEmptyTrainName(Wagon wagon) {
        System.out.println(wagon.getTrainName());
        return wagon.getTrainName() == null;
    }

    private boolean isSamePosition(TrainSet trainSet, int posWagon) {
        System.out.println("trainSet pos = " + trainSet.getPosWagon()
        + " posWagon = " + posWagon);
        return trainSet.getPosWagon() == posWagon;
    }

    private boolean samePosition(TrainSet trainSet, int position) {
        return trainSet.getPosWagon() == position;
    }

    private boolean isEmptyWarehouseName(Wagon wagon) {
        return wagon.getNameWarehouse() == null;
    }

    @Override
    public boolean addWagon(String trainName, Wagon wagon, int position) {
        Connection connection = null;

        assert wagon != null;


        TrainSetDaoImpl trainSetDao = new TrainSetDaoImpl(dataSource);
        List<TrainSet> tSetsDao = trainSetDao.findAll();

        Long idWagon = -1l;
        int numberOfPosition = 0;

        TrainSet tSet = new TrainSet();

        for (TrainSet trainSet : tSetsDao) {

            if (trainSet.getName().equals(trainName) && samePosition(trainSet, position) && trainSet.getIdWagon() == 0 ) {
                idWagon = wagon.getIdWagon();
                tSet.setPosWagon(position);
                tSet = trainSet;
                tSet.setIdWagon(idWagon);
                break;
            }
        }

        if (isEmptyWarehouseName(wagon) && tSet.getId() != null) {
            try {
                connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_WAGON);
                preparedStatement.setLong(1, idWagon);
                preparedStatement.setString(2, tSet.getName());
                preparedStatement.setInt(3, tSet.getPosWagon());
                preparedStatement.execute();

                WagonDaoImpl wagonDao = new WagonDaoImpl(dataSource);
                wagonDao.updateTrainSet(tSet, tSet.getId());

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
        return true;
    }


    @Override
    public List<TrainSet> findAll() {
        Connection connection = null;
        List<TrainSet> trainSets = new ArrayList<TrainSet>();

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                TrainSet trainSet = new TrainSet();
                trainSet.setId(rs.getLong(TrainSet.ID_COLUMN));
                trainSet.setName(rs.getString(TrainSet.NAME_COLUMN));
                trainSet.setIdWagon(rs.getLong(TrainSet.ID_WAGON_COLUMN));
                trainSet.setPosWagon(rs.getInt(TrainSet.POS_WAGON_COLUMN));
                trainSet.setIdTrain(rs.getLong(TrainSet.ID_TRAIN_COLUMN));
                trainSets.add(trainSet);
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

        return trainSets;
    }

    @Override
    public TrainSet findById(Long id) {
        Connection connection = null;
        TrainSet trainSet = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                trainSet = new TrainSet();
                trainSet.setId(rs.getLong(TrainSet.ID_COLUMN));
                trainSet.setName(rs.getString(TrainSet.NAME_COLUMN));
                trainSet.setIdWagon(rs.getLong(TrainSet.ID_WAGON_COLUMN));
                trainSet.setIdTrain(rs.getLong(TrainSet.ID_TRAIN_COLUMN));
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
        return trainSet;
    }

    @Override
    public TrainSet findByName(String name) {
        Connection connection = null;
        TrainSet trainSet = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                trainSet = new TrainSet();
                trainSet.setId(rs.getLong(TrainSet.ID_COLUMN));
                trainSet.setName(rs.getString(TrainSet.NAME_COLUMN));
                trainSet.setIdWagon(rs.getLong(TrainSet.ID_WAGON_COLUMN));
                trainSet.setIdTrain(rs.getLong(TrainSet.ID_TRAIN_COLUMN));
                trainSet.setPosWagon(rs.getInt(TrainSet.POS_WAGON_COLUMN));
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
        return trainSet;
    }

    @Override
    public void delete(TrainSet trainSet) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setLong(1, trainSet.getId());
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
    public void deleteByTrainName(Train train) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_TRAIN_NAME);
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

    @Override
    public void insert(TrainSet trainSet) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, trainSet.getName());
            preparedStatement.setInt(2, trainSet.getPosWagon());
            preparedStatement.setLong(3, trainSet.getIdTrain());
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                trainSet.setId(rs.getLong(1));
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

    @Override
    public void update(TrainSet trainSet) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);

            preparedStatement.setString(1, trainSet.getName());
            preparedStatement.setLong(2, trainSet.getIdWagon());
            preparedStatement.setLong(3, trainSet.getIdTrain());
            preparedStatement.setLong(4, trainSet.getId());
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

    private void showError(String text) {
        System.out.println(text);
    }
}
