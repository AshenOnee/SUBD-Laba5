package lab5;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
////import java.util.ArrayList;
////import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.swing.DefaultComboBoxModel;

//public class ExampleJDBC extends javax.swing.JFrame {
//
//    public static void main2(String[] args) {
//        // 1: Установка параметров подключения
//        String url = "jdbc:postgresql://127.0.0.1:5432/mydb";
//        String name = "postgres";
//        String password = "123456";
//        Connection connection = null;
//        //List<User> users = new ArrayList<>();
//        // insert null value for first default item in comboBox
//        //users.add(null);
//        try {
//            // 2: Выбор драйвера и установка connection
//            Class.forName("org.postgresql.Driver");
//            connection = DriverManager.getConnection(url, name, password);
//            Statement statement = connection.createStatement();
//
//            // 3: Выполняем запрос и получаем ResultSet
//            ResultSet rs = statement.executeQuery("SELECT * FROM user");
//
//            // 4: Разбор ResultSet’a
//            while (rs.next()) {
//                //User user = new User();
////
////                user.setId(rs.getLong("id"));
////                user.setFirstName(rs.getString("firstname"));
////                user.setLastName(rs.getString("lastname"));
////                user.setAge(rs.getLong("age"));
////                if (!rs.wasNull()) {
////                    user.setAge(null);
////                }
////                users.add(user); // добавление очередного юзера в список
//            }
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(ExampleJDBC.class.getName()).log(Level.SEVERE, null,
//                    ex);
//        } finally { // Шаг 5: Закрываем connection
//            if (connection != null) {
//                try {
//                    connection.close(); // Каскадное закрытие statement, resultSet
//                } catch (SQLException ex) {
//                    Logger.getLogger(ExampleJDBC.class.getName()).log(
//                            Level.SEVERE,
//                            null, ex);
//                }
//            }
//        }
////        MainFrame mf = new MainFrame();
////        mf.setVisible(true);
////        DefaultComboBoxModel model = new DefaultComboBoxModel(users.toArray());
////        mf.getjComboBox1().setModel(model);
//    }

//}
