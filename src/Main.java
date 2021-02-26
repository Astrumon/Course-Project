import main.DataSource;
import main.dao.impl.train_dao_impl.TrainDaoImpl;
import main.dao.impl.train_dao_impl.TrainSetDaoImpl;
import main.dao.impl.wagon_dao_impl.WagonDaoImpl;
import main.model.train.Train;
import main.model.train.TrainSet;
import main.model.wagon.Wagon;
import main.model.warehouse.Warehouse;

import java.sql.SQLException;
import java.util.List;

public class Main {
    private static final String PATH_DB = "jdbc:sqlite:C:\\Users\\Xiaomi\\IdeaProjects\\Course Project\\database\\railway.db";

    public static void main(String[] args) throws SQLException {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(PATH_DB);

        TrainSetDaoImpl trainSetDao = new TrainSetDaoImpl(dataSource);

        Train train1 = new Train();
        train1.setName("train#4");
        train1.setCount_wagon(5);

        TrainDaoImpl trainDao = new TrainDaoImpl(dataSource);
        //trainDao.insert(train1);
        trainDao.deleteByName(train1);
       // trainSetDao.deleteByTrainName(train1);

        TrainSet trainSet = new TrainSet();
        trainSet.setName("train#2");
        trainSet.setPosWagon(3);
        Train tr = new Train();
        tr.setName(null);


        Wagon wagon = new Wagon( 2l, null, "#1", 0, 1);
        WagonDaoImpl wagonDao = new WagonDaoImpl(dataSource);
        //wagonDao.insert(wagon);
        showAllWagons(wagonDao.findAll());
        //trainSetDao.addWagon(trainSet, wagonDao.findByIdWagon(wagon.getIdWagon()));
       // trainSetDao.deleteByTrainName(tr);

        showAllTrain(trainDao.findAll());

        System.out.println();
        showAllTrainSet(trainSetDao.findAll());

        showAllWagons(wagonDao.findAll());
    }

    public static void showWagon(Wagon wagon) {
        System.out.println(wagon.toString());
    }

    public static void showAllWagons(List<Wagon> wagons) {
        for (Wagon wagon : wagons) {
            System.out.println(wagon);
        }
    }

    public static void showWarehouse(Warehouse warehouse) {
        System.out.println(warehouse.toString());
    }

    public static void showAllWarehouse(List<Warehouse> warehouses) {
        for (Warehouse warehouse : warehouses) {
            System.out.println(warehouse);
        }
    }

    public static void showTrainSet(TrainSet trainSet) {
        System.out.println(trainSet.toString());
    }

    public static void showAllTrainSet(List<TrainSet> trainSets) {
        for (TrainSet trainSet : trainSets) {
            System.out.println(trainSet);
        }
    }

    public static void showTrain(Train train) {
        System.out.println(train.toString());
    }

    public static void showAllTrain(List<Train> trains) {
        for (Train train : trains) {
            System.out.println(train);
        }
    }
}
