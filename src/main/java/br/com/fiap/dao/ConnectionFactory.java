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

                String url = dotenv.get("DB_URL");
                String user = dotenv.get("DB_USER");
                String password = dotenv.get("DB_PASSWORD");

                if (url == null || user == null || password == null) {
                    throw new IllegalStateException("As variáveis de ambiente DB_URL, DB_USER ou DB_PASSWORD não estão definidas no arquivo .env.");
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
