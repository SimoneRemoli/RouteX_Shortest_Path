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
            background: linear-gradient(to bottom, #e0f7fa, #80deea);
        }

        /* Animazioni di sfondo con linee in movimento */
        .metro-lines {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            overflow: hidden;
            z-index: -1;
        }

        .line {
            position: absolute;
            width: 2px;
            height: 100%;
            background-color: #007bff;
            animation: move 6s linear infinite;
        }

        @keyframes move {
            0% {
                transform: translateX(-100%);
            }
            100% {
                transform: translateX(100%);
            }
        }

        .line:nth-child(2) {
            left: 10%;
            animation-delay: 1s;
            background-color: #ff5722;
        }

        .line:nth-child(3) {
            left: 30%;
            animation-delay: 2s;
            background-color: #4caf50;
        }

        .line:nth-child(4) {
            left: 50%;
            animation-delay: 3s;
            background-color: #ffc107;
        }

        .line:nth-child(5) {
            left: 70%;
            animation-delay: 4s;
            background-color: #9c27b0;
        }

        .line:nth-child(6) {
            left: 90%;
            animation-delay: 5s;
            background-color: #03a9f4;
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
    <!-- Animazioni di sfondo -->
    <div class="metro-lines">
        <div class="line"></div>
        <div class="line"></div>
        <div class="line"></div>
        <div class="line"></div>
        <div class="line"></div>
        <div class="line"></div>
    </div>

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
