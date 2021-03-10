package data_access.dao.impl.train_dao_impl;

import data_access.DataSource;
import data_access.dao.impl.wagon_dao_impl.WagonDaoImpl;
import data_access.dao.impl.warehouse_dao_impl.WarehouseDaoImpl;
import data_access.dao.impl.warehouse_dao_impl.WarehouseSetDaoImpl;
import data_access.dao.train_dao.TrainDao;
import data_access.dao.train_dao.TrainSetDao;
import data_access.model.wagon.Wagon;
import data_access.model.train.Train;
import data_access.model.train.TrainSet;
import data_access.model.warehouse.Warehouse;
import data_access.model.warehouse.WarehouseSet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс TrainSetDaoImpl служит для создания состава определенного поезда
 * Взаимодействует с таблицами train, train_set, wagon
 */
public class TrainSetDaoImpl implements TrainSetDao {

    private DataSource dataSource;

    /**
     * Конструктор для подключения к базе данных
     * @param dataSource
     */
    public TrainSetDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    /**
     * Метод для проверки позиции вагона в поезде
     * @param trainSet
     * @param position
     * @return
     */
    private boolean samePosition(TrainSet trainSet, int position) {
        return trainSet.getPosWagon() == position;
    }

    /**
     * Метод для проверки имени поезда у вагона
     * @param nameTrain
     * @return
     */
    private boolean isEmptyTrainName(String  nameTrain) {
        return nameTrain == null;
    }

    private void counterWagons(String trainName) {

        TrainSetDaoImpl trainSetDao = new TrainSetDaoImpl(dataSource);

        int count = 0;

        for (TrainSet trainSet : trainSetDao.findAll()) {
            if (trainSet.getIdWagon() != 0 && trainSet.getName().equals(trainName)) {
                count++;
            }
        }

        Train train = new Train();
        train.setName(trainName);
        train.setCountWagon(count);

        TrainDaoImpl trainDao = new TrainDaoImpl(dataSource);
        trainDao.update(train);
    }

    private TrainSet getFilledTrainSet(Wagon wagon, int position) {

        TrainSetDaoImpl trainSetDaoSetDao = new TrainSetDaoImpl(dataSource);
        TrainSet tSet = new TrainSet();
        for (TrainSet trainSet : trainSetDaoSetDao.findAll()) {

            if (trainSet.getName().equals(wagon.getTrainName()) && samePosition(trainSet, position) && trainSet.getIdWagon() == 0) {
                tSet = trainSet;
                tSet.setIdWagon(wagon.getIdWagon());
                tSet.setPosWagon(position);
                break;
            }
        }

        return tSet;
    }

   private void  updateWagonTrainSetInfo(TrainSet trainSet) {
       TrainDaoImpl trainDao = new TrainDaoImpl(dataSource);
       trainDao.updateTrainSet(trainSet, trainSet.getId());
   }
    /**
     * Метод который служит для добавления информации о вагоне в таблицу train_set
     * Информация о поезде так же записывается в таблицу wagon
     * @param trainName
     * @param wagon
     * @param position
     * @return
     */
    @Override
    public boolean addWagon(String trainName, Wagon wagon, int position) {
        Connection connection = null;
        assert wagon != null;

        String trainNameOfWagon = wagon.getNameWarehouse();

        wagon.setTrainName(trainName);
        TrainSet trainSet = getFilledTrainSet(wagon, position);



        if (isEmptyTrainName(trainNameOfWagon) && trainSet.getId() != null) {
            try {
                connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_WAGON);
                preparedStatement.setLong(1, trainSet.getIdWagon());
                preparedStatement.setString(2, trainSet.getName());
                preparedStatement.setInt(3, trainSet.getPosWagon());
                preparedStatement.execute();


                updateWagonTrainSetInfo(trainSet);
                counterWagons(trainName);
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
     * Выборка всей информации из таблицы train_set
     * @return
     */
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

    /**
     * Выборка всей информации одной записи по заданому id из таблицы train_set
     * @param id
     * @return
     */
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

    /**
     * Выборка всей информации одной записи по заданому имени поезда из таблицы train_set
     * @param name
     * @return
     */
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

    /**
     * Удаление записи с таблицы train_set по train_set.id
     * @param trainSet
     */
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

    /**
     * Удаление записи с таблицы train_set по train.name
     * @param train
     */
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

    /**
     * Вставка записи информации про поезд в таблицу train_set.
     * @param trainSet
     */
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

    /**
     * Обновляет запись в таблице train_set информацией об объекте trainSet
     * @param trainSet
     */
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
}
