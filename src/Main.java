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


        WarehouseDaoImpl warehouseDao1 = new WarehouseDaoImpl(dataSource);
        Wagon wagon = new Wagon();
        wagon.setId(4l);
        wagon.setSeatingCount(5);
        WagonDaoImpl wagonDao = new WagonDaoImpl(dataSource);
        wagonDao.insert(wagon);
        showAllWagons(wagonDao.findAll());

        System.out.println();
        Warehouse warehouse = new Warehouse();
        warehouse.setId(1l);
        warehouse.setIdWagon(wagon.getId());
        //warehouseDao1.insert(warehouse);

        wagonDao.delete(wagon);
        showAllWagons(wagonDao.findAll());
        showAllWarehouse(warehouseDao1.findAll());
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
