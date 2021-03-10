package UI.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

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

    @FXML
    void buttonDeleteTrainAc(ActionEvent event) {

    }

    @FXML
    void buttonSaveTrainAc(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert textFieldNameTrain != null : "fx:id=\"textFieldNameTrain\" was not injected: check your FXML file 'createTrain.fxml'.";
        assert buttonSaveTrain != null : "fx:id=\"buttonSaveTrain\" was not injected: check your FXML file 'createTrain.fxml'.";
        assert buttonDeleteTrain != null : "fx:id=\"buttonDeleteTrain\" was not injected: check your FXML file 'createTrain.fxml'.";
        assert listViewTrain != null : "fx:id=\"listViewTrain\" was not injected: check your FXML file 'createTrain.fxml'.";
        listViewTrain.getItems().addAll("asd","asdas","sadasd","ashfashkfhka","aasfasasf0","jashdskjhd");
        listViewTrain.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
