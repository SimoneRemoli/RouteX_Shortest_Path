<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>RouteX - Metro Finder</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Arial Rounded MT Bold', sans-serif;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            overflow: hidden;
            position: relative;
            background: #e0f7fa;
        }

        /* Contenitore SVG per la mappa animata */
        .metro-map {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: -1;
            overflow: hidden;
        }

        /* Linee animate */
        .line {
            fill: none;
            stroke-width: 5;
            stroke-linecap: round;
            animation: move 4s infinite linear;
        }

        .line1 {
            stroke: #007bff;
        }

        .line2 {
            stroke: #ff5722;
        }

        .line3 {
            stroke: #4caf50;
        }

        .line4 {
            stroke: #9c27b0;
        }

        @keyframes move {
            0% {
                stroke-dasharray: 0, 200;
            }
            100% {
                stroke-dasharray: 200, 200;
            }
        }

        /* Cerchi animati (nodi) */
        .station {
            fill: white;
            stroke: black;
            stroke-width: 3;
            r: 8;
            animation: pop 2s infinite alternate;
        }

        @keyframes pop {
            0% {
                transform: scale(1);
            }
            100% {
                transform: scale(1.2);
            }
        }

        .welcome-container {
            text-align: center;
            background: rgba(255, 255, 255, 0.9);
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
        }

        .welcome-container img {
            animation: fadeIn 2s ease-in-out;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: scale(0.9);
            }
            to {
                opacity: 1;
                transform: scale(1);
            }
        }

        .welcome-button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 15px;
            font-size: 18px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .welcome-button:hover {
            background-color: #0056b3;
        }

        .button-container {
            position: absolute;
            top: 20px;
            right: 40px;
            display: flex;
            gap: 10px;
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
            transition: background-color 0.3s ease;
        }

        .button-container a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <!-- Animazione SVG -->
    <svg class="metro-map" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 800 600">
        <!-- Linee metropolitane -->
        <path d="M100,100 C200,50 300,150 400,100 S600,150 700,100" class="line line1"/>
        <path d="M150,200 C250,250 350,150 450,200 S650,250 750,200" class="line line2"/>
        <path d="M100,300 C200,350 300,250 400,300 S600,350 700,300" class="line line3"/>
        <path d="M150,400 C250,450 350,350 450,400 S650,450 750,400" class="line line4"/>

        <!-- Stazioni (nodi) -->
        <circle cx="100" cy="100" class="station"/>
        <circle cx="400" cy="100" class="station"/>
        <circle cx="700" cy="100" class="station"/>

        <circle cx="150" cy="200" class="station"/>
        <circle cx="450" cy="200" class="station"/>
        <circle cx="750" cy="200" class="station"/>

        <circle cx="100" cy="300" class="station"/>
        <circle cx="400" cy="300" class="station"/>
        <circle cx="700" cy="300" class="station"/>

        <circle cx="150" cy="400" class="station"/>
        <circle cx="450" cy="400" class="station"/>
        <circle cx="750" cy="400" class="station"/>
    </svg>

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
