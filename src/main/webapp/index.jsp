<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>RouteX - Il percorso del futuro</title>
    <style>
        body {
            background: linear-gradient(to bottom, #e0f7fa, #80deea);
            font-family: 'Arial Rounded MT Bold', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
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
    </style>
</head>
<body>
    <div class="welcome-container">
        <img src="images/logo-no-background.png" alt="Logo" width="299" height="120"/>
        <h1>RouteX - Il percorso del futuro</h1>
        <form action="search.jsp" method="post">
            <button class="welcome-button" type="submit">Start</button>
        </form>
    </div>
</body>
</html>
