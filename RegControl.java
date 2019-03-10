import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegControl {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField firm;

    @FXML
    private PasswordField passVerify;

    @FXML
    private PasswordField pass;

    @FXML
    private TextField surname;

    @FXML
    private TextField name;

    @FXML
    private Button back;

    @FXML
    private Button createUser;

    @FXML
    private TextField phoneNum;

    @FXML
    private TextField login;

    @FXML
    private TextField email;

    @FXML
    void initialize() {
        createUser.setOnMouseClicked(event -> {
            if (validateEmail(email.getText())&&validateNumb(phoneNum.getText())
                    &&validateName(name.getText())&&validateLog(login.getText())
                    &&validateName(surname.getText())) {
                if (firm.getText().equals("") && pass.getText().equals(passVerify.getText()) && !pass.getText().equals("")) {
                    try {
                        if (PersonData.createUser(login.getText(), surname.getText(), name.getText(), phoneNum.getText(), email.getText(), pass.getText())) {
                            Main.setPane(0);
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } else if (!firm.getText().equals("") && pass.getText().equals(passVerify.getText()) && !pass.getText().equals("")) {
                try {
                    if (PersonData.createUser(login.getText(), surname.getText(), name.getText(), phoneNum.getText(), email.getText(), firm.getText(), pass.getText())) {
                        Main.setPane(0);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (!validateEmail(email.getText())||!validateNumb(phoneNum.getText())
                    ||!validateName(name.getText())||!validateLog(login.getText())
        ||!validateName(surname.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Некорректні дані!", ButtonType.YES);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Не всі поля заповнені!", ButtonType.YES);
                alert.showAndWait();
            }

        });
        back.setOnMouseClicked(event -> Main.setPane(0));

    }
    private static final Pattern VALID_LOGIN =
            Pattern.compile("^[A-Za-z0-9]{3,25}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_NAME =
            Pattern.compile("^[^0-9A-Za-z]{3,45}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_ADDRESS =
            Pattern.compile("^[^A-Za-z()]{15,45}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_GABAR =
            Pattern.compile("^[^A-Za-z]{1,10}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_PHONE =
            Pattern.compile("^\\+[3][8][0]?[0-9]{9}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_INDEX=
            Pattern.compile("^[0-9]{5}$", Pattern.CASE_INSENSITIVE);

    static boolean validGab(String gab) {
        Matcher matcher = VALID_GABAR.matcher(gab);
        return matcher.find();
    }
    private static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
    static boolean validateINDEX(String Ind) {
        Matcher matcher = VALID_INDEX.matcher(Ind);
        return matcher.find();
    }
    static boolean validateNumb(String numStr) {
        Matcher matcher = VALID_PHONE.matcher(numStr);
        return matcher.find();
    }
    static boolean validateName(String name) {
        Matcher matcher = VALID_NAME.matcher(name);
        return matcher.find();
    }
    private static boolean validateLog(String logi) {
        Matcher matcher = VALID_LOGIN.matcher(logi);
        return matcher.find();
    }
    static boolean validAddress(String adr) {
        Matcher matcher = VALID_ADDRESS.matcher(adr);
        return matcher.find();
    }
}

