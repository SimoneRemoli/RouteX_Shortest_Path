package com.example.servlets;

import java.sql.*;
import java.util.ArrayList;

public class PercorsiDAO {

    private ArrayList<String> Percorsi_Con_Fermate = new ArrayList<String>();
    private ArrayList<Integer> Percorsi_Codifica = new ArrayList<Integer>();
    private ArrayList<String> Sequenze_di_cambiamento = new ArrayList<String>();
    private ArrayList<String> Sequenze_nodi_cruciali = new ArrayList<String>();
    private int cambi_linee_metropolitane = 0;
    private String nome_stazione_cambio = "", ev="";
    private String precedente = "";
    ArrayList<String> linee = new ArrayList<String>();
    private ArrayList<String> in_mezzo = new ArrayList<String>();
    private ArrayList<String> in_mezzo_nomi = new ArrayList<String>();



    public PercorsiDAO(ArrayList<Integer> a, String city) throws Exception {
        this.Percorsi_Codifica = a;
        connection(Percorsi_Codifica,city);
    }
    public ArrayList<String> getSequenze_nodi_cruciali()
    {
        return Sequenze_nodi_cruciali;
    }
    public ArrayList<String> getSequenze_di_cambiamento()
    {
        return Sequenze_di_cambiamento;
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
        boolean check = false, no_pass=false,controllo=false,ci_son_passato=false;
        int count_bin = 0,quanto_ci_passo=0;

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

                    if(((linea.contains("-")))&&(i==0))
                    {
                        controllo = true;
                    }
                    if((!linea_temp.equals(linea))&&(i==1))
                    {
                        no_pass = true;
                    }

                    if(check)
                    {
                        if(!linea_temp.equals(linea))
                        {
                            if(linea.contains("-"))
                            {
                                quanto_ci_passo = quanto_ci_passo + 1;

                               // linea_temp = precedente;
                               // nome_stazione_cambio = fermate;

                                in_mezzo.add(linea);
                                in_mezzo_nomi.add(fermate);
                                check = true;
                                ci_son_passato = true;
                            }
                            else {
                                cambi_linee_metropolitane = cambi_linee_metropolitane + 1;
                                this.Sequenze_di_cambiamento.add(linea_temp);
                                this.Sequenze_di_cambiamento.add(linea);
                                if(quanto_ci_passo == 0)
                                    this.Sequenze_nodi_cruciali.add(nome_stazione_cambio);
                                else
                                {
                                    for(int j=0;j<in_mezzo.size();j++)
                                    {
                                        if(in_mezzo.get(j).contains(linea))
                                        {
                                            this.Sequenze_nodi_cruciali.add(in_mezzo_nomi.get(j));
                                        }
                                    }
                                }
                                in_mezzo.clear();
                                in_mezzo_nomi.clear();
                                check = false;
                                linea_temp = linea;
                                quanto_ci_passo = 0;

                            }
                        }
                        else {

                            check = false;
                            linea_temp = linea;

                            if(ci_son_passato==true)
                            {
                                in_mezzo.clear();
                                in_mezzo_nomi.clear();
                                check = false;
                                linea_temp = linea;
                                ci_son_passato = false;
                            }
                        }
                    }
                    else {
                        if (!(count_bin == 0)) {
                            if (!linea_temp.equals(linea)) {

                                if((i==1)&&(controllo==false))
                                {
                                    check = true;
                                    precedente = linea;
                                    nome_stazione_cambio = fermate; //la aggiungo solo se poi effettivamente rispetta check
                                    no_pass = false;
                                    in_mezzo.add(linea);
                                    in_mezzo_nomi.add(fermate);
                                    ev = linea_temp;
                                }
                                else {
                                    if (no_pass == false) {
                                        check = true;
                                        precedente = linea;
                                        nome_stazione_cambio = fermate; //la aggiungo solo se poi effettivamente rispetta check


                                        in_mezzo.add(linea);
                                        in_mezzo_nomi.add(fermate);
                                        ev = linea_temp;
                                        ci_son_passato = true;


                                    } else {
                                        no_pass = false;
                                        linea_temp = linea;
                                    }
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
