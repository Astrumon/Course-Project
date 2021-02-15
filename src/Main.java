import main.DataSource;
import main.dao.impl.WagonDaoImpl;
import main.dao.impl.WarehouseDaoImpl;
import main.model.Wagon;
import main.model.Warehouse;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    private static final String PATH_DB = "jdbc:sqlite:C:\\Users\\Xiaomi\\IdeaProjects\\Course Project\\database\\railway.db";

    public static void main(String[] args) throws SQLException {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(PATH_DB);


        Wagon wagon = new Wagon(1l, 5, 1l, 1l, 3);
        Wagon wagon1 = new Wagon(4l, 15, 2l, 1l, 3);
        Wagon wagon2 = new Wagon(2l, 25, 3l, 1l, 3);

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
}
