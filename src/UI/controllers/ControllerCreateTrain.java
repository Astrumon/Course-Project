package UI.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import data_access.model.train.Train;
import data_access.model.train.TrainSet;
import data_access.model.wagon.Wagon;
import data_access.model.warehouse.Warehouse;
import data_access.model.warehouse.WarehouseSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import support.AlertGenerator;
import support.ParseId;
import support.TrainManager;
import support.WagonManager;

public class ControllerCreateTrain {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textFieldNameTrain;

    @FXML
    private Button buttonSaveTrain;

    @FXML
    private Button buttonDeleteTrain;

    @FXML
    private ListView<String> listViewTrain;

    private TrainManager trainManager;



    @FXML
    void buttonDeleteTrainAc(ActionEvent event) {
        String nameWarehouse = textFieldNameTrain.getText();

        if (trainManager.deleteTrain(nameWarehouse)) {
            AlertGenerator.info("Потяг успішно видалено");
        } else {
            AlertGenerator.info("Виникла помилка при видаленні потягу");
        }
    }

    @FXML
    void buttonSaveTrainAc(ActionEvent event) {
        String nameTrain = textFieldNameTrain.getText();

        createTrain(nameTrain);

        addWagon(nameTrain);
    }

    private void createTrain(String nameTrain) {
        if (trainManager.getTrains().size() == 0) {
            createFirstTrain(nameTrain);
        }

        int count = 0;
        for (Train train : trainManager.getTrains()) {
            if (!train.getName().equals(nameTrain)) {
                count++;
                if (count == trainManager.getTrains().size()) {
                    if (trainManager.createTrain(nameTrain)) {
                        AlertGenerator.info("Потяг успішно створено");
                    } else {
                        AlertGenerator.error("Виникла помилка при створені потягу");
                    }
                }
            }
        }
    }

    private void createFirstTrain(String nameTrain) {
        if (trainManager.createTrain(nameTrain)) {
            AlertGenerator.info("Потяг успішно створено");
        } else {
            AlertGenerator.error("Виникла помилка при створені потягу");
        }
    }

    private void addWagon(String nameTrain) {
        Wagon wagon = new Wagon();

        for (String nameWagon : getWagonsFromList()) {
            wagon.setIdWagon(ParseId.getLongId(nameWagon, ControllerTableCar.WAGON_PREFIX_NAME));
            wagon.setType(Wagon.PASSENGER_TYPE);
            if (trainManager.addWagonToTrain(nameTrain, wagon, findEmptyPos())) {
                AlertGenerator.info("Вагон успішно приєднано до потягу");
            } else {
                AlertGenerator.error("Виникла помилка при приєднанні вагону до потягу");
            }
        }
    }

    private List<String> getWagonsFromList() {
        return listViewTrain.getSelectionModel().getSelectedItems();
    }

    private int findEmptyPos() {
        int pos = 0;
        for (TrainSet trainSet : trainManager.getTrainSets()) {
            if (trainSet.getIdWagon() == 0) {
                pos = trainSet.getPosWagon();
                break;
            }
        }

        return pos;
    }


    @FXML
    void initialize() {
        assert textFieldNameTrain != null : "fx:id=\"textFieldNameTrain\" was not injected: check your FXML file 'createTrain.fxml'.";
        assert buttonSaveTrain != null : "fx:id=\"buttonSaveTrain\" was not injected: check your FXML file 'createTrain.fxml'.";
        assert buttonDeleteTrain != null : "fx:id=\"buttonDeleteTrain\" was not injected: check your FXML file 'createTrain.fxml'.";
        assert listViewTrain != null : "fx:id=\"listViewTrain\" was not injected: check your FXML file 'createTrain.fxml'.";

        trainManager = new TrainManager();

        loadWagonsInfoToListView();
    }

    private void loadWagonsInfoToListView() {
        WagonManager wagonManager = new WagonManager();

        for (Wagon wagon : wagonManager.getWagons()) {
            listViewTrain.getItems().addAll(ControllerTableCar.WAGON_PREFIX_NAME + wagon.getIdWagon());
        }
        listViewTrain.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
