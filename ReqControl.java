import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;

import javafx.scene.control.*;

public class ReqControl {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField nameDest;
    @FXML
    private TextField indexDest;
    @FXML
    private TextField addressSender;
    @FXML
    private TextField visota;
    @FXML
    private TextField phoneSender;
    @FXML
    private Button back;
    @FXML
    private TextField dovzhina;
    @FXML
    private TextField vaga;
    @FXML
    private TextField phoneDest;
    @FXML
    private Button sendReq;
    @FXML
    private TextField addressDest;
    @FXML
    private ChoiceBox<String> chosePaym;
    @FXML
    private ChoiceBox<String> choseDeliv;
    @FXML
    private TextField nameSender;
    @FXML
    private TextField indexSender;
    @FXML
    private CheckBox isHurry;
    @FXML
    private Button toCalc;
    @FXML
    private TextField shirina;
    @FXML
    private Label vartist;
    private double sum;
    private void reset() {
        shirina.setText("");
        dovzhina.setText("");
        visota.setText("");
        vaga.setText("");
        phoneDest.setText("");
        phoneSender.setText("");
        nameDest.setText("");
        nameSender.setText("");
        indexSender.setText("");
        indexDest.setText("");
        addressDest.setText("");
        addressSender.setText("");
        isHurry.setSelected(false);
        chosePaym.setValue("Готівка");
        choseDeliv.setValue("Кур`єром");
        vartist.setText("0 грн");
    }

    @FXML
    void initialize() {

        back.setOnMouseClicked(event -> {
            Main.hidePane();
            reset();
            MainControl.setStage();
        });
        toCalc.setOnMouseClicked(event -> {
            reset();
            Main.setPane(1);
        });

        shirina.textProperty().addListener((observable, oldValue, newValue) -> setPay());
        dovzhina.textProperty().addListener((observable, oldValue, newValue) -> setPay());
        visota.textProperty().addListener((observable, oldValue, newValue) -> setPay());
        vaga.textProperty().addListener((observable, oldValue, newValue) -> setPay());
        choseDeliv.setOnAction(event -> setPay());
        isHurry.textProperty().addListener((observable, oldValue, newValue) -> setPay());

        sendReq.setOnMouseClicked(event -> {
            if (RegControl.validateNumb(phoneSender.getText())&&RegControl.validateNumb(phoneDest.getText())&&
                    RegControl.validateName(nameSender.getText())&& RegControl.validateName(nameDest.getText())&&
                    RegControl.validateINDEX(indexSender.getText())&& RegControl.validateINDEX(indexDest.getText())&&
                    RegControl.validGab(dovzhina.getText())&&RegControl.validGab(shirina.getText())
                    &&RegControl.validGab(vaga.getText())&&RegControl.validGab(visota.getText())
                    &&RegControl.validAddress(addressDest.getText())&&RegControl.validAddress(addressDest.getText())) {
                ArrayList<String> request = new ArrayList<>();
                request.add(nameSender.getText());
                request.add(addressSender.getText());
                request.add(indexSender.getText());
                request.add(phoneSender.getText());
                request.add(vaga.getText());
                request.add(visota.getText());
                request.add(dovzhina.getText());
                request.add(shirina.getText());
                request.add(isHurry.isSelected() ? "Так" : "Ні");
                request.add(nameDest.getText());
                request.add(addressDest.getText());
                request.add(indexSender.getText());
                request.add(phoneDest.getText());
                request.add(chosePaym.getValue());
                request.add(choseDeliv.getValue());
                request.add(String.valueOf(sum));
                request.add(DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDate.now()));

                try {
                    PersonData.createReq(request);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Main.hidePane();
                reset();
                MainControl.setStage();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR, "Введено некоректні дані!", ButtonType.YES);
                alert.showAndWait();
            }
        });

    }

    private void setPay() {
        if (!vaga.getText().isEmpty() && !shirina.getText().isEmpty()
                && !dovzhina.getText().isEmpty() && !visota.getText().isEmpty()
                ) { sum =Math.round ((Double.parseDouble(vaga.getText()) * 8.25) + (Double.parseDouble(dovzhina.getText()) *
                    Double.parseDouble(shirina.getText()) * Double.parseDouble(visota.getText())) / 4000);
                if (isHurry.isSelected())sum+=20;
                if(choseDeliv.getValue().equals("Кур`єром"))sum+=20;
            DecimalFormat df = new DecimalFormat("0.00");
            vartist.setText("" + df.format(sum) + " грн");
        }
    }
}
