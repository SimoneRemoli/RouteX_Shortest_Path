package com.example.servlets;

import java.sql.*;
import java.util.ArrayList;

public class PercorsiDAO {

    private ArrayList<String> Percorsi_Con_Fermate = new ArrayList<String>();
    private ArrayList<Integer> Percorsi_Codifica = new ArrayList<Integer>();
    private ArrayList<String> Sequenze_di_cambiamento = new ArrayList<String>();
    private ArrayList<String> Sequenze_nodi_cruciali = new ArrayList<String>();
    private ArrayList<String> lista_appoggio = new ArrayList<String>();
    private ArrayList<String> nome_cambio = new ArrayList<String>();
    private ArrayList<String> cambi = new ArrayList<String>();
    private ArrayList<String> cambi_iniziali = new ArrayList<String>();
    private ArrayList<String> cambi_iniziali_linee = new ArrayList<String>();


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
        String linea = null, linea_temp = "", temporanea="", da_raggiungere="",temp="",successivo="",success="";
        boolean check = false, no_pass=false,controllo=false,ci_son_passato=false,stopping=false,ancora=false,uno=false;
        int count_bin = 0,quanto_ci_passo=0, con=0,contatore=0,count=0,conta=0;

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
                        cambi_iniziali.add(fermate);
                        cambi_iniziali_linee.add(linea);
                        uno=true;
                    }

                    if(((linea.contains("-")))&&(i==1))
                    {
                        no_pass = true;
                        cambi_iniziali.add(fermate);
                        cambi_iniziali_linee.add(linea);
                        stopping = false;
                        if(uno)
                            ancora = true;

                    }
                    else if((!linea_temp.equals(linea))&&(i==1))
                    {
                        no_pass = true;
                    }
                    if((stopping==false)&&(ancora==true)) {
                        if (i > 1) {
                            if ((linea.contains("-"))) {
                                stopping = false;
                                no_pass = true;
                                cambi_iniziali.add(fermate);
                                cambi_iniziali_linee.add(linea);
                                ancora=true;
                            } else {
                                stopping = true;
                                success = linea;
                                no_pass = true;
                            }
                        }
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

//nuovo
                                if(i==Percorsi_Codifica.size()-1)
                                {
                                    for(int k=0;k<in_mezzo.size();k++)
                                    {
                                        String[] appoggio = in_mezzo.get(k).split("-");
                                        for (String parola : appoggio)
                                        {
                                            if(!parola.equals(ev))
                                            {
                                                conta = conta + 1;
                                                if(conta == appoggio.length)
                                                {
                                                    cambi_linee_metropolitane = cambi_linee_metropolitane + 1;
                                                    this.Sequenze_nodi_cruciali.add(in_mezzo_nomi.get(k-1));
                                                    this.Sequenze_di_cambiamento.add(ev);
                                                    for(int index=0;index<in_mezzo.size()-1;index++)
                                                    {
                                                        String[] parolina = in_mezzo.get(index).split("-");
                                                        for(String p:parolina)
                                                        {
                                                            if(!p.equals(ev))
                                                            {
                                                                String par = p;
                                                                String[] parolina2 = in_mezzo.get(index+1).split("-");
                                                                for(String g:parolina2)
                                                                {
                                                                    if(par.equals(g))
                                                                    {
                                                                        this.Sequenze_di_cambiamento.add(par);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            else
                                            {
                                                conta = 0;
                                            }
                                        }


                                    }
                                }


                            }
                            else {
                                cambi_linee_metropolitane = cambi_linee_metropolitane + 1;
                                this.Sequenze_di_cambiamento.add(linea_temp);
                                successivo = linea;
                                if(quanto_ci_passo == 0)
                                    this.Sequenze_nodi_cruciali.add(nome_stazione_cambio);
                                else
                                {
                                    /*for(int j=0;j<in_mezzo.size();j++)
                                    {
                                        if(in_mezzo.get(j).contains(ev))
                                        {
                                            con = con + 1; //con=0
                                            if(con==in_mezzo.size())
                                            {
                                                for(int l=0;l<in_mezzo.size();l++)
                                                {
                                                    if(in_mezzo.get(l).contains(linea))
                                                    {
                                                        this.Sequenze_nodi_cruciali.add(in_mezzo_nomi.get(l));
                                                        l=in_mezzo.size();
                                                    }
                                                }
                                            }

                                            //this.Sequenze_nodi_cruciali.add(in_mezzo_nomi.get(j));
                                        }
                                        else {
                                            this.Sequenze_nodi_cruciali.add(in_mezzo_nomi.get(j-1));
                                        }
                                    }
                                    */

                                    for (int j = 0; j < in_mezzo.size()-1; j++)
                                    {
                                        if(in_mezzo.get(j).equals(in_mezzo.get(j+1)))
                                        {
                                            count = count + 1;
                                        }
                                    }
                                    if(count==in_mezzo.size()-1)
                                    {
                                        count = 0;
                                        this.Sequenze_nodi_cruciali.add(in_mezzo_nomi.get(in_mezzo_nomi.size()-1));
                                    }
                                    else {

                                        while (!(da_raggiungere.equals(ev))) {
                                            for (int j = 0; j < in_mezzo.size(); j++) {
                                                if (in_mezzo.get(j).contains(linea)) {
                                                    temp = in_mezzo.get(j);
                                                    nome_cambio.add(in_mezzo_nomi.get(j)); //nome_cambio ho tutto
                                                    lista_appoggio.add(temp);
                                                    in_mezzo.set(j, "");
                                                    in_mezzo_nomi.set(j, "");

                                                }
                                            }
                                            //puo essere che lista appoggio abbia più di un elemento
                                            //ora strtok sugli elementi iesimi
                                            for (int l = 0; l < lista_appoggio.size(); l++) {

                                                String[] parole = lista_appoggio.get(l).split("-");
                                                for (String parola : parole) {
                                                    //System.out.println(parola);
                                                    if (!(parola.equals(linea))) //se parola != linea
                                                    {
                                                        da_raggiungere = parola;
                                                        if (!(da_raggiungere.equals(ev))) {
                                                            linea = da_raggiungere;
                                                            this.Sequenze_di_cambiamento.add(linea);
                                                            cambi_linee_metropolitane = cambi_linee_metropolitane + 1;
                                                            cambi.add(nome_cambio.get(l));
                                                            l = 1000;
                                                            break; //esce dal for
                                                        } else {
                                                            cambi.add(nome_cambio.get(l)); //ok
                                                            l = 1000;
                                                        }


                                                        //linea = da_raggiungere;

                                                    }

                                                }

                                            }

                                            lista_appoggio.clear();
                                            nome_cambio.clear();
                                        }
                                    }

                                }
                                in_mezzo.clear();
                                in_mezzo_nomi.clear();
                                check = false;
                                linea_temp = linea;
                                quanto_ci_passo = 0;
                                this.Sequenze_di_cambiamento.add(successivo);

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

                                    if(stopping==true) {
                                        for (int j = 0; j < cambi_iniziali_linee.size(); j++) {
                                            String[] parole = cambi_iniziali_linee.get(j).split("-");
                                            for (String parola : parole) {
                                                if (parola.equals(success)) {
                                                    contatore++;
                                                    nome_stazione_cambio = cambi_iniziali.get(j);
                                                }
                                            }
                                        }
                                        if (contatore == cambi_iniziali_linee.size()) {
                                            System.out.println("Nessun cambio");
                                            nome_stazione_cambio = "";
                                        } else {
                                            this.Sequenze_nodi_cruciali.add(nome_stazione_cambio);
                                        }
                                        contatore=0;
                                        cambi_iniziali.clear();
                                        cambi_iniziali_linee.clear();
                                    }
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



            for(int i=cambi.size()-1;i>=0;i--)
            {
                System.out.println(" CAMBIO = " + cambi.get(i));
                this.Sequenze_nodi_cruciali.add(cambi.get(i));

            }

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
