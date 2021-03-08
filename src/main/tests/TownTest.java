package main.tests;


import main.DataSource;
import main.dao.impl.route_dao_impl.TownDaoImpl;
import main.model.route.Town;

public class TownTest {
    public static final String PATH_DB = "jdbc:sqlite:C:\\Users\\Xiaomi\\IdeaProjects\\Course Project\\database\\railway.db";
    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(PATH_DB);
        TownDaoImpl townDao = new TownDaoImpl(dataSource);


        townDao.insert(new Town("Киев"));
        townDao.insert(new Town("Львов"));
        townDao.insert(new Town("Запорожье"));
        townDao.insert(new Town("Одесса"));

        for (Town town : townDao.findByAll()) {
            System.out.println(town);
        }

        System.out.println();

        townDao.delete(new Town("Одесса"));

        for (Town town : townDao.findByAll()) {
            System.out.println(town);
        }

        System.out.println();

        System.out.println(townDao.findById(1l));

        System.out.println();

        System.out.println(townDao.findByName("Львов"));

    }
}
