<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Ajouter un nouvel article</title>
    <link rel="stylesheet" type="text/css" href="article.css">
    <style>
        form {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        
        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }
        
        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        
        input[type="submit"] {
            padding: 10px 20px;
            background-color: #4caf50;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        h3{
            padding-left: 160px;
            
        }
        
        .art {
            padding-left: 370px;
            padding-top: 10px;
        }
    </style>
</head>

<body>
    <div class="art">
        <h3>Ajouter un nouvel article :</h3>
    
        <form method="get" action="AjouterArticleServlet">
            <label for="refArticle">Référence Article :</label>
            <input type="number" id="refArticle" name="refArticle"><br>
            
            <label for="nomArticle">Nom article :</label>
            <input type="text" id="nomArticle" name="nomArticle"><br>
            
            <label for="quantite">Quantité :</label>
            <input type="number" id="quantite" name="quantite"><br>
            
            <label for="prixVente">Prix de vente :</label>
            <input type="number" id="prixVente" name="prixVente"><br>
            
            <input type="submit" value="Ajouter">
        </form>
    </div>
</body>
</html>
