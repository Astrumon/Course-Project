package main.dao.impl;

import main.DataSource;
import main.dao.TrainSetDao;
import main.model.Train;
import main.model.TrainSet;
import main.model.Wagon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainSetDaoImpl implements TrainSetDao {

    private DataSource dataSource;
    private List<TrainSet> trainSets;

    public TrainSetDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean addWagon(TrainSet trainSet, Wagon wagon) {
        Connection connection = null;


        assert wagon != null;
        if (trainSet.getName().equals(wagon.getTrainName()) || wagon.getTrainName() == null && wagon.getPosTrain() == 0) {
            wagon.setTrainName(trainSet.getName());
            wagon.setPosTrain(trainSet.getPosWagon());
            trainSet.setIdWagon(wagon.getIdWagon());


            try {
                connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_WAGON);
                preparedStatement.setLong(1, trainSet.getIdWagon());
                preparedStatement.setString(2, trainSet.getName());
                preparedStatement.setInt(3, trainSet.getPosWagon());
                preparedStatement.execute();

                String SQL_UPDATE_TRAIN_NAME_AND_POS = "UPDATE " + Wagon.TABLE_NAME + " SET "
                        + Wagon.TRAIN_NAME_COLUMN + " = ?, " + Wagon.POSITION_TRAIN_COLUMN + " = ? WHERE "
                        + Wagon.ID_WAGON_COLUMN + " = ?";
                PreparedStatement preparedStatementWagon = connection.prepareStatement(SQL_UPDATE_TRAIN_NAME_AND_POS);
                preparedStatementWagon.setString(1, wagon.getTrainName());
                preparedStatementWagon.setInt(2, trainSet.getPosWagon());
                preparedStatementWagon.setLong(3, trainSet.getIdWagon());
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
        return true;
    }


    @Override
    public List<TrainSet> findAll() {
        Connection connection = null;
        trainSets = new ArrayList<TrainSet>();

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
        // trainSets = findAll();
//        //TODO занести в метод
//        if (trainSets != null) {
//            int countWagons = 0;
//            for (TrainSet value : trainSets) {
//                if (value.getName().equals(trainSet.getName()) && value.getPosWagon() != 0) {
//                    countWagons = value.getPosWagon();
//                    if (countWagons != 0) {
//                        countWagons--;
//                        trainSet.setPosWagon(countWagons);
//                    }
//                }
//            }
//        }
//        if (trainSet.getPosWagon() != 0) {
        try {
            connection = dataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, trainSet.getName());
            preparedStatement.setInt(2, trainSet.getPosWagon());
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
//                System.out.println("in method insert: size trainSets" + trainSets.size());
            } catch (SQLException exc) {
                System.out.println(exc);
            }
        }
//        } else {
//            showError(trainSet.getName() + " - мест больше нет");
//        }
    }

    @Override
    public void update(TrainSet trainSet) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);

            preparedStatement.setString(1, trainSet.getName());
            preparedStatement.setLong(2, trainSet.getIdWagon());
            preparedStatement.setLong(3, trainSet.getId());
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
