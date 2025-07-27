package com.example.servlets;

import Model.Credentials;
import Exception.DAOException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class UtenteDAO {
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

    public static Connection gettConnection() throws SQLException {
        return connection;
    }

    public void save(Credentials cred) throws DAOException, SQLException {


        try (Connection conn = ConnectionFactory.gettConnection()){
            String sp = "{ CALL routex.register_user(?, ?, ?, ?, ?, ?, ?, ?) }";
            CallableStatement cs = conn.prepareCall(sp);

            cs.setString(1, cred.getNome());
            cs.setString(2, cred.getCognome());
            cs.setString(3, cred.getCodiceFiscale());
            cs.setString(4, cred.getPassword());
            cs.setString(5, cred.getEmail());
            cs.setDate(6, cred.getDataDiNascita());
            cs.setBoolean(7, cred.getDisabile());
            cs.setInt(8, cred.getPermessi().getId());

            cs.execute();

        } catch (Exception e) {
            throw new DAOException("Errore durante la registrazione utente: " + e.getMessage());
        }
    }
}
