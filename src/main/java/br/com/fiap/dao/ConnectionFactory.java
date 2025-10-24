package br.com.fiap.dao;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection connection;
    private static final Dotenv dotenv = Dotenv.load();

    public static void closeConnection() {
        try {
            if (!connection.isClosed()){
                connection.close();
            }
        } catch (Exception e){
            System.out.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        try {
            if(connection != null && !connection.isClosed()){
                return connection;
            }
            Class.forName("oracle.jdbc.driver.OracleDriver");

            String url = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");

            if (url == null || user == null || password == null) {
                throw new RuntimeException("As variáveis do Banco não estão configuradas corretamente");
            }

            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao carregar o driver JDBC: " + e.getMessage());
        }
        return connection;
    }
}