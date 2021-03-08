package sample.controllers;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.DataSource;
import main.dao.impl.wagon_dao_impl.WagonDaoImpl;
import main.dao.impl.warehouse_dao_impl.WarehouseDaoImpl;
import main.dao.impl.warehouse_dao_impl.WarehouseSetDaoImpl;
import main.model.wagon.Wagon;
import main.model.warehouse.Warehouse;
import main.model.warehouse.WarehouseSet;

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

    private String wagonName = "Вагон№ ";

    @FXML
    void buttonDeleteStorageAc(ActionEvent event) {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(DataSource.PATH);
        WarehouseDaoImpl warehouseDao = new WarehouseDaoImpl(dataSource);

        Warehouse warehouse = new Warehouse();
        warehouse.setName(textFieldNameStorage.getText());

        warehouseDao.deleteByName(warehouse);
        alert("Склад успішно видалено");
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




//        warehouseDao.insert(warehouse);
//        pos = 0;
//        alert("Склад успішно створено");
        if (warehouseDao.findAll().size() == 0) {
            warehouseDao.insert(warehouse);
            alert("Склад успішно створено");
        }
        int count = 0;
        for (Warehouse warehouse1 : warehouseDao.findAll()) {

            if (!warehouse1.getName().equals(warehouse.getName())) {
                count++;
                if (count == warehouseDao.findAll().size() ) {
                    warehouseDao.insert(warehouse);
                    alert("Склад успішно створено");
                }
            }
        }

        ObservableList<String> wagons;
        wagons = listViewCar.getSelectionModel().getSelectedItems();
        Wagon wagon = new Wagon();

        for (String nameWagon : wagons) {
            wagon.setIdWagon(Long.parseLong(nameWagon.substring(wagonName.length())));
            wagon.setType(1);
            warehouseSetDao.addWagon(warehouse.getName(), wagon, pos);
        }
    }

    private void alert(String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Увага!");
        alert.setContentText(text);
        alert.showAndWait();
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
            listViewCar.getItems().addAll(wagonName + wagon.getIdWagon());
        }
        listViewCar.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);



    }


}
