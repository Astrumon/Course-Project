package UI.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import data_access.DataSource;
import data_access.dao.impl.wagon_dao_impl.WagonDaoImpl;
import data_access.model.wagon.Wagon;
import support.NumberIDGenerator;
import support.WagonManager;
import support.WarehouseManager;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTableCar {

    @FXML
    private BorderPane mainPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Wagon> tableCar;

    @FXML
    private TableColumn tblName;

    @FXML
    private TableColumn tblNumber;

    @FXML
    private TableColumn tblCount;

    public static final String WAGON_PREFIX_NAME = "Вагон№ ";

    private WagonManager wagonManager;


    private ObservableList<Wagon> wagons;

    @FXML
    void initialize() {
        fillTable();
    }

    public void fillTable() {
        wagonManager = new WagonManager();

        wagons = tableCar.getItems();
        wagons.addAll(wagonManager.getWagons());

        tblName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures cellDataFeatures) {
                Wagon wagon = (Wagon) cellDataFeatures.getValue();
                return new SimpleStringProperty(WAGON_PREFIX_NAME + wagon.getIdWagon());
            }
        });

        tblCount.setCellValueFactory(new PropertyValueFactory<Wagon, String>("countSeats"));


        tblNumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures cellDataFeatures) {
                return new SimpleIntegerProperty(NumberIDGenerator.generate(wagons, cellDataFeatures));
            }
        });

        tableCar.setItems(wagons);
    }

    public void clickToEdit() {
        tableCar.setRowFactory(tv -> {
            TableRow<Wagon> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Wagon rowData = row.getItem();
                    System.out.println(rowData);
//                  FxmlLoader object = new FxmlLoader();
//                    Pane view = object.getPage("createCar");
//                   BorderPane borderPane = new BorderPane();
//                   borderPane.setId("manePane");
//                   borderPane.setCenter(view);
                    //TODO переход на создание/редактирование
                }
            });
            return row;
        });
    }
}





