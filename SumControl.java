
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;


public class SumControl {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button backMenu;
    @FXML
    private TextField vaga;
    @FXML
    private TextField visota;
    @FXML
    private RadioButton useWeight;
    @FXML
    private RadioButton useFact;
    @FXML
    private Button calculate;
    @FXML
    private TextField dovzhina;
    @FXML
    private Button createNewZay;
    @FXML
    private TextField shirina;
    @FXML
    private Label vartist;

    private void reset() {
        vaga.setText("");
        visota.setText("");
        dovzhina.setText("");
        shirina.setText("");
        vartist.setText("0 грн");
        useWeight.setSelected(true);
    }

    @FXML
    void initialize() {

        ToggleGroup group = new ToggleGroup();
        useWeight.setToggleGroup(group);
        useFact.setToggleGroup(group);
        useWeight.setSelected(true);
        createNewZay.setOnMouseClicked(event -> {
            reset();
            Main.setPane(3);
        });

        backMenu.setOnMouseClicked(event -> {
            Main.hidePane();
            reset();
            MainControl.stage.show();

        });

        calculate.setOnAction(event -> {
            if ((RegControl.validGab(dovzhina.getText())&&RegControl.validGab(shirina.getText())
                  &&RegControl.validGab(visota.getText())) || RegControl.validGab(vaga.getText())) {
                DecimalFormat df = new DecimalFormat("0.00");
                if (useWeight.isSelected()) {
                    Double sum = 29 * (Double.parseDouble(dovzhina.getText()) *
                            Double.parseDouble(shirina.getText()) * Double.parseDouble(visota.getText())) / 4000;
                    vartist.setText("  " + df.format(sum) + " грн");
                } else if (useFact.isSelected()) {
                    Double sum = Double.parseDouble(vaga.getText()) * 7.25;
                    vartist.setText("" + df.format(sum) + " грн");
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Введено некоректні дані!", ButtonType.YES);
                alert.showAndWait();
            }
        });

    }
}

