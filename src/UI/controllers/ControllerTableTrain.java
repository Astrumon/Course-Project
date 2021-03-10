package UI.controllers;

import data_access.model.train.Train;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import support.NumberIDGenerator;
import support.TrainManager;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTableTrain {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView tableTrain;

    @FXML
    private TableColumn tblNumber;

    @FXML
    private TableColumn tblName;

    @FXML
    private TableColumn tblCountWagons;

    private ObservableList<Train> trains;

    private TrainManager trainManager;

    @FXML
    void initialize() {
        fillTable();
    }

    public void fillTable() {
        trainManager = new TrainManager();

        trains = tableTrain.getItems();
        trains.addAll(trainManager.getTrains());

        tblName.setCellValueFactory(new PropertyValueFactory<Train, String>("name"));

        tblNumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures cellDataFeatures) {
                return new SimpleIntegerProperty(NumberIDGenerator.generate(trains, cellDataFeatures));
            }
        });

        tblCountWagons.setCellValueFactory(new PropertyValueFactory<Train, String>("countWagon"));


        tableTrain.setItems(trains);
    }
}
