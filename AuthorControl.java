import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;


public class AuthorControl {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private PasswordField passField;
    @FXML
    private TextField logField;
    @FXML
    private Button newUser;
    @FXML
    private Button enterSys;
    public static Stage st;
    @FXML
    void initialize() {
        newUser.setOnMouseClicked(event -> {
            passField.setText("");
            logField.setText("");
            Main.setPane(2);
        });

        enterSys.setOnMouseClicked(event -> {
            //proverka na polzovatelya
            try {
                if (logField.getText().equals("") && passField.getText().equals("")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Введіть " +
                            "логін і пароль!", ButtonType.YES);
                    alert.showAndWait();
                } else if (PersonData.getAccessPerson(logField.getText().toLowerCase(), passField.getText())) {
                    login = logField.getText().toLowerCase();
                    Main.hidePane();
                    MainControl.setStage();
                    passField.clear();
                    logField.clear();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Невірно " +
                            "введено логін або пароль!", ButtonType.YES);
                    alert.showAndWait();

                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }


        });

    }

    static String login;
}
