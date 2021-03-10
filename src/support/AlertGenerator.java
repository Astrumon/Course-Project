package support;

import javafx.scene.control.Alert;

public class AlertGenerator {

    private static Alert alert = new Alert(Alert.AlertType.NONE);

    public AlertGenerator() {
    }

    public static void info(String text) {
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setTitle("Увага!");
        alert.setContentText(text);
        alert.showAndWait();
    }

    public static void error(String text) {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setTitle("Увага!");
        alert.setContentText(text);
        alert.showAndWait();
    }
}
