package com.example.servlets;

import java.sql.*;
import java.util.ArrayList;

public class PercorsiDAO {

    private ArrayList<String> Percorsi_Con_Fermate = new ArrayList<String>();
    private ArrayList<Integer> Percorsi_Codifica = new ArrayList<Integer>();
    private int cambi_linee_metropolitane = -1;


    public PercorsiDAO(ArrayList<Integer> a, String city) throws Exception {
        this.Percorsi_Codifica = a;
        connection(Percorsi_Codifica,city);
    }
    public  ArrayList<String> getPercorsi_Con_Fermate()
    {
        return Percorsi_Con_Fermate;
    }
    public int getCambi_linee_metropolitane()
    {
        return this.cambi_linee_metropolitane;
    }
    private void connection(ArrayList<Integer> Percorsi_Codifica,String city) throws Exception
    {
        String url = "jdbc:mysql://sql8.freesqldatabase.com:3306/sql8747953"; // Host e nome del database
        String username = "sql8747953"; // Username del database
        String password = "egM4kA6PMB"; // Password del database
        Statement stmt = null;
        ResultSet rs = null;
        String fermate = null;
        String linea = null, linea_temp = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Caricamento del driver
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connessione al database riuscita!");

            for (int i = 0; i < Percorsi_Codifica.size(); i++)
            {
               // System.out.println("Fermata da attraversare nel dao " + i + ": " + Percorsi_Codifica.get(i));
                String query = "select nome,linea from "+city+" where id="+Percorsi_Codifica.get(i).toString();
                //System.out.println("La query = "+query);
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    fermate = rs.getString("nome");
                    linea = rs.getString("linea");

                    if(!linea.equals(linea_temp)) cambi_linee_metropolitane = cambi_linee_metropolitane + 1;

                    linea_temp = linea;
                    this.Percorsi_Con_Fermate.add(fermate);
                }
            }
            rs.close();
            stmt.close();
            conn.close();
          //  String query = "select id from " + citta + " where nome=?";
            //System.out.println("Query =  " + query);
           // Statement stmt = conn.createStatement();
          //  ResultSet rs = stmt.executeQuery(query);

            // Lettura dei dati
          /*  while (rs.next())
            {
                System.out.println("Nome: " + rs.getString("nome"));
            }

            // Chiusura della connessione
            rs.close();
            stmt.close();
            conn.close();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.cambi_linee_metropolitane = this.cambi_linee_metropolitane - (this.cambi_linee_metropolitane/2);
        System.out.println("CAMBI LINEE METROPOLITANE in PercorsiDAO.Java = " + cambi_linee_metropolitane);
    }

}
