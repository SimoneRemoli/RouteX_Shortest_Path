<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <title>Visualizzazione Percorsi</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            margin-top: 20px;
            color: #333;
        }

        .container {
            max-width: 600px;
            margin: 20px auto;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }

        .route-list {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        .route-list li {
            display: flex;
            align-items: center;
            padding: 15px;
            border-bottom: 1px solid #ddd;
            font-size: 16px;
            animation: fadeIn 0.5s ease;
        }

        .route-list li:last-child {
            border-bottom: none;
        }

        .route-list li span {
            margin-right: 10px;
            background: #0078d7;
            color: #fff;
            border-radius: 50%;
            width: 30px;
            height: 30px;
            display: flex;
            justify-content: center;
            align-items: center;
            font-weight: bold;
        }

        .route-list li:hover {
            background-color: #f0f8ff;
        }

        .no-data {
            text-align: center;
            color: #888;
            padding: 20px;
        }

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
    <h1>Percorso Stazioni</h1>
    <div class="container">
        <ul class="route-list">
            <% // È utilizzato per includere codice Java direttamente all'interno di una pagina JSP.
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
</body>
</html>
