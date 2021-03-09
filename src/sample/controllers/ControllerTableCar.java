package sample.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import main.DataSource;
import main.dao.impl.wagon_dao_impl.WagonDaoImpl;
import main.model.wagon.Wagon;
import sample.FxmlLoader;

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


    private ObservableList<Wagon> wagons;

    @FXML
    void initialize() {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(DataSource.PATH);
        WagonDaoImpl wagonDao = new WagonDaoImpl(dataSource);

        wagons = tableCar.getItems();
        wagons.addAll(wagonDao.findAll());

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
                return new SimpleIntegerProperty(wagons.indexOf((Wagon) cellDataFeatures.getValue()) + 1);
            }
        });

        tableCar.setItems(wagons);

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





