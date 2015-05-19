package sample.Functionality;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.User;

import java.sql.*;

/**
 * Created by IamFabulous on 5/18/2015.
 */
public class logic {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/new_schema";
    static final String USER = "root";
    static final String PASS = "root";

    private ObservableList<User> usersData = FXCollections.observableArrayList();

    // подготавливаем данные для таблицы
    public ObservableList<User> initData() {

        Connection conn = null;
        Statement stmt = null;
        String sql = "SELECT id,info,phone FROM new_schema.table";
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                User s = new User(rs.getInt("id"), rs.getString("info"), rs.getString("phone"));
                usersData.add(s);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersData;
    }
    public void delete(int index){
        Connection conn = null;
        PreparedStatement prstmt = null;
        String sql = "DELETE FROM new_schema.table WHERE id = ?";
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            prstmt= conn.prepareStatement(sql);
            prstmt.setInt(1, index);
            prstmt.executeUpdate();

            prstmt.close();
            conn.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void deleteAllUsers(){
        Connection conn = null;
        Statement stmt = null;
        String sql = "TRUNCATE TABLE new_schema.table";
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void createAddPreparedStatement(String sql, String info, String phone){
        Connection conn = null;
        PreparedStatement prstmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            prstmt = conn.prepareStatement(sql);
            prstmt.setString(1, info);
            prstmt.setString(2, phone);
            prstmt.executeUpdate();

            prstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
