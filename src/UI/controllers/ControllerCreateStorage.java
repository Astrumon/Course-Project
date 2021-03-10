package UI.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import data_access.DataSource;
import data_access.dao.impl.wagon_dao_impl.WagonDaoImpl;
import data_access.dao.impl.warehouse_dao_impl.WarehouseDaoImpl;
import data_access.dao.impl.warehouse_dao_impl.WarehouseSetDaoImpl;
import data_access.model.wagon.Wagon;
import data_access.model.warehouse.Warehouse;
import data_access.model.warehouse.WarehouseSet;
import support.AlertGenerator;
import support.ParseId;

import java.net.URL;
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


    @FXML
    void buttonDeleteStorageAc(ActionEvent event) {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(DataSource.PATH);
        WarehouseDaoImpl warehouseDao = new WarehouseDaoImpl(dataSource);

        Warehouse warehouse = new Warehouse();
        warehouse.setName(textFieldNameStorage.getText());

        warehouseDao.deleteByName(warehouse);
        AlertGenerator.info("Склад успішно видалено");
    }


    @FXML
    void buttonSaveStorageAc(ActionEvent event) {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(DataSource.PATH);
        WarehouseDaoImpl warehouseDao = new WarehouseDaoImpl(dataSource);
        int pos = 0;
        Warehouse warehouse = new Warehouse();
        warehouse.setName(textFieldNameStorage.getText());
        warehouse.setCapacity(100);
        WarehouseSetDaoImpl warehouseSetDao = new WarehouseSetDaoImpl(dataSource);
        for (WarehouseSet warehouseSet : warehouseSetDao.findAll()) {
            if (warehouseSet.getIdWagon() == 0) {
                pos = warehouseSet.getPosition();
            }
        }


        if (warehouseDao.findAll().size() == 0) {
            warehouseDao.insert(warehouse);
            AlertGenerator.info("Склад успішно створено");
        }
        int count = 0;
        for (Warehouse warehouse1 : warehouseDao.findAll()) {

            if (!warehouse1.getName().equals(warehouse.getName())) {
                count++;
                if (count == warehouseDao.findAll().size()) {
                    warehouseDao.insert(warehouse);
                    AlertGenerator.info("Склад успішно створено");
                }
            }
        }

        ObservableList<String> wagons;
        wagons = listViewCar.getSelectionModel().getSelectedItems();
        Wagon wagon = new Wagon();

        for (String nameWagon : wagons) {
            wagon.setIdWagon(ParseId.getLongId(nameWagon, ControllerTableCar.WAGON_PREFIX_NAME));
            wagon.setType(1);
            warehouseSetDao.addWagon(warehouse.getName(), wagon, pos);
        }
    }


    @FXML
    void initialize() {
        assert textFieldNameStorage != null : "fx:id=\"textFieldNameStorage\" was not injected: check your FXML file 'createStorage.fxml'.";
        assert buttonSaveStorage != null : "fx:id=\"buttonSaveStorage\" was not injected: check your FXML file 'createStorage.fxml'.";
        assert buttonDeleteStorage != null : "fx:id=\"buttonDeleteStorage\" was not injected: check your FXML file 'createStorage.fxml'.";
        assert listViewCar != null : "fx:id=\"listViewCar\" was not injected: check your FXML file 'createStorage.fxml'.";

        DataSource dataSource = new DataSource();
        dataSource.setUrl(DataSource.PATH);
        WagonDaoImpl wagonDao = new WagonDaoImpl(dataSource);
        for (Wagon wagon : wagonDao.findAll()) {
            listViewCar.getItems().addAll(ControllerTableCar.WAGON_PREFIX_NAME + wagon.getIdWagon());
        }
        listViewCar.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }


}
