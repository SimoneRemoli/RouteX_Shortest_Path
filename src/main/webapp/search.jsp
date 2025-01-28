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
            position: relative;
        }

        /* Container per il form e la mappa */
        .main-container {
            display: flex;
            background: rgba(255, 255, 255, 0.9);
            padding: 40px; /* Aumentato il padding */
            border-radius: 20px; /* Aumentato il border-radius */
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.3); /* Rafforzato lo shadow */
            animation: slideIn 1s ease-out;
            max-width: 1200px; /* Aumentato da 1000px a 1200px */
            width: 95%; /* Aumentato il width */
            gap: 40px; /* Aumentato lo spazio tra i contenitori */
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
            border-radius: 20px; /* Aumentato il border-radius */
            box-shadow: 0 8px 10px rgba(0, 0, 0, 0.2); /* Rafforzato lo shadow */
            background: #f0f8ff;
        }

        .map-container img {
            max-width: 100%;
            max-height: 100%;
            border-radius: 10px;
        }

        /* Stile per le dropdown */
        select {
            width: 90%; /* Aumentata la larghezza */
            padding: 16px; /* Aumentato il padding */
            margin: 10px 0;
            border: 1px solid #007bff;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            font-size: 18px; /* Aumentato il font-size */
            transition: all 0.3s ease;
        }

        /* Stile per il bottone */
        button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 16px 30px; /* Aumentato il padding */
            border-radius: 15px;
            font-size: 20px; /* Aumentato il font-size */
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
            font-size: 20px; /* Aumentato il font-size */
            color: #333;
            margin-top: 20px;
        }

        /* Stile per i pulsanti registrati, login e home */
        .button-container-right {
            position: absolute;
            top: 20px;
            right: 40px; /* Spazio dal margine destro */
            display: flex;
            gap: 10px; /* Spazio tra i due pulsanti */
        }

        .button-container-right a, .button-container-left a {
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

        .button-container-right a:hover, .button-container-left a:hover {
            background-color: #0056b3;
        }

        /* Contenitore per il pulsante Home */
        .button-container-left {
            position: absolute;
            top: 20px;
            left: 40px; /* Spazio dal margine sinistro */
        }

    </style>
<body>

    <!-- Contenitore del pulsante Home -->
    <div class="button-container-left">
        <a href="index.jsp">Home</a>
    </div>

    <!-- Contenitore dei pulsanti Login e Registrati -->
    <div class="button-container-right">
        <a href="register.jsp">Register</a>
        <a href="login.jsp">Login</a>
    </div>

    <div class="main-container">
        <div class="form-container">
            <h2>RouteX - Find Your Metro Route</h2>
            <form action="ServletDemo" method="post" name="select">
                <select name="city" id="citySelect" onchange="updateStationsAndMap()">
                    <option value="" disabled selected>Select a city</option>
                    <option value="Rome">Rome</option>
                    <option value="Paris">Paris</option>
                    <option value="Milan">Milan</option>
                    <option value="Naples">Naples</option>
                    <option value="London">London</option>
                    <option value="Berlin">Berlin</option>
                    <option value="Stockholm">Stockholm</option>
                    <option value="Athens">Athens</option>
                    <option value="Budapest">Budapest</option>
                </select>

                <select name="startStation" id="startStation">
                    <!-- Opzioni stazioni caricate dinamicamente -->
                </select>

                <select name="endStation" id="endStation">
                    <!-- Opzioni stazioni caricate dinamicamente -->
                </select>

                <button type="submit">Find Route</button>
            </form>

        </div>

        <div class="map-container">
            <img id="mapImage" src="images/subway_default.png" alt="Metro Map">
        </div>
    </div>

    <script>
        // Dati delle stazioni per ogni città
        const cityStations = {
            "Rome": [
                "Battistini", "Cornelia", "Baldo degli Ubaldi", "Valle Aurelia", "Cipro",
                "Ottaviano", "Lepanto", "Flaminio", "Spagna", "Barberini", "Repubblica",
                "Termini", "Vittorio Emanuele", "Manzoni", "San Giovanni", "Re di Roma",
                "Ponte Lungo", "Furio Camillo", "Colli Albani", "Arco di Travertino",
                "Porta Furba", "Numidio Quadrato", "Lucio Sestio", "Giulio Agricola",
                "Subaugusta", "Cinecittà", "Anagnina", "Pantano", "Graniti",
                "Finocchio", "Bolognetta", "Borghesiana", "Due Leoni - Fontana Candida", "Grotte Celoni",
                "Torre Gaia", "Torre Angela", "Torrenova", "Giardinetti", "Torre Maura",
                "Torre Spaccata", "Alessandrino", "Parco di Centocelle", "Mirti",
                "Gardenie", "Teano", "Malatesta", "Pigneto", "Lodi", "San Giovanni", "Laurentina",
                "EUR Fermi", "EUR Palasport", "EUR Magliana", "Marconi", "Basilica S. Paolo",
                "Garbatella", "Piramide", "Circo Massimo", "Colosseo","Cavour", "Termini",
                "Castro Pretorio", "Policlinico", "Bologna", "Tiburtina FS", "Quintiliani",
                "Monti Tiburtini", "Pietralata", "Santa Maria del Soccorso", "Ponte Mammolo",
                "Rebibbia", "Annibaliano", "Libia", "Conca d'Oro", "Jonio"
            ],

            "Paris": ["La Défense", "Esplanade de La Défense", "Pont de Neuilly", "Les Sablons",
                     "Porte Maillot", "Argentine", "Charles de Gaulle–Étoile", "George V",
                     "Franklin D. Roosevelt", "Champs-Élysées–Clemenceau", "Concorde",
                     "Tuileries", "Palais Royal–Musée du Louvre", "Louvre–Rivoli",
                     "Châtelet", "Hôtel de Ville", "Saint-Paul", "Bastille", "Gare de Lyon",
                     "Reuilly–Diderot", "Nation", "Porte de Vincennes", "Saint-Mandé", "Bérault",
                     "Château de Vincennes", "Porte Dauphine", "Victor Hugo",
                     "Ternes", "Courcelles",
                     "Monceau", "Villiers", "Rome", "Place de Clichy", "Blanche", "Pigalle", "Anvers",
                     "Barbès–Rochechouart", "La Chapelle", "Stalingrad", "Jaurès", "Colonel Fabien",
                     "Belleville", "Couronnes", "Ménilmontant", "Père Lachaise",
                     "Philippe Auguste", "Alexandre Dumas", "Avron", "Pont de Levallois–Bécon",
                     "Anatole France", "Louise Michel", "Porte de Champerret", "Pereire", "Wagram",
                     "Malesherbes", "Europe", "Saint-Lazare", "Havre–Caumartin", "Opéra",
                     "Quatre-Septembre", "Bourse", "Sentier", "Réaumur–Sébastopol", "Arts et Métiers",
                     "Temple", "République", "Parmentier", "Rue Saint-Maur",
                     "Gambetta", "Porte de Bagnolet", "Gallieni", "Porte des Lilas", "Saint-Fargeau",
                     "Pelleport", "Porte de Clignancourt", "Simplon", "Marcadet–Poissonniers",
                     "Château Rouge", "Gare de l'Est",
                     "Château d'Eau", "Strasbourg–Saint-Denis",
                     "Étienne Marcel", "Les Halles", "Cité", "Saint-Michel", "Odéon",
                     "Saint-Germain-des-Prés", "Saint-Sulpice", "Saint-Placide", "Montparnasse–Bienvenüe",
                     "Vavin", "Raspail", "Denfert-Rochereau", "Mouton-Duvernet", "Alésia",
                     "Porte d'Orléans", "Mairie de Montrouge", "Bobigny – Pablo Picasso",
                     "Bobigny – Pantin – Raymond Queneau", "Église de Pantin", "Hoche",
                     "Porte de Pantin", "Ourcq", "Laumière", "Gare du Nord",
                     "Jacques Bonsergent", "Oberkampf", "Richard-Lenoir",
                     "Bréguet–Sabin", "Quai de la Rapée", "Gare d'Austerlitz",
                     "Saint-Marcel", "Campo-Formio", "Place d'Italie", "Kléber", "Boissière", "Trocadéro", "Passy", "Bir-Hakeim", "Dupleix",
                     "La Motte-Picquet – Grenelle", "Cambronne", "Sèvres-Lecourbe", "Pasteur",
                     "Edgar Quinet", "Saint-Jacques", "Glacière", "Corvisart",
                     "Nationale",
                     "Chevaleret", "Quai de la Gare", "Bercy", "Dugommier", "Daumesnil", "Bel-Air",
                     "La Courneuve – 8 Mai 1945", "Fort d'Aubervilliers",
                     "Aubervilliers – Pantin – Quatre Chemins", "Porte de la Villette",
                     "Corentin Cariou", "Crimée", "Riquet", "Louis Blanc",
                     "Château-Landon", "Poissonnière", "Cadet", "Le Peletier",
                     "Chaussée d'Antin – La Fayette", "Pyramides",
                     "Pont Neuf",
                     "Pont Marie", "Sully – Morland", "Jussieu", "Place Monge",
                     "Censier – Daubenton", "Les Gobelins", "Tolbiac", "Maison Blanche",
                     "Le Kremlin-Bicêtre", "Villejuif – Léo Lagrange", "Villejuif – Paul Vaillant-Couturier",
                     "Villejuif – Louis Aragon", "Porte de Choisy", "Porte d'Ivry",
                     "Pierre et Marie Curie", "Mairie d'Ivry", "Bolivar",
                     "Buttes Chaumont", "Botzaris", "Danube", "Place des Fêtes", "Pré Saint-Gervais", "Balard",
                     "Lourmel", "Boucicaut", "Félix Faure", "Commerce",
                     "École Militaire", "La Tour-Maubourg", "Invalides", "Madeleine",
                     "Richelieu–Drouot", "Grands Boulevards", "Bonne Nouvelle",
                     "Filles du Calvaire",
                     "Saint-Sébastien–Froissart", "Chemin Vert", "Ledru-Rollin",
                     "Faidherbe–Chaligny", "Montgallet",
                     "Michel Bizot", "Porte Dorée", "Porte de Charenton", "Liberté", "Charenton–Écoles",
                     "École Vétérinaire de Maisons-Alfort", "Maisons-Alfort–Stade", "Maisons-Alfort–Les Juilliottes",
                     "Créteil–L'Échat", "Créteil–Université", "Créteil–Préfecture", "Créteil–Pointe du Lac", "Pont de Sèvres",
                     "Billancourt", "Marcel Sembat", "Porte de Saint-Cloud", "Exelmans", "Michel-Ange–Molitor",
                     "Michel-Ange–Auteuil", "Jasmin", "Ranelagh", "La Muette", "Rue de la Pompe",
                     "Iéna", "Alma–Marceau", "Saint-Philippe du Roule", "Miromesnil",
                     "Saint-Augustin", "Chaussée d'Antin–La Fayette", "Filles du Calvaire",
                     "Charonne", "Rue des Boulets", "Buzenval",
                     "Maraîchers", "Porte de Montreuil", "Robespierre", "Croix de Chavaux",
                     "Mairie de Montreuil", "Boulogne–Pont de Saint-Cloud", "Boulogne–Jean Jaurès",
                     "Chardon Lagache", "Église d'Auteuil", "Mirabeau",
                     "Javel–André Citroën", "Charles Michels", "Avenue Émile Zola",
                     "Ségur", "Duroc", "Vaneau", "Sèvres–Babylone", "Mabillon", "Cluny–La Sorbonne",
                     "Maubert–Mutualité", "Cardinal Lemoine",
                     "Rambuteau", "Goncourt",
                     "Pyrénées", "Jourdain", "Télégraphe",
                     "Mairie des Lilas", "Front Populaire", "Porte de la Chapelle", "Marx Dormoy",
                     "Jules Joffrin", "Lamarck–Caulaincourt", "Abbesses",
                     "Saint-Georges", "Notre-Dame-de-Lorette", "Trinité–d'Estienne d'Orves",
                     "Assemblée Nationale", "Solférino", "Rue du Bac",
                     "Rennes", "Notre-Dame-des-Champs", "Montparnasse Bienvenüe", "Falguière",
                     "Volontaires", "Vaugirard", "Convention", "Porte de Versailles", "Corentin Celton",
                     "Mairie d'Issy", "Saint-Denis–Université", "Basilique de Saint-Denis", "Saint-Denis–Porte de Paris",
                     "Carrefour Pleyel", "Les Agnettes", "Gabriel Péri", "Mairie de Saint-Ouen", "Garibaldi",
                     "Porte de Saint-Ouen", "Guy Môquet", "La Fourche", "Liège",
                     "Champs-Élysées–Clemenceau", "Varenne",
                     "Saint-François-Xavier", "Gaîté", "Pernety", "Plaisance",
                     "Porte de Vanves", "Malakoff–Plateau de Vanves", "Malakoff–Rue Étienne Dolet",
                     "Châtillon–Montrouge", "Pyramides", "Cour Saint-Émilion", "Bibliothèque François Mitterrand",
                     "Olympiades"],

            "Milan": ["Sesto 1 Maggio FS", "Sesto Rondo", "Sesto Marelli", "Villa San Giovanni", "Precotto", "Gorla", "Turro", "Rovereto",
                      "Pasteur", "Loreto", "Lima", "Porta Venezia", "Palestro", "San Babila", "Duomo", "Cordusio", "Cairoli", "Cadorna FN",
                      "Conciliazione", "Pagano", "De Angeli", "Gambara", "Bande Nere", "Primaticcio", "Inganni", "Bisceglie", "Wagner",
                      "Buonarroti", "Amendola", "Lotto", "QT8", "Lampugnano", "Uruguay", "Bonola", "San Leonardo", "Molino Dorino", "Rho Fiera",
                      "Abbiategrasso", "Piazza Abbiategrasso", "Famagosta", "Romolo", "Porta Genova FS", "Sant'Agostino", "Sant'Ambrogio",
                      "Lanza", "Moscova", "Garibaldi FS", "Gioia", "Centrale FS", "Caiazzo", "Piola", "Lambrate FS", "Udine", "Cimiano",
                      "Crescenzago", "Cascina Gobba", "Cologno Sud", "Cologno Centro", "Cologno Nord", "Vimodrone", "Cascina Burrona",
                      "Cernusco sul Naviglio", "Villa Fiorita", "Cassina de' Pecchi", "Bussero", "Villa Pompea", "Gorgonzola", "Gessate",
                      "Comasina", "Affori FN", "Affori Centro", "Dergano", "Maciachini", "Zara", "Sondrio", "Repubblica", "Turati",
                      "Montenapoleone", "Missori", "Crocetta", "Porta Romana", "Lodi T.I.B.B.", "Brenta", "Corvetto", "Porto di Mare",
                      "Rogoredo FS", "San Donato", "San Cristoforo", "Segneri", "Frattini", "Gelsomini", "Tolstoi", "Washington-Bolivar",
                      "De Amicis", "Vetra", "Santa Sofia", "Tricolore", "Dateo", "Susa", "Argonne",
                      "Forlanini FS", "Repetti", "Stazione Forlanini", "Linate Aeroporto", "Bignami", "Ponale", "Bicocca", "Ca' Granda",
                      "Istria", "Marche", "Isola", "Monumentale", "Cenisio", "Gerusalemme", "Domodossola FN", "Tre Torri", "Portello",
                      "Segesta", "San Siro Ippodromo", "San Siro Stadio"],
            "Naples": ["Pozzuoli Solfatara", "Bagnoli-Agnano Terme", "Campi Flegrei", "Mostra", "P.Leopardi", "Augusto", "Lala",
                       "Mergellina", "Arco Mirelli", "San Pasquale", "Chiaia", "P.Amedeo", "Montesanto", "Museo-Piazza Cavour",
                       "Dante", "Toledo", "Municipio", "Università", "Duomo", "Garibaldi", "Gianturco", "S.Giovanni-Barra",
                       "Materdei", "Salvator Rosa", "Quattro Giornate", "Vanvitelli", "Medaglie D'Oro", "Montedonzelli",
                       "Rione Alto", "Policlinico", "Colli Aminei", "Frullone", "Chiaiano", "Piscinola", "Mugnano", "Giugliano",
                       "Aversa Ippodromo", "Aversa Centro"],
            "London": ["Victoria", "Waterloo", "King's Cross", "Paddington"],
            "Berlin": ["Alexanderplatz", "Hauptbahnhof", "Potsdamer Platz", "Zoologischer Garten"],
            "Stockholm": ["T-Centralen", "Gamla Stan", "Slussen", "Fridhemsplan"],
            "Athens": ["Syntagma", "Acropolis", "Monastiraki", "Omonia"],
            "Budapest": ["Deák Ferenc tér", "Kálvin tér", "Astoria", "Keleti pályaudvar"]
        };

        function updateStationsAndMap() {
            const citySelect = document.getElementById('citySelect');
            const startStation = document.getElementById('startStation');
            const endStation = document.getElementById('endStation');
            const mapImage = document.getElementById('mapImage');
            const selectedCity = citySelect.value;

            // Se non è selezionata una città, non cambiare l'immagine
            if (!selectedCity) {
                return;
            }

            // Aggiorna le opzioni delle stazioni in base alla città selezionata
            const stations = cityStations[selectedCity] || [];

            // Funzione per aggiornare le opzioni in un menu a tendina
            function updateDropdown(dropdown) {
                dropdown.innerHTML = ""; // Svuota il menu a tendina
                stations.forEach(station => {
                    const option = document.createElement("option");
                    option.value = station;
                    option.text = station;
                    dropdown.appendChild(option);
                });
            }

            // Aggiorna entrambi i menu a tendina
            updateDropdown(startStation);
            updateDropdown(endStation);

            // Aggiorna l'immagine della mappa in base alla città selezionata
            mapImage.src = 'images/metro-' + selectedCity.toLowerCase() + '.jpg';
        }

        // Inizializza le stazioni e la mappa per la città di default solo al caricamento
        document.addEventListener("DOMContentLoaded", () => {
            document.getElementById("mapImage").src = "images/subway_default.png";
        });
    </script>

</body>
</html>
