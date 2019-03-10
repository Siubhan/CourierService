import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;
import java.util.ArrayList;

class PersonData {


    static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/courierservise", "root", "root");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static void getNameId(String logn, String name, int id) throws SQLException {
        Connection connection = null;
        connection = getConnection();
        String query = "SELECT login,iduser,name FROM  users ";
        Statement st;
        ResultSet rs;
        assert connection != null;
        st = connection.createStatement();
        rs = st.executeQuery(query);
        while (rs.next()) {
            if (logn.equals(rs.getString("login"))) {
                MainControl.setName(rs.getString("name"));
                MainControl.setId(rs.getInt("iduser"));
                break;
            }
        }
        connection.close();
    }

    static boolean getAccessPerson(String logn, String pasw) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        String query = "SELECT login,password FROM  users ";
        Statement st;
        ResultSet rs;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                if (logn.equals(rs.getString("login"))
                        && pasw.equals(rs.getString("password"))) {
                    connection.close();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert connection != null;
        connection.close();
        return false;
    }

    private static int checkValid(String login, String email) throws ClassNotFoundException {
        Connection connection = getConnection();
        String query = "SELECT login,email FROM  users ";
        Statement st;
        ResultSet rs;
        try {
            assert connection != null;
            st = connection.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                if (login.equals(rs.getString("login"))) return 1;
                if (email.equals(rs.getString("email"))) return 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 3;
    }

    static boolean createUser(String logn, String surn,
                              String nam, String telph,
                              String emal, String passw) throws SQLException, ClassNotFoundException {
        int result = checkValid(logn, emal);

        if (result == 1) {
        } else if (result == 2) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Користувач з таким e-mail'ом вже існує!", ButtonType.YES);
            alert.showAndWait();
        } else {
            Connection connection = getConnection();
            String query = "INSERT INTO `users`(`login`, `surname`, `name`,`phoneNumber`, " +
                    "`email`, `password`) VALUES ('" + logn + "','" + surn + "','" + nam +
                    "','" + telph + "','" + emal + "','" + passw + "')";
            Statement st;
            assert connection != null;
            st = connection.createStatement();
            st.executeUpdate(query);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Користувача створено!", ButtonType.YES);
            alert.showAndWait();
            return true;
        }
        return false;
    }

    static boolean createUser(String logn, String surn,
                              String nam, String telph,
                              String emal, String firm, String passw) throws SQLException, ClassNotFoundException {
        int result = checkValid(logn, emal);

        if (result == 1) {
        } else if (result == 2) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Користувач з таким e-mail'ом вже існує!", ButtonType.YES);
            alert.showAndWait();
        } else {
            Connection connection = getConnection();
            String query = "INSERT INTO `users`(`login`, `surname`, `name`,`phoneNumber`, " +
                    "`email`,`firm`, `password`) VALUES ('" + logn + "','" + surn + "','"
                    + nam + "','" + telph + "','" + emal + "','" + firm + "','" + passw + "')";
            Statement st;
            assert connection != null;
            st = connection.createStatement();
            st.executeUpdate(query);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Користувача створено!", ButtonType.YES);
            alert.showAndWait();
            return true;
        }
        return false;
    }

    static void createReq(ArrayList<String> req) throws SQLException, ClassNotFoundException {
       Connection connection = getConnection();
        String query = "INSERT INTO `courierservise`.`requestserv` " +
                "(`iduser`, `nameSender`, `addressSender`, `indexSender`, `phoneSender`," +
                " `packWeight`, `packHeight`, `packLength`, `packWidth`, `isQuick`," +
                " `nameRec`, `addressRec`, `indexRec`, `phoneRec`, `payType`, `typeDelivery`,`payment`,`dateBeg`)" +
                " VALUES ('"+MainControl.getId() +"', '"+req.get(0) +"', '"+req.get(1) +"', '"+
                req.get(2)+"', '"+req.get(3)+"', '"+ Double.parseDouble(req.get(4))+"', '"
                + Double.parseDouble(req.get(5))+"', '"+ Double.parseDouble(req.get(6)) +
                "', '"+Double.parseDouble(req.get(7)) +"', '"+ req.get(8)+"', '"+ req.get(9)+"', '"+req.get(10)
                +"', '"+req.get(11)+"', '"+req.get(12) +"', '"+req.get(13) +"', '"+req.get(14)
                +"', '"+Double.parseDouble(req.get(15)) +"', '"+req.get(16)+"')";
        Statement st;
        assert connection != null;
        st = connection.createStatement();
        st.executeUpdate(query);
        connection.close();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Заявку створено!", ButtonType.YES);
        alert.showAndWait();
    }


}

