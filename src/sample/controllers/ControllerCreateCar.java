package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.DataSource;
import main.dao.impl.wagon_dao_impl.PlaceDaoImpl;
import main.dao.impl.wagon_dao_impl.TypePlaceDaoImpl;
import main.dao.impl.wagon_dao_impl.WagonDaoImpl;
import main.dao.wagon_dao.WagonDao;
import main.model.wagon.Place;
import main.model.wagon.TypePlace;
import main.model.wagon.Wagon;

public class ControllerCreateCar {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textFieldNameCar;

    @FXML
    private TextField textFieldNumberVipSeats;

    @FXML
    private TextField textFieldNumberTopSeats;

    @FXML
    private TextField textFieldNumberLowerSeats;

    @FXML
    private TextField textFieldNumberSittingSeats;

    @FXML
    private Button buttonSaveCar;

    @FXML
    private Button buttonDeleteCar;

    @FXML
    void buttonDeleteCarAc(ActionEvent event) {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(DataSource.PATH);

        WagonDaoImpl wagonDao = new WagonDaoImpl(dataSource);
        Wagon wagon = new Wagon();
        wagon.setIdWagon(Long.parseLong(textFieldNameCar.getText()));
        wagonDao.delete(wagon);

        TypePlaceDaoImpl typePlaceDao = new TypePlaceDaoImpl(dataSource);

        if (typePlaceDao.deleteByIdWagon(wagon.getIdWagon())) {
            alert("Вагон успішно видалений");
        };

    }

    @FXML
    void buttonSaveCarAc(ActionEvent event) {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(DataSource.PATH);
        WagonDaoImpl wagonDao = new WagonDaoImpl(dataSource);
        Long idWagon = Long.parseLong(textFieldNameCar.getText());
        int count = 0;

        for (Wagon wagon : wagonDao.findAll()) {
            if (idWagon.equals(wagon.getIdWagon())) {
                update();
                break;
            }

            if (!idWagon.equals(wagon.getIdWagon())) {
               count++;
               if (count == wagonDao.findAll().size()) {
                   create();
               }
            }
        }

    }

    private void update() {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(DataSource.PATH);

        TypePlace typePlace = new TypePlace();

        typePlace.setCountVip(Integer.parseInt(textFieldNumberVipSeats.getText()));
        typePlace.setCountLow(Integer.parseInt(textFieldNumberLowerSeats.getText()));
        typePlace.setCountMiddle(Integer.parseInt(textFieldNumberTopSeats.getText()));
        typePlace.setCountSeats(Integer.parseInt(textFieldNumberSittingSeats.getText()));
        typePlace.setIdWagon(Long.parseLong(textFieldNameCar.getText()));

        TypePlaceDaoImpl typePlaceDao = new TypePlaceDaoImpl(dataSource);
        typePlaceDao.update(typePlace);

        alert("Інформація о вагоні оновлена");
    }

    private void create() {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(DataSource.PATH);
        WagonDaoImpl wagonDao = new WagonDaoImpl(dataSource);

        TypePlace typePlace = new TypePlace();

        typePlace.setCountVip(Integer.parseInt(textFieldNumberVipSeats.getText()));
        typePlace.setCountLow(Integer.parseInt(textFieldNumberLowerSeats.getText()));
        typePlace.setCountMiddle(Integer.parseInt(textFieldNumberTopSeats.getText()));
        typePlace.setCountSeats(Integer.parseInt(textFieldNumberSittingSeats.getText()));

        Wagon wagon = new Wagon();
        wagon.setIdWagon(Long.parseLong(textFieldNameCar.getText()));
        wagon.setType(1);
        wagonDao.insert(wagon);

        wagonDao.setTypePlace(wagon, typePlace);
        alert("Вагон успішно створений");
    }

    private void alert(String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Увага!");
        alert.setContentText(text);
        alert.showAndWait();
    }

    @FXML
    void initialize() {
        assert textFieldNameCar != null : "fx:id=\"textFieldNameCar\" was not injected: check your FXML file 'createCar.fxml'.";
        assert textFieldNumberVipSeats != null : "fx:id=\"textFieldNumberVipSeats\" was not injected: check your FXML file 'createCar.fxml'.";
        assert textFieldNumberTopSeats != null : "fx:id=\"textFieldNumberTopSeats\" was not injected: check your FXML file 'createCar.fxml'.";
        assert textFieldNumberLowerSeats != null : "fx:id=\"textFieldNumberLowerSeats\" was not injected: check your FXML file 'createCar.fxml'.";
        assert textFieldNumberSittingSeats != null : "fx:id=\"textFieldNumberSittingSeats\" was not injected: check your FXML file 'createCar.fxml'.";
        assert buttonSaveCar != null : "fx:id=\"buttonSaveCar\" was not injected: check your FXML file 'createCar.fxml'.";
        assert buttonDeleteCar != null : "fx:id=\"buttonDeleteCar\" was not injected: check your FXML file 'createCar.fxml'.";

    }
}
