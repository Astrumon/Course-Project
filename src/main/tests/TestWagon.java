package main.tests;

import main.DataSource;
import main.dao.impl.wagon_dao_impl.WagonDaoImpl;
import main.model.wagon.Wagon;

public class TestWagon {
    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(TestTrain.PATH_DB);

        Wagon wagon = new Wagon();
        wagon.setIdWagon(1l);
        wagon.setType(1);


        WagonDaoImpl wagonDao = new WagonDaoImpl(dataSource);
        System.out.println(wagonDao.insert(wagon));
        //wagon.setId(1l);
       //wagonDao.delete(wagon);

    }
}
