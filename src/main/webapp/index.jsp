<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>RouteX - Metro Finder</title>
    <style>
        body {
            background: linear-gradient(to bottom, #e0f7fa, #80deea);
            font-family: 'Arial Rounded MT Bold', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            position: relative;
        }

        .welcome-container {
            text-align: center;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .welcome-button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 15px;
            font-size: 18px;
            cursor: pointer;
        }

        /* Stile per i pulsanti registrati e login */
        .button-container {
            position: absolute;
            top: 20px;
            right: 40px; /* Spazio dal margine destro */
            display: flex;
            gap: 10px; /* Spazio tra i due pulsanti */
        }

        .button-container a {
            background-color: #007bff;
            color: white;
            padding: 10px 15px;
            font-size: 16px;
            border: none;
            cursor: pointer;
            border-radius: 10px;
            text-decoration: none;
            text-align: center;
        }

        .button-container a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

    <!-- Contenitore dei pulsanti Login e Registrati -->
    <div class="button-container">
        <a href="register.jsp">Register</a>
        <a href="login.jsp">Login</a>
    </div>



    <!-- Contenitore centrale -->
    <div class="welcome-container">
        <img src="images/logo-no-background.png" alt="Logo" width="299" height="120"/>
        <h1> RouteX - Navigating the Future, One Stop at a Time </h1>
        <form action="search.jsp" method="post">
            <button class="welcome-button" type="submit">Start</button>
        </form>
    </div>
</body>
</html>
