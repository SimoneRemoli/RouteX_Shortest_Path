package com.example.servlets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

//faccio un commento per sincronizzarmi con la repository remota
//ricommentoo
//sync
//aa
//new
//pooo
@WebServlet("/ServletDemo")
public class ServletDemo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupera il valore della città selezionata dal form
        System.out.println(("ciao"));
        String city = request.getParameter("city");
        String startStation = request.getParameter("startStation");
        String endStation = request.getParameter("endStation");
        //30
        // Esegui la logica con i dati ricevuti
        System.out.println("City: " + city);
        System.out.println("Start Station: " + startStation);
        System.out.println("End Station: " + endStation);

        // Aggiungi la logica per calcolare il percorso o qualsiasi altra logica
        String result = "Route frommm " + startStation + " to " + endStation + " in " + city;
        System.out.println(result);

        // Imposta il risultato come attributo per la pagina JSP
       // request.setAttribute("result", result);
        //aggiunta riga

        // Inoltra la richiesta alla pagina search.jsp per mostrare il risultato
        request.getRequestDispatcher("/search.jsp").forward(request, response);


        /*City roma = new City(city);
        roma.Dijkstra();
        System.out.println("Val = "+roma.nome);


*/



        City metropoli1 = new Rome();
        City metropoli2 = new Milan();
       // metropoli1.Dijkstra();
        metropoli2.Dijkstra();




    

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
