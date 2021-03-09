package support;

import main.DataSource;
import main.dao.impl.wagon_dao_impl.TypePlaceDaoImpl;
import main.dao.impl.wagon_dao_impl.WagonDaoImpl;
import main.model.wagon.TypePlace;
import main.model.wagon.Wagon;

import java.util.List;

public class WagonManager {

    DataSource dataSource = new DataSource();

    private WagonDaoImpl wagonDao;

    private TypePlaceDaoImpl typePlaceDao;

    private Wagon wagon;

    private List<Wagon> wagons;

    public WagonManager() {
        wagonDao = new WagonDaoImpl(dataSource);
        typePlaceDao = new TypePlaceDaoImpl(dataSource);
        wagon = new Wagon();

    }

    public boolean deleteWagon(Long idWagon) {
        wagon.setIdWagon(idWagon);
        wagonDao.delete(wagon);
        return deleteWagonFromWarehouse(idWagon);
    }

    private boolean deleteWagonFromWarehouse(Long idWagon) {
        return typePlaceDao.deleteByIdWagon(idWagon);
    }

    public boolean createWagon(Long idWagon, TypePlace typePlace) {
        wagon.setIdWagon(idWagon);
        wagon.setType(1);
        wagonDao.insert(wagon);
        return wagonDao.setTypePlace(wagon, typePlace);
    }

    public boolean updateWagon(Long idWagon, TypePlace typePlace) {
        typePlace.setIdWagon(idWagon);
        return typePlaceDao.update(typePlace);
    }

    public List<Wagon> getWagons() {
        wagons = wagonDao.findAll();
        return wagons;
    }
}
