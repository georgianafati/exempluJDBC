package Exercitiulab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


class Exercitiu_excursie {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/lab8_schema";
        String sql ="select * from excursii";
        Connection connection= DriverManager.getConnection(url, "root", "root");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next())
            System.out.println("id_excursie=" + rs.getInt("Id_excursie") + ", id_persoana=" + rs.getInt("Id_persoana") + ", destinatia= "
                    + rs.getString("destinatia") + ", anul=" + rs.getInt("anul"));
        connection.close();
        statement.close();
        rs.close();
    }
}

