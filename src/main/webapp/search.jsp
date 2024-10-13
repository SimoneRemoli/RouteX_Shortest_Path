<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>RouteX - Metro Finder</title>
    <style>
        /* Stile di base per la pagina */
        body {
            background: linear-gradient(to bottom, #e0f7fa, #80deea);
            font-family: 'Arial Rounded MT Bold', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        /* Container per il form e la mappa */
        .main-container {
            display: flex;
            background: rgba(255, 255, 255, 0.9);
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
            animation: slideIn 1s ease-out;
            max-width: 1000px;
            width: 90%;
            gap: 20px;
        }

        /* Container per il form */
        .form-container {
            flex: 1;
            text-align: center;
        }

        /* Container per la mappa */
        .map-container {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            border: 2px solid #007bff;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            background: #f0f8ff;
        }

        .map-container img {
            max-width: 100%;
            max-height: 100%;
            border-radius: 10px;
        }

        /* Stile per le dropdown */
        select {
            width: 80%;
            padding: 12px;
            margin: 10px 0;
            border: 1px solid #007bff;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            font-size: 16px;
            transition: all 0.3s ease;
        }

        /* Stile per il bottone */
        button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 12px 20px;
            border-radius: 10px;
            font-size: 18px;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.2s ease;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        /* Effetto hover per il bottone */
        button:hover {
            background-color: #0056b3;
            transform: translateY(-2px);
        }

        /* Effetto focus per i select */
        select:focus {
            outline: none;
            border-color: #0056b3;
            box-shadow: 0 0 8px rgba(0, 91, 187, 0.5);
        }

        /* Animazione di slide in */
        @keyframes slideIn {
            from {
                opacity: 0;
                transform: translateY(-20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        /* Stile per il testo del risultato */
        p {
            font-size: 18px;
            color: #333;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="main-container">
        <div class="form-container">
            <h2>RouteX - Find Your Metro Route</h2>
            <form action="ServletDemo" method="post" name="select">
                <select name="city" id="citySelect" onchange="updateMap()">
                    <option value="Rome">Rome</option>
                    <option value="Paris">Paris</option>
                    <!-- Altre città -->
                </select>

                <select name="startStation">
                    <option value="Termini">Termini</option>
                    <option value="Colosseo">Colosseo</option>
                </select>

                <select name="endStation">
                    <option value="Termini">Termini</option>
                    <option value="Colosseo">Colosseo</option>
                </select>

                <button type="submit">Find Route</button>
            </form>

            <p>${result}</p>
        </div>

        <div class="map-container">
            <img id="mapImage" src="images/subway_default.png" alt="Metro Map">
        </div>
    </div>

    <script>
        function updateMap() {
            const citySelect = document.getElementById('citySelect');
            const mapImage = document.getElementById('mapImage');
            const selectedCity = citySelect.value.toLowerCase();
            // Aggiorna il percorso dell'immagine in base alla città selezionata
            mapImage.src = 'images/metro-' + selectedCity + '.jpg';
            //alert("La città selezionata è: " + mapImage.src);

        }
    </script>
        <title>RouteX - Metro Finder Result</title>

        <h2>RouteX - Route Result</h2>
        <p>${result}</p>
</body>
</html>
