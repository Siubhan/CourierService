import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import obClasses.tableServ;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MainControl {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private ChoiceBox<String> listStaniv;
    @FXML
    private RadioButton searchOtrym;
    @FXML
    private Button findSearch;
    @FXML
    private Button declineSearch;
    @FXML
    private TextField fieldOtrym;
    @FXML
    private TableView tableDost;
    @FXML
    private TableColumn<tableServ, String> pibOtr;
    @FXML
    private TableColumn<tableServ, String> adrOtr;
    @FXML
    private TableColumn<tableServ, String> stanVidpr;
    @FXML
    private TableColumn<tableServ, String> typVidpr;
    @FXML
    private TableColumn<tableServ, String> dataDost;
    @FXML
    private TableColumn<tableServ, String> dataVidpr;
    @FXML
    private TableColumn<tableServ, Double> vartis;
    @FXML
    private RadioButton searchDate;
    @FXML
    private DatePicker fieldDate;
    @FXML
    private Button calcDost;
    @FXML
    private RadioButton searchStan;
    @FXML
    private Button newZayavka;
    @FXML
    private Button leaveSys;
    static Stage stage;
    private static Parent app;
    private ObservableList<tableServ> list = FXCollections.observableArrayList();
    private ObservableList<tableServ> listfilter = FXCollections.observableArrayList();


    private void reset() {
        searchStan.setSelected(true);
        fieldOtrym.clear();
    }


    private ObservableList<tableServ> getList(ObservableList l) {
        return l;
    }

    private void fill(int id) throws SQLException, ClassNotFoundException { Connection connection = PersonData.getConnection();
        if(id==6){
            String query = "SELECT idrequest,nameRec,addressRec," +
                    "typeDelivery,dateBeg,status,dateEnd,payment  FROM  requestserv";
            Statement st;
            ResultSet rs;
            assert connection != null;
            st = connection.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                list.add(new tableServ(rs.getInt("idrequest"), rs.getString("nameRec"), rs.getString("addressRec"),
                        rs.getString("typeDelivery"), rs.getString("dateBeg"),
                        rs.getString("status"), rs.getString("dateEnd"), rs.getDouble("payment")));
            }
        }
        else {
            String query = "SELECT idrequest,nameRec,addressRec," +
                    "typeDelivery,dateBeg,status,dateEnd,payment  FROM  requestserv WHERE iduser LIKE " + id;
            Statement st;
            ResultSet rs;
            assert connection != null;
            st = connection.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                list.add(new tableServ(rs.getInt("idrequest"), rs.getString("nameRec"), rs.getString("addressRec"),
                        rs.getString("typeDelivery"), rs.getString("dateBeg"),
                        rs.getString("status"), rs.getString("dateEnd"), rs.getDouble("payment")));
            }
        }
    }
    public void fill() throws SQLException, ClassNotFoundException {
        Connection connection = PersonData.getConnection();
        String query = "SELECT idrequest,nameRec,addressRec," +
                "typeDelivery,dateBeg,status,dateEnd,payment  FROM  requestserv ";
        Statement st;
        ResultSet rs;
        assert connection != null;
        st = connection.createStatement();
        rs = st.executeQuery(query);
        while (rs.next()) {
            list.add(new tableServ(rs.getInt("idrequest"), rs.getString("nameRec"), rs.getString("addressRec"),
                    rs.getString("typeDelivery"), rs.getString("dateBeg"),
                    rs.getString("status"), rs.getString("dateEnd"), rs.getDouble("payment")));
        }
    }

    public void fillfilter(int id, int n, String val) throws SQLException, ClassNotFoundException {
        Connection connection = PersonData.getConnection();
        String query;
        if(id==6){
            if (n == 1) {
                query = "SELECT idrequest,nameRec,addressRec," +
                        "typeDelivery,dateBeg,status,dateEnd,payment  FROM  requestserv WHERE " +
                        "status LIKE '" + val + "'";
            } else if (n == 2) {
                query = "SELECT idrequest,nameRec,addressRec," +
                        "typeDelivery,dateBeg,status,dateEnd,payment  FROM" +
                        "  requestserv WHERE dateEnd LIKE '" + val + "'";

            } else {
                query = "SELECT idrequest,nameRec,addressRec," +
                        "typeDelivery,dateBeg,status,dateEnd,payment  FROM " +
                        " requestserv WHERE nameRec LIKE '%" + val + "%'";
            }
        }
        else {
            if (n == 1) {
                query = "SELECT idrequest,nameRec,addressRec," +
                        "typeDelivery,dateBeg,status,dateEnd,payment  FROM  requestserv WHERE (iduser LIKE " + id + ") " +
                        "AND (status LIKE '" + val + "')";
            } else if (n == 2) {
                query = "SELECT idrequest,nameRec,addressRec," +
                        "typeDelivery,dateBeg,status,dateEnd,payment  FROM" +
                        "  requestserv WHERE (iduser like " + id + ")AND (dateEnd LIKE '" + val + "')";

            } else {
                query = "SELECT idrequest,nameRec,addressRec," +
                        "typeDelivery,dateBeg,status,dateEnd,payment  FROM " +
                        " requestserv WHERE (iduser LIKE " + id + ") AND (nameRec LIKE '%" + val + "%')";
            }
        }

        Statement st;
        ResultSet rs;
        assert connection != null;
        st = connection.createStatement();
        rs = st.executeQuery(query);

        while (rs.next()) {
            listfilter.add(new tableServ(rs.getInt("idrequest"), rs.getString("nameRec"), rs.getString("addressRec"),
                    rs.getString("typeDelivery"), rs.getString("dateBeg"),
                    rs.getString("status"), rs.getString("dateEnd"), rs.getDouble("payment")));
        }
    }


    public void setVals(ObservableList sList) {
        pibOtr.setCellValueFactory(new PropertyValueFactory<>("pibOtrym"));
        adrOtr.setCellValueFactory(new PropertyValueFactory<>("adresOtrym"));
        stanVidpr.setCellValueFactory(new PropertyValueFactory<>("stan"));
        typVidpr.setCellValueFactory(new PropertyValueFactory<>("typVidpravk"));
        dataDost.setCellValueFactory(new PropertyValueFactory<>("dateOtrym"));
        dataVidpr.setCellValueFactory(new PropertyValueFactory<>("dateVidpr"));
        vartis.setCellValueFactory(new PropertyValueFactory<tableServ, Double>("vartist"));

        tableDost.setItems(getList(sList));
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        setName();
        if (getId() == 6) {
            fill();
            setVals(list);
        } else {
            fill(getId());
            setVals(list);
        }

        ToggleGroup group = new ToggleGroup(); // group buttons
        searchDate.setToggleGroup(group);
        searchOtrym.setToggleGroup(group);
        searchStan.setToggleGroup(group);
        fieldOtrym.setText("");
        searchStan.setSelected(true);
        fieldDate.setShowWeekNumbers(true);

        leaveSys.setOnAction(e -> {
            reset();//leave system
            stage.close();
            Main.setPane(0);
        });
        newZayavka.setOnAction(e -> {
            reset();//action new request
            stage.close();
            Main.setPane(3);
        });
        calcDost.setOnAction(e -> {
            reset();//action calculate cost
            stage.hide();
            Main.setPane(1);
        });
        findSearch.setOnAction(event -> {
            listfilter.clear();
            if (searchOtrym.isSelected()) {
                if (RegControl.validateName(fieldOtrym.getText())) {
                    try {
                        fillfilter(getId(), 3, fieldOtrym.getText());
                        setVals(listfilter);
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Некоректно " +
                            "введено імя!", ButtonType.YES);
                    alert.showAndWait();
                }
            } else if (searchDate.isSelected()) {
                try {
                    String pattern = "dd.MM.yyyy";
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
                    String val = dateFormatter.format(fieldDate.getValue());
                    fillfilter(getId(), 2, val);
                    setVals(listfilter);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    fillfilter(getId(), 1, listStaniv.getValue());
                    setVals(listfilter);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        declineSearch.setOnAction(event -> {//action decline search
            listfilter.clear();
            setVals(list);
            reset();
        });
        list.addListener((ListChangeListener) change -> setVals(list));

        tableDost.setRowFactory(tv -> {
            TableRow<tableServ> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    tableServ clickedRow = row.getItem();
                    Alert alert = null;

                    try {
                        alert = new Alert(Alert.AlertType.INFORMATION, setFull(clickedRow.getIdDost()), ButtonType.YES);
                        alert.setTitle("Повна заявка");
                        alert.setHeaderText("Повна заявка");

                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    assert alert != null;
                    alert.showAndWait();

            }
            });
            return row ;
        });
    }

    static void setStage() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainControl.class.getResource("FXML/mainwin.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        app = loader.getRoot();
        stage = new Stage();
        stage.setScene(new Scene(app));
        stage.setTitle("Головне меню 'Courier Service'");
        stage.show();


    }

    private String setFull(int id) throws SQLException, ClassNotFoundException {
        Connection connection = PersonData.getConnection();
        String query = "SELECT nameSender,addressSender," +
                "indexSender,phoneSender,packWeight,packHeight" +
                ",packLength,packWidth,isQuick,nameRec,addressRec" +
                ",indexRec,phoneRec,payType,typeDelivery,payment,status" +
                ",dateBeg,dateEnd FROM " +
                    " requestserv WHERE idrequest LIKE " + id;

        Statement st;
        ResultSet rs;
        assert connection != null;
        st = connection.createStatement();
        rs = st.executeQuery(query);
        String result = "";
        while (rs.next()) {
            result+="Відправник: "+rs.getString("nameSender")+"\n";
            result+="Адреса відправника: "+rs.getString("addressSender")+"\n";
            result+="Поштовий індекс відправника: "+rs.getString("indexSender")+"\n";
            result+="Номер телефону відправника: "+rs.getString("phoneSender")+"\n";
            result+="Вага посилки: "+rs.getDouble("packWeight")+" ,кг\n";
            result+="Висота посилки: "+rs.getDouble("packHeight")+" ,см\n";
            result+="Довжина посилки: "+rs.getDouble("packLength")+" ,см\n";
            result+="Ширина посилки: "+rs.getDouble("packWidth")+" ,см\n";
            result+="Чи термінова посилка?: "+rs.getString("isQuick")+"\n";
            result+="Отримувач: "+rs.getString("nameRec")+"\n";
            result+="Адреса отримувача: "+rs.getString("addressRec")+"\n";
            result+="Поштовий індекс отримувача: "+rs.getString("indexRec")+"\n";
            result+="Номер телефону отримувача: "+rs.getString("phoneRec")+"\n";
            result+="Спосіб оплати: "+rs.getString("payType")+"\n";
            result+="Тип доставки: "+rs.getString("typeDelivery")+"\n";
            result+="Вартість: "+rs.getString("payment")+" грн\n";
            result+="Статус: "+rs.getString("status")+"\n";
            result+="Дата початку доставки: "+rs.getString("dateBeg")+"\n";
            result+="Дата закінчення доставки: "+rs.getString("dateEnd");

        }
        return result;
    }
    private static String usName;
    private static int idUs;

    static void setId(int id) {
        idUs = id;
    }

    static void setName(String name) {
        usName = name;
    }

    static int getId() {
        return idUs;
    }

    private static String getName() {
        return usName;
    }

    private static void setName() throws SQLException {
        PersonData.getNameId(AuthorControl.login, getName(), getId());
    }
}
