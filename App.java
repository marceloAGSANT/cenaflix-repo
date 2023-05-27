/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cenaflix;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author marce
 */
public class App {
public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/cenaflix";
        String username = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            // Conexão estabelecida com sucesso!
            // Faça o que precisa ser feito com a conexão...
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado.");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
    
}
