<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Visualizzazione Percorsi</title>
</head>
<body>
    <h1>Elenco Dati</h1>
    <ul>
        <%
            // Ottieni i dati dall'attributo della richiesta
            List<String> dati = (List<String>) request.getAttribute("percorsi");

            if (dati != null) {
                for (String elemento : dati) {
                    out.println("<li>" + elemento + "</li>");
                    out.println(" ");
                    //Il metodo out.println in una pagina JSP scrive contenuto direttamente nella risposta HTTP
                    //inviata al browser.


                }
            } else {
                out.println("<li>Nessun dato disponibile.</li>");
            }
        %>
    </ul>
</body>
</html>
