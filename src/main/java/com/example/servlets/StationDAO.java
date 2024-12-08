package com.example.servlets;

import java.sql.*;

public class StationDAO {

    int Stazione_di_Partenza, Stazione_di_Arrivo;

    public StationDAO(String startstation, String endstation, String citta) throws Exception {
        connection(startstation,endstation,citta);

    }
    public int getStazione_di_Partenza()
    {
        return Stazione_di_Partenza;
    }
    public int getStazione_di_arrivo()
    {
        return Stazione_di_Arrivo;
    }
    private void connection(String startstation, String endstation, String citta) throws Exception {
        String url = "jdbc:mysql://localhost:3306/RouteX"; // Host e nome del database
        String username = "root"; // Username del database
        String password = "ste952r456!"; // Password del database

        try {
            System.out.println("La città che entra è = " + citta);
            Class.forName("com.mysql.cj.jdbc.Driver"); // Caricamento del driver
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connessione al database riuscita!");
            /*Eseguo una query parametrizzata*/
            String query = "select id from " + citta + " where nome=?";
            //System.out.println("Query =  " + query);
            PreparedStatement pstmt = conn.prepareStatement(query);

            // Primo parametro: startstation
            pstmt.setString(1, startstation);
            ResultSet rs1 = pstmt.executeQuery();
            if (rs1.next()) {
                this.Stazione_di_Partenza = rs1.getInt("id");
            }
            rs1.close();

            // Secondo parametro: endstation
            pstmt.setString(1, endstation);
            ResultSet rs2 = pstmt.executeQuery();
            if (rs2.next()) {
                this.Stazione_di_Arrivo = rs2.getInt("id");
            }
            rs2.close();

            pstmt.close();
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