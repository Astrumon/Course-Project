package main;

import main.dao.impl.WagonDaoImpl;
import main.dao.impl.warehouse_dao_impl.WarehouseDaoImpl;
import main.dao.impl.warehouse_dao_impl.WarehouseSetDaoImpl;
import main.dao.warehouse_dao.WarehouseDao;
import main.model.Wagon;
import main.model.warehouse.Warehouse;
import main.model.warehouse.WarehouseSet;

import java.util.List;

public class TestWarehouse {
    private static final String PATH_DB = "jdbc:sqlite:C:\\Users\\Xiaomi\\IdeaProjects\\Course Project\\database\\railway.db";
    public static void main(String[] args) {

        DataSource dataSource = new DataSource();
        dataSource.setUrl(PATH_DB);
        WarehouseDaoImpl warehouseDao = new WarehouseDaoImpl(dataSource );

       warehouseDao.deleteByName(new Warehouse("warehouse#1", 3));
       // warehouseDao.insert(new Warehouse("warehouse#1", 3));
        showWarehouse(warehouseDao.findAll());

        WarehouseSetDaoImpl warehouseSetDao = new WarehouseSetDaoImpl(dataSource);
        // warehouseSetDao.insert( new WarehouseSet("test#1"));

        Wagon wagon = new Wagon();
        wagon.setType(1);
        wagon.setIdWagon(3l);
        wagon.setSeatingCount(100);

        Wagon wagon1 = new Wagon();
        wagon1.setType(1);
        wagon1.setIdWagon(5l);
        wagon1.setSeatingCount(200);

        WagonDaoImpl wagonDao =  new WagonDaoImpl(dataSource);
        wagonDao.insert(wagon);
        wagonDao.insert(wagon1);
        //warehouseSetDao.addWagon(warehouseSetDao.findByName("warehouse#1"), wagon);
       // warehouseSetDao.addWagon(warehouseSetDao.findByName("warehouse#1"), wagon1);
        System.out.println();
        showWarehouseSet(warehouseSetDao.findAll());
    }

    public static void showWarehouse(List<Warehouse> warehouses) {
        for (Warehouse warehouse : warehouses) {
            System.out.println(warehouse.toString());
        }
    }

    public static void showWarehouseSet(List<WarehouseSet> warehouseSets) {
        for (WarehouseSet warehouseSet : warehouseSets) {
            System.out.println(warehouseSet.toString());
        }
    }
}
