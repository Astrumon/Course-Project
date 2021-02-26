package main.tests;

import main.DataSource;
import main.dao.impl.wagon_dao_impl.TypePlaceDaoImpl;
import main.dao.impl.wagon_dao_impl.WagonDaoImpl;
import main.model.wagon.TypePlace;
import main.model.wagon.Wagon;

public class TestPlace {
    public static void main(String[] args) {
        TypePlace typePlace = new TypePlace();
        typePlace.setCountSeats(10);
        typePlace.setCountVip(1);
        typePlace.setCountMiddle(5);
        typePlace.setCountLow(5);

        DataSource dataSource = new DataSource();
        dataSource.setUrl(TestTrain.PATH_DB);
        TypePlaceDaoImpl typePlaceDao = new TypePlaceDaoImpl(dataSource);

       // typePlaceDao.insert(typePlace);
        //System.out.println(typePlaceDao.findAll().size());
       typePlace.setIdTypePlace(typePlaceDao.findAll().get(0).getIdTypePlace());
        WagonDaoImpl wagonDao = new WagonDaoImpl(dataSource);

        Wagon wagon = new Wagon();
        wagon.setIdWagon(3l);
        wagon.setType(1);

        wagonDao.setTypePlace(wagon, typePlace);

    }
}
