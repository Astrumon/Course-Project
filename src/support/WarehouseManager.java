package support;

import data_access.dao.impl.warehouse_dao_impl.WarehouseDaoImpl;
import data_access.dao.impl.warehouse_dao_impl.WarehouseSetDaoImpl;
import data_access.model.wagon.Wagon;
import data_access.model.warehouse.Warehouse;
import data_access.model.warehouse.WarehouseSet;

import java.util.List;

public class WarehouseManager extends Manager {

    public static final int WAREHOUSE_CAPACITY = 100;

    private WarehouseDaoImpl warehouseDao;

    private WarehouseSetDaoImpl warehouseSetDao;

    private Warehouse warehouse;

    private List<Warehouse> warehouses;



    public WarehouseManager() {
        warehouseDao = new WarehouseDaoImpl(dataSource);
        warehouseSetDao = new WarehouseSetDaoImpl(dataSource);
        warehouse = new Warehouse();
        warehouse.setCapacity(WAREHOUSE_CAPACITY);
    }

    public List<Warehouse> getWarehouses() {
        return warehouseDao.findAll();
    }

    public boolean deleteWarehouse(String nameWarehouse) {
        warehouse.setName(nameWarehouse);
       return warehouseDao.deleteByName(warehouse);
    }

    public List<WarehouseSet> getWarehouseSets() {
        return warehouseSetDao.findAll();
    }

    public boolean createWarehouse(String nameWarehouse) {
        warehouse.setName(nameWarehouse);
        return warehouseDao.insert(warehouse);
    }

    public boolean addWagonToWarehouse(String nameWarehouse, Wagon wagon, int position) {
        return warehouseSetDao.addWagon(nameWarehouse, wagon, position);
    }



}
