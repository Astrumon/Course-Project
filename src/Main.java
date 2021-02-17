import main.DataSource;
import main.dao.impl.TrainDaoImpl;
import main.dao.impl.TrainSetDaoImpl;
import main.dao.impl.WagonDaoImpl;
import main.dao.impl.WarehouseDaoImpl;
import main.model.Train;
import main.model.TrainSet;
import main.model.Wagon;
import main.model.Warehouse;

import java.sql.SQLException;
import java.util.List;

public class Main {
    private static final String PATH_DB = "jdbc:sqlite:C:\\Users\\Xiaomi\\IdeaProjects\\Course Project\\database\\railway.db";

    public static void main(String[] args) throws SQLException {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(PATH_DB);


        Wagon wagon = new Wagon(1l, 5, 1l, "", 1l, 3, 1);
        Wagon wagon1 = new Wagon(4l, 15, 2l, "",  1l, 3, 2);
        Wagon wagon2 = new Wagon(2l, 25, 3l,"", 1l, 3, 3);

        WagonDaoImpl wagonDao = new WagonDaoImpl(dataSource);
        wagonDao.insert(wagon);
        wagonDao.insert(wagon1);
        wagonDao.insert(wagon2);

        //wagonDao.delete(wagon1);

        showAllWagons(wagonDao.findAll());

        System.out.println();
        WarehouseDaoImpl warehouseDao = new WarehouseDaoImpl(dataSource);

        warehouseDao.delete(new Warehouse(1l, 4l, null));
        Warehouse warehouse = new Warehouse(2l, 1l, "#1");
        Warehouse warehouse1 = new Warehouse(3l, 2l, "#2");
        Warehouse warehouse2 = new Warehouse(1l, 3l, "#2");

        wagonDao.delete(wagon);
        warehouseDao.delete(warehouse);
        warehouseDao.delete(warehouse1);

        warehouseDao.insert(warehouse);
        warehouseDao.insert(warehouse1);
        warehouseDao.insert(warehouse2);

        showAllWarehouse(warehouseDao.findAll());

        wagonDao.delete(wagon);
        wagonDao.delete(wagon1);
        wagonDao.delete(wagon2);

        TrainSetDaoImpl trainSetDao = new TrainSetDaoImpl(dataSource);

        Train train = new Train();
        train.setName("train#1");
        train.setCount_wagon(5);

        TrainDaoImpl trainDao = new TrainDaoImpl(dataSource);
        trainDao.insert(train);
        showAllTrain(trainDao.findAll());



//        TrainSet trainSet = new TrainSet();
//        trainSet.setId(1l);
//        trainSet.setName("trainSet1");
//        trainSet.setCountWagons(3);
//
//        TrainSet trainSet1 = new TrainSet();
//        trainSet1.setId(2l);
//        trainSet1.setName("trainSet1");
//
//        TrainSet trainSet2 = new TrainSet();
//        trainSet2.setId(3l);
//        trainSet2.setName("trainSet1");
//
//        TrainSet trainSet3 = new TrainSet();
//        trainSet3.setId(4l);
//        trainSet3.setName("trainSet1");
//
//        TrainSet trainSet4 = new TrainSet();
//        trainSet4.setId(5l);
//        trainSet4.setCountWagons(10);
//        trainSet4.setName("trainSet2");
//
//        TrainSet trainSet5 = new TrainSet();
//        trainSet5.setId(6l);
//        trainSet5.setName("trainSet2");
//
//        TrainSet trainSet6 = new TrainSet();
//        trainSet6.setId(7l);
//        trainSet6.setName("trainSet1");
//
//        System.out.println(trainSet1);
//        System.out.println(trainSet.toString());


        System.out.println();
//        trainDao.insert(trainSet6);
//        trainDao.insert(trainSet3);
//        trainDao.insert(trainSet4);
//        trainDao.insert(trainSet5);
//        trainDao.insert(trainSet2);
//        trainDao.insert(trainSet);
//        trainDao.insert(trainSet1);
//        trainDao.delete(trainSet);
//        trainDao.delete(trainSet1);
//        trainDao.delete(trainSet2);
//        trainDao.delete(trainSet3);
//        trainDao.delete(trainSet4);
//        trainDao.delete(trainSet5);
//        trainDao.delete(trainSet6);

       // showAllTrainSet(trainDao.findAll());









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
