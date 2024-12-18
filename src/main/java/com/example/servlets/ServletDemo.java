package com.example.servlets;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.Line;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;


//faccio un commento per sincronizzarmi con la repository remota
//ricommentoo
//sync
//aa
//new
//pooo
@WebServlet("/ServletDemo")
public class ServletDemo extends HttpServlet {
    ArrayList<String> Percorsi_Con_Nomi = new ArrayList<String>();
    ArrayList<Integer> Percorsi_Codifiche = new ArrayList<Integer>();
    int numero_cambi = 0;
    ArrayList<String> Linee_Metropolitane = new ArrayList<String>();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupera il valore della città selezionata dal form
        System.out.println(("ciao"));
        String city = request.getParameter("city");
        String startStation = request.getParameter("startStation");
        String endStation = request.getParameter("endStation");
        int code_start_station = 0, code_finish_station = 0;
        //30
        // Esegui la logica con i dati ricevuti
        System.out.println("City: " + city);
        System.out.println("Start Station: " + startStation);
        System.out.println("End Station: " + endStation);


        try {
            StationDAO DAO = new StationDAO(startStation,endStation,city);
            code_start_station = DAO.getStazione_di_Partenza();
            code_finish_station = DAO.getStazione_di_arrivo();
            System.out.println("Id della stazione di partenza = " + DAO.getStazione_di_Partenza());
            System.out.println("Id della stazione di arrivo = " + DAO.getStazione_di_arrivo());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }



        City metropoli = null;
        
        if(city.equals("Rome")) // se scelgo roma entra qui
        {
            metropoli = new Rome();
        }
        if(city.equals("Milan")) //se scelgo milano entra qui
        {
            metropoli = new Milan();
        }
        assert metropoli != null;
        try {
            metropoli.Dijkstra(code_start_station,code_finish_station,city);
            Percorsi_Con_Nomi = metropoli.getPercorsi_Nomi(); //NELLA SERVLET HO I PERCORSI
            Percorsi_Codifiche = metropoli.getPercorsi_codifica();
            numero_cambi = metropoli.getNumero_cambi();
            Linee_Metropolitane = metropoli.getLinee();
            System.out.println(" ");
            System.out.println("-----------Numero cambi =  "+ numero_cambi);
            for (String s : Linee_Metropolitane) System.out.println("Sequenza di linee metropolitane =  " + s);
            if(numero_cambi == -1)
                numero_cambi = 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Nella servlet");
        for(int i=0;i<Percorsi_Con_Nomi.size();i++) System.out.print(" " + Percorsi_Con_Nomi.get(i)+" ---> ");
        for(int i=0;i<Percorsi_Codifiche.size();i++) System.out.println(" " + Percorsi_Codifiche.get(i)+ " ----> ");
        request.setAttribute("percorsi", Percorsi_Con_Nomi);
        request.setAttribute("numero_cambi", numero_cambi);
        request.setAttribute("linee", Linee_Metropolitane);
        //inoltro la richiesta al jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("PathNOREG.jsp");
        dispatcher.forward(request, response);


        // Aggiungi la logica per calcolare il percorso o qualsiasi altra logica
        String result = "Route from " + startStation + " to " + endStation + " in " + city;
        System.out.println(result);

        // Imposta il risultato come attributo per la pagina JSP
       // request.setAttribute("result", result);
        //aggiunta riga

        // Inoltra la richiesta alla pagina search.jsp per mostrare il risultato
        //request.getRequestDispatcher("/search.jsp").forward(request, response);


        /*City roma = new City(city);
        roma.Dijkstra();
        System.out.println("Val = "+roma.nome);


*/



       /* City metropoli1 = new Rome();
        City metropoli2 = new Milan();
        //metropoli1.Dijkstra();
        metropoli2.Dijkstra();
*/



    

    }
}

/*
Come Funziona?
L'utente seleziona la città e le stazioni dal form e preme "Find Route" (in search.jsp).
Il form invia una richiesta POST alla servlet SearchServlet.
La servlet SearchServlet legge i parametri (city, startStation, endStation),
elabora i dati e crea una stringa result.
La servlet inoltra la richiesta alla pagina search.jsp, passando il result come attributo.
La pagina search.jsp visualizza il result.
Nota Importante
Assicurati di:

Avere la servlet mappata correttamente nel web.xml o tramite l'annotazione @WebServlet.
Il path della servlet (/search) corrisponda all'azione del form (action="search").


NOTA: Se hai configurato la servlet con l'annotazione @WebServlet, il valore dell'action nel
form deve corrispondere al path specificato nell'annotazione
 */
