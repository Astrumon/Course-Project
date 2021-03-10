package UI.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import data_access.model.wagon.Wagon;
import UI.FxmlLoader;

public class ControllerBasisOperatorInterface {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane mainPane;

    @FXML
    private GridPane mainTopGridPane;

    @FXML
    private StackPane stackPane;

    @FXML
    private Button buttonCreateStorage;

    @FXML
    private Button buttonCreateCar;

    @FXML
    private Button buttonCreateTrain;

    @FXML
    private Button buttonCreatePath;

    @FXML
    private Button buttonCreateTicket;

    @FXML
    private Button buttonCleanStorage;

    @FXML
    private Button buttonCleanCar;

    @FXML
    private Button buttonCleanTrain;

    @FXML
    private Button buttonCleanPath;

    @FXML
    private Button buttonCleanTicket;

    @FXML
    private TableView<Wagon> tableCar;


    @FXML
    void buttonExit(ActionEvent event) {

    }

    @FXML
    void buttonCar(ActionEvent event) {
        System.out.println("You clicked Button1");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("tableCar");
        mainPane.setCenter(view);

        ObservableList<Node> childs = stackPane.getChildren();
        Node requiredNode = childs.get(childs.indexOf(buttonCreateCar));
        requiredNode.toFront();
    }

    @FXML
    void buttonPath(ActionEvent event) {
        System.out.println("You clicked Button1");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("tablePath");
        mainPane.setCenter(view);

        ObservableList<Node> childs = stackPane.getChildren();
        Node requiredNode = childs.get(childs.indexOf(buttonCreatePath));
        requiredNode.toFront();
    }

    @FXML
    void buttonStorage(ActionEvent event) {
        System.out.println("You clicked Button1");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("tableStorage");
        mainPane.setCenter(view);

        ObservableList<Node> childs = stackPane.getChildren();
        Node requiredNode = childs.get(childs.indexOf(buttonCreateStorage));
        requiredNode.toFront();
    }

    @FXML
    void buttonTicket(ActionEvent event) {
        System.out.println("You clicked Button1");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("tableTicket");
        mainPane.setCenter(view);

        ObservableList<Node> childs = stackPane.getChildren();
        Node requiredNode = childs.get(childs.indexOf(buttonCreateTicket));
        requiredNode.toFront();
    }

    @FXML
    void buttonTrain(ActionEvent event) {
        System.out.println("You clicked Button1");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("tableTrain");
        mainPane.setCenter(view);

        ObservableList<Node> childs = stackPane.getChildren();
        Node requiredNode = childs.get(childs.indexOf(buttonCreateTrain));
        requiredNode.toFront();

    }

    @FXML
    void buttonCleanCarAc(ActionEvent event) {
        System.out.println("You clicked Button1");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("createCar");
        mainPane.setCenter(view);
    }

    @FXML
    void buttonCleanPathAc(ActionEvent event) {

    }

    @FXML
    void buttonCleanStorageAc(ActionEvent event) {
        System.out.println("You clicked Button1");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("createStorage");
        mainPane.setCenter(view);
    }

    @FXML
    void buttonCleanTicketAc(ActionEvent event) {

    }

    @FXML
    void buttonCleanTrainAc(ActionEvent event) {
        System.out.println("You clicked Button1");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("createTrain");
        mainPane.setCenter(view);
    }

    @FXML
    void buttonCreateCarAc(ActionEvent event) {
        System.out.println("You clicked Button1");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("createCar");
        mainPane.setCenter(view);

        ObservableList<Node> childs = stackPane.getChildren();
        Node requiredNode = childs.get(childs.indexOf(buttonCleanCar));
        requiredNode.toFront();

    }



    @FXML
    void buttonCreatePathAc(ActionEvent event) {

    }

    @FXML
    void buttonCreateStorageAc(ActionEvent event) {
        System.out.println("You clicked Button1");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("createStorage");
        mainPane.setCenter(view);

        ObservableList<Node> childs = stackPane.getChildren();
        Node requiredNode = childs.get(childs.indexOf(buttonCleanStorage));
        requiredNode.toFront();
    }

    @FXML
    void buttonCreateTicketAc(ActionEvent event) {

    }

    @FXML
    void buttonCreateTrainAc(ActionEvent event) {
        System.out.println("You clicked Button1");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("createTrain");
        mainPane.setCenter(view);

        ObservableList<Node> childs = stackPane.getChildren();
        Node requiredNode = childs.get(childs.indexOf(buttonCleanTrain));
        requiredNode.toFront();
    }

    @FXML
    void initialize() {
        assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'basisOperatorInterface.fxml'.";
        assert mainTopGridPane != null : "fx:id=\"mainTopGridPane\" was not injected: check your FXML file 'basisOperatorInterface.fxml'.";


    }
}
