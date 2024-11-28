<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Visualizzazione Dati</title>
</head>
<body>
    <h1>Elenco Dati</h1>
    <ul>
        <%
            // Ottieni i dati dall'attributo della richiesta
            List<String> dati = (List<String>) request.getAttribute("dati");

            if (dati != null) {
                for (String elemento : dati) {
                    out.println("<li>" + elemento + "</li>");
                }
            } else {
                out.println("<li>Nessun dato disponibile.</li>");
            }
        %>
    </ul>
</body>
</html>
