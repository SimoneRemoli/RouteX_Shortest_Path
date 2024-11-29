<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <title>RouteX - Visualizzazione Percorsi</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
            display: flex;
            height: 100vh;
        }

        /* Barra laterale per i percorsi */
        .sidebar {
            width: 300px;
            background: #0078d7;
            color: white;
            padding: 20px;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
            overflow-y: auto; /* Aggiunge lo scrolling verticale */
            height: 100vh; /* Imposta altezza della barra laterale */
        }

        .sidebar h1 {
            margin: 0;
            font-size: 22px;
            text-align: center;
        }

        .route-list {
            list-style: none;
            padding: 0;
            margin: 20px 0;
        }

        .route-list li {
            display: flex;
            align-items: center;
            margin-bottom: 15px;
            padding: 10px;
            background: rgba(255, 255, 255, 0.2);
            border-radius: 8px;
            animation: fadeIn 0.5s ease;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .route-list li:hover {
            background: rgba(255, 255, 255, 0.4);
        }

        .route-list li span {
            margin-right: 10px;
            background: white;
            color: #0078d7;
            border-radius: 50%;
            width: 30px;
            height: 30px;
            display: flex;
            justify-content: center;
            align-items: center;
            font-weight: bold;
        }

        .no-data {
            text-align: center;
            color: rgba(255, 255, 255, 0.8);
        }

        /* Sezione principale */
        .main {
            flex-grow: 1;
            padding: 20px;
            position: relative;
        }

        /* Pulsante Home */
        .home-button {
            position: absolute;
            top: 20px;
            right: 20px;
            background: #0078d7;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            transition: background-color 0.3s ease, transform 0.2s ease;
        }
        .back-button {
            position: absolute;
            top: 20px;
            right: 120px; /* Posizionato vicino al pulsante "Home" */
            background: #0078d7; /* Colore blu */
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            transition: background-color 0.3s ease, transform 0.2s ease;
        }

        .back-button:hover {
            background: #d32f2f;
            transform: translateY(-2px);
        }


        .home-button:hover {
            background: #005bb5;
            transform: translateY(-2px);
        }

        /* Animazioni */
        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(10px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
    </style>
</head>
<body>
    <!-- Barra laterale -->
    <div class="sidebar">
        <h1>Best route</h1>
        <ul class="route-list">
            <%
                // Ottieni i dati dall'attributo della richiesta
                List<String> dati = (List<String>) request.getAttribute("percorsi");



                if (dati != null && !dati.isEmpty()) {
                    int count = 1;
                    for (String elemento : dati) {
            %>
                        <li>

                            <span><%= count++ %></span>
                            <%= elemento %>
                        </li>
            <%
                    }
                } else {
            %>
                <div class="no-data">
                    <p>Nessun dato disponibile per il percorso.</p>
                </div>
            <%
                }
            %>
        </ul>
    </div>

    <!-- Sezione principale -->
    <div class="main">
        <button class="home-button" onclick="location.href='index.jsp'">Home</button>
        <button class="back-button" onclick="location.href='search.jsp'">Back</button>

        <h2>Welcome to RouteX!</h2>
        <p>
            Information about the route you have just chosen will be provided below.
            <%
            int numero_cambi = (int) request.getAttribute("numero_cambi");
            //out.println("<li>" + numero_cambi + "</li>");
            out.print("<h3> Number of metro line changes : " + numero_cambi + "</h3>");
            %>

        </p>
    </div>
</body>
</html>
