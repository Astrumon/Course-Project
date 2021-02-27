package main.tests;

import main.DataSource;
import main.dao.impl.wagon_dao_impl.PlaceDaoImpl;
import main.dao.impl.wagon_dao_impl.TypePlaceDaoImpl;
import main.dao.impl.wagon_dao_impl.WagonDaoImpl;
import main.model.wagon.Place;
import main.model.wagon.TypePlace;
import main.model.wagon.Wagon;

public class TestPlace {
    public static void main(String[] args) {
        TypePlace typePlace = new TypePlace();
        typePlace.setCountSeats(1);
        typePlace.setCountVip(1);
        typePlace.setCountMiddle(1);
        typePlace.setCountLow(2);

        DataSource dataSource = new DataSource();
        dataSource.setUrl(TestTrain.PATH_DB);

        WagonDaoImpl wagonDao = new WagonDaoImpl(dataSource);

        Wagon wagon = new Wagon();
        wagon.setIdWagon(3l);
        wagon.setType(1);


        wagonDao.setTypePlace(wagon, typePlace);
        PlaceDaoImpl placeDao = new PlaceDaoImpl(dataSource);
        Place place = new Place();
        place.setIdPlace(674l);
       // placeDao.delete(place);

    }
}
