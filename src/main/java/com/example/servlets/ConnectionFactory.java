package com.example.servlets;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {


    private static Connection connection;

    static {

        System.out.println("🟨 ConnectionFactory: blocco static avviato");

        try {
            InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("db.properties");
            if (input == null) {
                System.out.println("❌ File db.properties NON TROVATO nel classpath");
                throw new RuntimeException("File db.properties non trovato!");
            }

            Properties properties = new Properties();
            properties.load(input);
            System.out.println("✅ File db.properties caricato");

            String connection_url = properties.getProperty("CONNECTION_URL");
            String user = properties.getProperty("TRAVELER_USER");
            String pass = properties.getProperty("TRAVELER_PASS");

            System.out.println("🔧 URL: " + connection_url);
            System.out.println("🔧 USER: " + user);
            System.out.println("🔧 PASS: " + pass);

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connection_url, user, pass);

            if (connection != null) {
                System.out.println("✅ Connessione creata con successo");
            } else {
                System.out.println("❌ Connessione è NULL");
            }

        } catch (Exception e) {
            System.out.println("❌ ECCEZIONE durante la creazione della connessione:");
            e.printStackTrace();
        }
    }

    public static Connection gettConnection() throws SQLException, IOException {
        if (connection == null || connection.isClosed()) {
            // apri la connessione
            InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("db.properties");
            if (input == null) {
                System.out.println("❌ File db.properties NON TROVATO nel classpath");
                throw new RuntimeException("File db.properties non trovato!");
            }
            Properties properties = new Properties();
            properties.load(input);
            String connection_url = properties.getProperty("CONNECTION_URL");
            String user = properties.getProperty("TRAVELER_USER");
            String pass = properties.getProperty("TRAVELER_PASS");
            connection = DriverManager.getConnection(connection_url, user, pass);
            if (connection != null) {
                System.out.println("✅ Connessione creata con successo");
            } else {
                System.out.println("❌ Connessione è NULL");
            }
        }
        return connection;
    }


    public static void Cambio_Di_Ruolo(Ruolo ruolo) throws SQLException {

        connection.close();

        try (InputStream input = new FileInputStream("resources/db.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            String connection_url = properties.getProperty("CONNECTION_URL");
            String user = properties.getProperty(ruolo.name() + "_USER");

            System.out.println(user);
            String pass = properties.getProperty(ruolo.name() + "_PASS");

            connection = DriverManager.getConnection(connection_url, user, pass);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }

}
