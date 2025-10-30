<!DOCTYPE html>
<html>
<head>
  <title>Page d'accueil</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="home.css">
  <style>
    /* Styles CSS supplémentaires */

    /* Définition des styles pour le corps de la page */
    body{
        background-color: white;
    }
    
    /* Définition des styles pour le tableau */
    table {
        border-collapse: collapse;
        width: 100%;
        position: relative;
        left: 230px;
        top: 90px;
    }

    th, td {
        padding: 8px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    th {
        background-color: #f2f2f2;
    }

    tr:hover {
        background-color: #f5f5f5;
    }
    
    /* Définition des styles pour le lien "Home" */
    .home{
        position: absolute;
        top: 43px;
        left: 30px ;
    }
    
    /* Définition des styles pour le lien "Déconnexion" */
    .deconnexion-link {
        position: absolute;
        top: 0px;
        right: 0px;
        padding: 48px 70px; 
        background-color: buttonface;
    }
    
    /* Définition des styles pour les boutons */
    .btn {
        margin-right: 20px; /* Espacement entre les boutons */
    }

    .btn-primary {
        color: white; /* Couleur du texte */
        background-color: #007bff; /* Couleur de fond */
        border-color: #007bff; /* Couleur de la bordure */
    }

    .btn-primary:hover {
        background-color: white; /* Couleur de fond au survol */
        border-color: #0062cc; /* Couleur de la bordure au survol */
    }

    /* Définition des styles pour la section supérieure de la page */
    .container.top-section {
        display: flex;
        justify-content: space-between;
        background-color: buttonface;
        padding: 41px;
        padding-left: 229px;
        margin-left: 0;
        margin-top: 0;
    }

    .container.top-section a {
        flex-grow: 1;
        text-align: center;
    }
      
    /* Définition des styles pour la section fixe sur le côté gauche */
    .fixed-side-section {
        position: fixed;
        top: 120px;
        left: 0;
        bottom: 0;
        width: 200px;
        background-color: greenyellow;
        padding: 20px;
    }

  </style>
</head>
<body>
  <% 
    // Récupération de la session et du nom d'utilisateur
    HttpSession ss = request.getSession(false);
    String user = (String)ss.getAttribute("user") ;
    if (user != null) { 
  %>
  <div class="container top-section">
    <!-- Lien de déconnexion -->
    <a href="DeconnexionServlet" class="deconnexion-link">Déconnexion</a>
    
    <!-- Boutons de navigation -->
    <a href="#" class="btn btn-primary" onclick="loadClients()">Clients</a>
    <a href="#" class="btn btn-primary" onclick="loadFacture()">Factures</a>
    <a href="#" class="btn btn-primary" onclick="loadArticle()">Articles</a>
  </div>

  <!-- Lien "Home" -->
  <a href="home.jsp" class="home btn btn-primary">Home</a> 
 
  <div class="row">
    <div class="col-md-3 fixed-side-section">
      <ul class="nav flex-column">
        <li class="nav-item mb-5">
          <a class="nav-link" href="#" onclick="loadAjouterArticleForm()">Ajouter Article</a>
        </li>
        <li class="nav-item mb-5">
          <a class="nav-link" href="#" onclick="loadAjouterFactureForm()">Ajouter Facture</a>
        </li>
        <li class="nav-item mb-5">
          <a class="nav-link" href="#" onclick="loadAjouterClientForm()">Ajouter Client</a>
        </li>
      </ul>
    </div>

    <div class="col-md-9">
      <div id="ajouterClientContainer"></div>
      <div id="ajouterFactureContainer"></div>
      <div id="ajouterArticleContainer"></div>
    </div>
  </div>

  <% } else { 
      out.print("error");
  } %>
  
  <%-- Affichage des messages de succès ou d'erreur --%>
  <% 
    // Récupération des messages de succès ou d'erreur
    String message = (String) request.getSession().getAttribute("message");
    request.getSession().removeAttribute("message");
  %>
  <% if (message != null) { %>
    <div style="text-align: center; margin-top: 20px;">
      <h2><%= message %></h2>
    </div>
  <% } %>

  <% 
    String messageS = (String) request.getSession().getAttribute("messageS");
    request.getSession().removeAttribute("messageS");
  %>
  <% if (messageS != null) { %>
    <div style="text-align: center; margin-top: 20px;">
      <h2><%= messageS %></h2>
    </div>
  <% } %>

  <% 
    String messageL = (String) request.getSession().getAttribute("messageL");
    request.getSession().removeAttribute("messageL");
  %>
  <% if (messageL != null) { %>
    <div style="text-align: center; margin-top: 20px;">
      <h2><%= messageL %></h2>
    </div>
  <% } %>

  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <script src="home.js"></script>
  
  <!-- Script JavaScript -->
  <script>
    // Fonction pour charger la section des articles
    function loadArticle() {
        var clientContainer = document.getElementById("ajouterClientContainer");
        var factureContainer = document.getElementById("ajouterFactureContainer");
        var articleContainer = document.getElementById("ajouterArticleContainer");

        // Effacer le contenu des autres conteneurs
        factureContainer.innerHTML = "";
        clientContainer.innerHTML = "";

        var xhr = new XMLHttpRequest();
        xhr.open("GET", "AffichageArticle", true); 
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                factureContainer.innerHTML = xhr.responseText;
            }
        };
        xhr.send();
    }
    
    // Fonction pour charger la section des factures
    function loadFacture() {
        var clientContainer = document.getElementById("ajouterClientContainer");
        var factureContainer = document.getElementById("ajouterFactureContainer");
        var articleContainer = document.getElementById("ajouterArticleContainer");

        // Effacer le contenu des autres conteneurs
        articleContainer.innerHTML = "";
        clientContainer.innerHTML = "";
  
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "AffichageFactures", true); 
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                factureContainer.innerHTML = xhr.responseText;
            }
        };
        xhr.send();
    }

    // Fonction pour charger la section des clients
    function loadClients() {
        var container = document.getElementById("ajouterClientContainer");
        var factureContainer = document.getElementById("ajouterFactureContainer");
        var articleContainer = document.getElementById("ajouterArticleContainer");

        // Effacer le contenu des autres conteneurs
        factureContainer.innerHTML = "";
        articleContainer.innerHTML = "";

        var xhr = new XMLHttpRequest();
        xhr.open("GET", "afficherClient", true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                container.innerHTML = xhr.responseText;
            }
        };
        xhr.send();
    }

    // Fonction pour charger le formulaire d'ajout de client
    function loadAjouterClientForm() {
        var container = document.getElementById("ajouterClientContainer");
        var factureContainer = document.getElementById("ajouterFactureContainer");
        var articleContainer = document.getElementById("ajouterArticleContainer");

        // Effacer le contenu des autres conteneurs
        factureContainer.innerHTML = "";
        articleContainer.innerHTML = "";

        var xhr = new XMLHttpRequest();
        xhr.open("GET", "AjouterClient.jsp", true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                container.innerHTML = xhr.responseText;
            }
        };
        xhr.send();
    }

    // Fonction pour charger le formulaire d'ajout de facture
    function loadAjouterFactureForm() {
        var container = document.getElementById("ajouterFactureContainer");
        var clientContainer = document.getElementById("ajouterClientContainer");
        var articleContainer = document.getElementById("ajouterArticleContainer");

        // Effacer le contenu des autres conteneurs
        clientContainer.innerHTML = "";
        articleContainer.innerHTML = "";

        var xhr = new XMLHttpRequest();
        xhr.open("GET", "AjouterFacture.jsp", true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                container.innerHTML = xhr.responseText;
            }
        };
        xhr.send();
    }

    // Fonction pour charger le formulaire d'ajout d'article
    function loadAjouterArticleForm() {
        var container = document.getElementById("ajouterArticleContainer");
        var clientContainer = document.getElementById("ajouterClientContainer");
        var factureContainer = document.getElementById("ajouterFactureContainer");

        // Effacer le contenu des autres conteneurs
        clientContainer.innerHTML = "";
        factureContainer.innerHTML = "";

        var xhr = new XMLHttpRequest();
        xhr.open("GET", "AjouterArticle.jsp", true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                container.innerHTML = xhr.responseText;
            }
        };
        xhr.send();
    }
  </script>

</body>
</html>
