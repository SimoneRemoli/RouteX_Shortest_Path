package com.example.servlets;

import java.sql.*;
import java.util.ArrayList;

public class PercorsiDAO {

    private ArrayList<String> Percorsi_Con_Fermate = new ArrayList<String>();
    private ArrayList<Integer> Percorsi_Codifica = new ArrayList<Integer>();
    private int cambi_linee_metropolitane = 0;
    ArrayList<String> linee = new ArrayList<String>();



    public PercorsiDAO(ArrayList<Integer> a, String city) throws Exception {
        this.Percorsi_Codifica = a;
        connection(Percorsi_Codifica,city);
    }
    public ArrayList<String> getLinee()
    {
        return linee;
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
        String url = "jdbc:mysql://localhost:3306/RouteX"; // Host e nome del database
        String username = "root"; // Username del database
        String password = "ste952r456!"; // Password del database
        Statement stmt = null;
        ResultSet rs = null;
        String fermate = null;
        String linea = null, linea_temp = "", temporanea="";
        boolean check = false, no_pass=false;
        int count_bin = 0;

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

                    if((!linea_temp.equals(linea))&&(i==1))
                    {
                        no_pass = true;
                    }

                    if(check)
                    {
                        if(!linea_temp.equals(linea))
                        {
                            cambi_linee_metropolitane = cambi_linee_metropolitane + 1;
                            check = false;
                            linea_temp = linea;
                        }
                        else {
                            check = false;
                            linea_temp = linea;
                        }
                    }
                    else {
                        if (!(count_bin == 0)) {
                            if (!linea_temp.equals(linea)) {
                                if(no_pass==false)
                                {
                                    check = true;
                                }
                                else
                                {
                                    no_pass = false;
                                    linea_temp = linea;
                                }

                            } else {
                                linea_temp = linea;
                            }
                        } else if (count_bin == 0) {
                            linea_temp = linea;
                            count_bin = count_bin + 1;

                        }
                    }
                    this.Percorsi_Con_Fermate.add(fermate); //linea che consente di stampare i percorsi con fermate (non toccare)
                    this.linee.add(linea);
                }
                    System.out.println("Numero cambi = "+ cambi_linee_metropolitane);


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
        //this.cambi_linee_metropolitane = this.cambi_linee_metropolitane - (this.cambi_linee_metropolitane/2);
        System.out.println("CAMBI LINEE METROPOLITANE in PercorsiDAO.Java = " + cambi_linee_metropolitane);
    }

}
