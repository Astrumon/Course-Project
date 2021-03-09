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

/**
 * Класс WagonDaoImpl служит для создания вагонов
 * взаимодействует с таблицей wagon, count_type_place, place, train_set, warehouse_set
 */
public class WagonDaoImpl implements WagonDao {
    private DataSource dataSource;

    /**
     * Конструктор служит для установки подключения к базе данных
     *
     * @param dataSource
     */
    public WagonDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    /**
     * Выборка всей информации из таблицы wagon
     *
     * @return
     */
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
                wagon.setId(rs.getLong(Wagon.ID_COLUMN));
                wagon.setIdWagon(rs.getLong(Wagon.ID_WAGON_COLUMN));
                wagon.setNameWarehouse(rs.getString(Wagon.NAME_WAREHOUSE_COLUMN));
                wagon.setTrainName(rs.getString(Wagon.TRAIN_NAME_COLUMN));
                wagon.setPosTrain(rs.getInt(Wagon.POSITION_TRAIN_COLUMN));
                wagon.setType(rs.getInt(Wagon.TYPE_COLUMN));
                wagon.setIdTrainSet(rs.getLong(Wagon.ID_TRAIN_SET_COLUMN));
                wagon.setIdWarehouseSet(rs.getLong(Wagon.ID_WAREHOUSE_SET_COLUMN));
                wagon.setIdCountTypePlace(rs.getLong(Wagon.ID_COUNT_TYPE_PLACE_COLUMN));
                wagon.setCountSeats(rs.getInt(Wagon.COUNT_SEATS_COLUMN));
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

    /**
     * Выборка всей информации одной записи по заданому id из таблицы wagon
     *
     * @param id
     * @return
     */
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
                wagon.setId(rs.getLong(Wagon.ID_COLUMN));
                wagon.setIdWagon(rs.getLong(Wagon.ID_WAGON_COLUMN));
                wagon.setNameWarehouse(rs.getString(Wagon.NAME_WAREHOUSE_COLUMN));
                wagon.setPosTrain(rs.getInt(Wagon.POSITION_TRAIN_COLUMN));
                wagon.setIdTrainSet(rs.getLong(Wagon.ID_TRAIN_SET_COLUMN));
                wagon.setIdWarehouseSet(rs.getLong(Wagon.ID_WAREHOUSE_SET_COLUMN));
                wagon.setIdCountTypePlace(rs.getLong(Wagon.ID_COUNT_TYPE_PLACE_COLUMN));
                wagon.setCountSeats(rs.getInt(Wagon.COUNT_SEATS_COLUMN));
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

    /**
     * Выборка всей информации одной записи по заданому Wagon.id из таблицы wagon
     *
     * @param id
     * @return
     */
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
                wagon.setId(rs.getLong(Wagon.ID_COLUMN));
                wagon.setIdWagon(rs.getLong(Wagon.ID_WAGON_COLUMN));
                wagon.setTrainName(rs.getString(Wagon.TRAIN_NAME_COLUMN));
                wagon.setNameWarehouse(rs.getString(Wagon.NAME_WAREHOUSE_COLUMN));
                wagon.setPosTrain(rs.getInt(Wagon.POSITION_TRAIN_COLUMN));
                wagon.setIdTrainSet(rs.getLong(Wagon.ID_TRAIN_SET_COLUMN));
                wagon.setIdWarehouseSet(rs.getLong(Wagon.ID_WAREHOUSE_SET_COLUMN));
                wagon.setIdCountTypePlace(rs.getLong(Wagon.ID_COUNT_TYPE_PLACE_COLUMN));
                wagon.setCountSeats(rs.getInt(Wagon.COUNT_SEATS_COLUMN));
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

    /**
     * Обновляет записи из таблицы wagon информацией про warehouset
     *
     * @param warehouseSet
     * @param idWarehouseSet
     */
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

    /**
     * Обновляет записи из таблицы wagon информацией про trainset
     *
     * @param trainSet
     * @param idTrainSet
     */
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

    /**
     * Удаление записи с таблицы train по wagon.id
     *
     * @param wagon
     */
    @Override
    public void delete(Wagon wagon) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);

            preparedStatement.setLong(1, wagon.getIdWagon());
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
     * Создаёт места (записи в таблице place) определенного типа
     *
     * @param typePlace POJO объект таблицы count_type_place
     * @param type - тип места 1 - VIP, 2 - MIDDLE, 3 - LOW, 4 - SEATS
     */
    private void createTypePlace(TypePlace typePlace, int type) {
        PlaceDaoImpl placeDao = new PlaceDaoImpl(dataSource);

        Place place = new Place();
        place.setType(type);
        place.setIdWagon(typePlace.getIdWagon());
        place.setIdCountType(typePlace.getIdTypePlace());

        for (int i = 1; i <= typePlace.defineType(type); i++) {

            place.setNumber(NumberGenerator.number++);
            placeDao.insert(place);
            if (i == typePlace.getAllPlace()) {
                NumberGenerator.number = 1;
            }
        }
    }

    /**
     * Создание мест для вагона
     * @param idCountTypePlace
     */
    protected void createPlace(Long idCountTypePlace) {
        TypePlaceDaoImpl typePlaceDao = new TypePlaceDaoImpl(dataSource);
        TypePlace typePlace = typePlaceDao.findById(idCountTypePlace);

        createTypePlace(typePlace, TypePlace.VIP);
        createTypePlace(typePlace, TypePlace.MIDDLE);
        createTypePlace(typePlace, TypePlace.LOW);
        createTypePlace(typePlace, TypePlace.SEATS);
    }

    @Override
    public void setCountSeats(TypePlace typePlace) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_COUNT_SEATS);
            preparedStatement.setLong(1, typePlace.getAllPlace());
            preparedStatement.setLong(2, typePlace.getIdWagon());
            preparedStatement.execute();

        } catch (SQLException exc ) {
            System.out.println(exc);
        } finally {
            try {
                connection.close();
            }catch (SQLException exc) {
                System.out.println(exc);
            }
        }
    }
    /**
     * Добавляет пассажирскому вагону доступное количество созданных мест определенного типа
     * @param wagon
     * @param typePlace
     */
    @Override
    public boolean setTypePlace(Wagon wagon, TypePlace typePlace) {
        Connection connection = null;
        if (wagon.getType() == Wagon.PASSENGER_TYPE) {
            TypePlaceDaoImpl typePlaceDao = new TypePlaceDaoImpl(dataSource);
            typePlace.setIdWagon(wagon.getIdWagon());
            typePlaceDao.insert(typePlace);

            WagonDaoImpl wagonDao = new WagonDaoImpl(dataSource);
            wagonDao.setCountSeats(typePlace);
            try {
                connection = dataSource.getConnection();
                try {
                    PreparedStatement preparedStatementWagon = connection.prepareStatement(SQL_UPDATE_ID_TYPE_PLACE);
                    preparedStatementWagon.setLong(1, typePlace.getIdTypePlace());
                    preparedStatementWagon.setLong(2, typePlace.getIdWagon());
                    preparedStatementWagon.execute();
                    createPlace(typePlace.getIdTypePlace());
                } catch (NullPointerException exc) {
                    System.out.println(exc + " duplicate id_type_place");
                    return false;
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
            return true;
        } else {
            System.out.println("CARGO");
            return false;
        }
    }


    /**
     * Вставка записи информации про вагон в таблицу wagon.
     *
     * @param wagon
     */
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

    /**
     * Обновляет запись в таблице wagon информацией об объекте wagon
     * @param wagon
     */
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
            preparedStatement.setLong(8, wagon.getId());
            preparedStatement.setLong(9, wagon.getIdCountTypePlace());
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
     * Данный класс позволяет генерировать номер места вагона в зависимости от его типа
     * 11(первый индекс числа отвечает за тип места, второй за его количество)
     */
    private static class NumberGenerator {
        static int number = 1;

        public static int generate(int type) {
            return Integer.parseInt(type + "" + number);
        }
    }


}
