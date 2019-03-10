import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;


public class Main extends Application {
    private static Pane root;
    private static ArrayList<GridPane> listScenes = new ArrayList<>();
    private static ArrayList<Integer> listHeight = new ArrayList<>();
    private static ArrayList<Integer> listWidth = new ArrayList<>();
    private static ArrayList<String> listTitles = new ArrayList<>();

    public static int getId_cur() {
        return id_cur;
    }

    private static int id_cur = 0;

    private static Stage st;

    @Override
    public void start(Stage primaryStage) throws Exception {
        st = primaryStage;
        root = FXMLLoader.load(getClass().getResource("FXML/authoriz.fxml"));


        listScenes.add(FXMLLoader.load(getClass().getResource("FXML/authoriz.fxml")));

        listScenes.add(FXMLLoader.load(getClass().getResource("FXML/raschet.fxml")));
        listScenes.add(FXMLLoader.load(getClass().getResource("FXML/reg.fxml")));
        listScenes.add(FXMLLoader.load(getClass().getResource("FXML/zayavka.fxml")));

        listHeight.add(325);   //Авторизація
        listHeight.add(460);   //Розрахунок
        listHeight.add(565);   //Регестрація
        listHeight.add(635);   //Заявка

        listWidth.add(460);
        listWidth.add(750);
        listWidth.add(400);
        listWidth.add(1000);

        listTitles.add("Авторизація 'Courier Service'");
        listTitles.add("Розрахунок");
        listTitles.add("Реєстрація");
        listTitles.add("Створення нової заявки на доставку");

        Scene scene = new Scene(root);
        st.setScene(scene);
        st.setTitle("Авторизація 'Courier Service'");
        st.setResizable(false);
        st.show();


    }

    public static Pane getPane(int id) {
        return listScenes.get(id);
    }

    static void hidePane() {
        st.hide();
    }

    static void setPane(int id) {
        st.hide();
        root.getChildren().remove(listScenes.get(id));
        st.setHeight(listHeight.get(id));
        st.setWidth(listWidth.get(id));
        root.getChildren().add(listScenes.get(id));
        st.setTitle(listTitles.get(id));
        st.show();
        id_cur = id;
    }


}
