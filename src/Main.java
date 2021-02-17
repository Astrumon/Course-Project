import main.DataSource;
import main.dao.TrainDao;
import main.dao.impl.TrainDaoImpl;
import main.dao.impl.WagonDaoImpl;
import main.dao.impl.WarehouseDaoImpl;
import main.model.Train;
import main.model.Wagon;
import main.model.Warehouse;
import org.w3c.dom.ls.LSOutput;

import java.sql.Connection;
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

        TrainDaoImpl trainDao = new TrainDaoImpl(dataSource);

        Train train  = new Train();
        train.setId(1l);
        train.setName("train1");
        train.setCountWagons(3);

        Train train1  = new Train();
        train1.setId(2l);
        train1.setName("train1");

        Train train2  = new Train();
        train2.setId(3l);
        train2.setName("train1");

        Train train3  = new Train();
        train3.setId(4l);
        train3.setName("train1");

        Train train4  = new Train();
        train4.setId(5l);
        train4.setCountWagons(10);
        train4.setName("train2");

        Train train5  = new Train();
        train5.setId(6l);
        train5.setName("train2");

        Train train6  = new Train();
        train6.setId(7l);
        train6.setName("train1");

        System.out.println(train1);
        System.out.println(train.toString());


        System.out.println();
        trainDao.insert(train6);
        trainDao.insert(train3);
        trainDao.insert(train4);
        trainDao.insert(train5);
        trainDao.insert(train2);
        trainDao.insert(train);
        trainDao.insert(train1);
//        trainDao.delete(train);
//        trainDao.delete(train1);
//        trainDao.delete(train2);
//        trainDao.delete(train3);
//        trainDao.delete(train4);
//        trainDao.delete(train5);
//        trainDao.delete(train6);

        showAllTrains(trainDao.findAll());









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

    public static void showTrain(Train train) {
        System.out.println(train.toString());
    }

    public static void showAllTrains(List<Train> trains) {
        for (Train train : trains) {
            System.out.println(train);
        }
    }
}
