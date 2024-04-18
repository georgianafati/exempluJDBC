package Laborator8;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


class Adaugare_stergere {
    public static void afisare_tabela(Statement statement, String mesaj) {
        String sql ="select * from excursie";
        System.out.println("\n---"
                +mesaj
                +"---");
        try(ResultSet rs =statement.executeQuery(sql)) {
            while (rs.next())
                System.out.println("id_excursie=" + rs.getInt(1) + ", destinatia=" + rs.getString(2) + ", anul="
                        + rs.getInt(3) + ", id_persoana=" + rs.getInt(4));
        } catch (SQLException
                e) {
            e.printStackTrace();
        }
    }public static void adaugare(Connection connection, int id_excursie, String destinatie, int anul, int id_persoana) {
        String sql="insert into excursii values (?,?,?)";
        try(PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setInt(1, id_excursie);
            ps.setString(2, destinatie);
            ps.setInt(3, anul);
            ps.setInt(4, id_persoana);
            int nr_randuri=ps.executeUpdate();
            System.out.println("\nNumar randuri afectate de adaugare="+nr_randuri);
        } catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }
    }
    public static void actualizare(Connection connection,int id_excursie,int anul){
        String sql="update excursii set anul=? where id_excursie=?";
        try(PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setInt(1, id_excursie);
            ps.setInt(2, anul);
            int nr_randuri=ps.executeUpdate();
            System.out.println("\nNumar randuri afectate de modificare="+nr_randuri);
        } catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }
    }public static void stergere(Connection connection,int id_excursie){
        String sql="delete from excursii where id_excursie=?";
        try(PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setInt(1, id_excursie);
            int nr_randuri=ps.executeUpdate();
            System.out.println("\nNumar randuri afectate de modificare="+nr_randuri);
        }
        catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/lab8_schema";
        try {
            Connection connection = DriverManager.getConnection(url, "root", "root");
            Statement statement = connection.createStatement();
            afisare_tabela(statement,"Continut initial");
            adaugare(connection,4,"Elvetia",2019, 3);
            afisare_tabela(statement,"Dupa adaugare");
            actualizare(connection,4,2019);
            afisare_tabela(statement,"Dupa modificare");stergere(connection,4);
            afisare_tabela(statement,"Dupa stergere");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}