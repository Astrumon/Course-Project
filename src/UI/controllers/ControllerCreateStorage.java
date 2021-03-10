package UI.controllers;

import data_access.model.wagon.Wagon;
import data_access.model.warehouse.Warehouse;
import data_access.model.warehouse.WarehouseSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import support.AlertGenerator;
import support.ParseId;
import support.WagonManager;
import support.WarehouseManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class ControllerCreateStorage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textFieldNameStorage;

    @FXML
    private Button buttonSaveStorage;

    @FXML
    private Button buttonDeleteStorage;

    @FXML
    private ListView<String> listViewCar;

    @FXML
    private TableView<Wagon> tableCar;

    private WarehouseManager warehouseManager;

    @FXML
    void buttonDeleteStorageAc(ActionEvent event) {

        String nameWarehouse = textFieldNameStorage.getText();

        if (warehouseManager.deleteWarehouse(nameWarehouse)) {
            AlertGenerator.info("Склад успішно видалено");
        } else {
            AlertGenerator.info("Виникла помилка при видаленні вагону");
        }
    }


    private int findEmptyPos() {
        int pos = 0;
        for (WarehouseSet warehouseSet : warehouseManager.getWarehouseSets()) {
            if (warehouseSet.getIdWagon() == 0) {
                pos = warehouseSet.getPosition();
                break;
            }
        }
        return pos;
    }


    private void createWarehouse(String nameWarehouse) {

        if (warehouseManager.getWarehouses().size() == 0) {
            createFirstWarehouse(nameWarehouse);
        }

        int count = 0;
        for (Warehouse warehouse : warehouseManager.getWarehouses()) {
            if (!warehouse.getName().equals(nameWarehouse)) {
                count++;
                if (count == warehouseManager.getWarehouses().size()) {
                    if (warehouseManager.createWarehouse(nameWarehouse)) {
                        AlertGenerator.info("Склад успішно створено");
                    } else {
                        AlertGenerator.error("Виникла помилка при створені складу");
                    }
                }
            }
        }
    }

    private void createFirstWarehouse(String nameWarehouse) {
        if (warehouseManager.createWarehouse(nameWarehouse)) {
            AlertGenerator.info("Склад успішно створено");
        } else {
            AlertGenerator.error("Виникла помилка при створені складу");
        }
    }

    private void addWagon(String nameWarehouse) {

        Wagon wagon = new Wagon();

        for (String nameWagon : getWagonsFromList()) {
            wagon.setIdWagon(ParseId.getLongId(nameWagon, ControllerTableCar.WAGON_PREFIX_NAME));
            wagon.setType(Wagon.PASSENGER_TYPE);
            if (warehouseManager.addWagonToWarehouse(nameWarehouse, wagon, findEmptyPos())) {
                AlertGenerator.info("Вагон успішно додано на склад");
            } else {
                AlertGenerator.error("Виникла помилка при додаванні вагону на склад");
            }
        }
    }

    private List<String> getWagonsFromList() {
        return listViewCar.getSelectionModel().getSelectedItems();
    }

    @FXML
    void buttonSaveStorageAc(ActionEvent event) {
        String nameWarehouse = textFieldNameStorage.getText();

        createWarehouse(nameWarehouse);

        addWagon(nameWarehouse);
    }


    @FXML
    void initialize() {
        assert textFieldNameStorage != null : "fx:id=\"textFieldNameStorage\" was not injected: check your FXML file 'createStorage.fxml'.";
        assert buttonSaveStorage != null : "fx:id=\"buttonSaveStorage\" was not injected: check your FXML file 'createStorage.fxml'.";
        assert buttonDeleteStorage != null : "fx:id=\"buttonDeleteStorage\" was not injected: check your FXML file 'createStorage.fxml'.";
        assert listViewCar != null : "fx:id=\"listViewCar\" was not injected: check your FXML file 'createStorage.fxml'.";

        warehouseManager = new WarehouseManager();

        loadWagonsInfoToListView();
    }

    private void loadWagonsInfoToListView() {
        WagonManager wagonManager = new WagonManager();

        for (Wagon wagon : wagonManager.getWagons()) {
            listViewCar.getItems().addAll(ControllerTableCar.WAGON_PREFIX_NAME + wagon.getIdWagon());
        }
        listViewCar.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }


}
