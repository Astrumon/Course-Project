package sample.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import main.DataSource;
import main.dao.impl.warehouse_dao_impl.WarehouseDaoImpl;
import main.dao.impl.warehouse_dao_impl.WarehouseSetDaoImpl;
import main.model.wagon.Wagon;
import main.model.warehouse.Warehouse;
import main.model.warehouse.WarehouseSet;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTableStorage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Warehouse> tableWarehouse;

    @FXML
    private TableColumn tblNumber;

    @FXML
    private TableColumn tblName;

    @FXML
    private TableColumn tblCountCars;

    private ObservableList<Warehouse> warehouses;



    @FXML
    void initialize() {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(DataSource.PATH);
        WarehouseDaoImpl warehouseDao = new WarehouseDaoImpl(dataSource);


        warehouses = tableWarehouse.getItems();
        warehouses.addAll(warehouseDao.findAll());
        //tblName.setCellValueFactory(new PropertyValueFactory<Warehouse, String>("name"));


        tblName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures cellDataFeatures) {
                String name = ((Warehouse)cellDataFeatures.getValue()).getName();

                return new SimpleStringProperty(name);
            }
        });

        tblNumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures cellDataFeatures) {
                int idNumber = warehouses.indexOf((Warehouse) cellDataFeatures.getValue()) + 1;
                return new SimpleIntegerProperty(idNumber);
            }
        });


        tblCountCars.setCellValueFactory(new PropertyValueFactory<Warehouse, String>("countWagons"));

        tableWarehouse.setItems(warehouses);


    }
}
