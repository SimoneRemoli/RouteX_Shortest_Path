package com.example.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StationDAO {

    public StationDAO() throws Exception {
        connection();

    }
    private void connection() throws Exception {
        String url = "jdbc:mysql://sql8.freesqldatabase.com:3306/sql8747953"; // Host e nome del database
        String username = "**"; // Username del database
        String password = "**"; // Password del database

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Caricamento del driver
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connessione al database riuscita!");
            String query = "select * from Roma;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Lettura dei dati
            while (rs.next())
            {
                System.out.println("Stazione: " + rs.getString("nome"));
            }

            rs.close();
            stmt.close();

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


/* su RouteX DB - Remoto
+------+----------+----------+-------+
| id   | nome     | disabile | linea |
+------+----------+----------+-------+
|    0 | Rebibbia | no       | B     |
+------+----------+----------+-------+
 */