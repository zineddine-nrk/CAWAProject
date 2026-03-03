<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter une facture</title>
    <link rel="stylesheet" type="text/css" href="facture.css">
</head>
<body>
    <h2>Ajouter des lignes de facture</h2>
    <form method="get" action="AjouterLigneFactureServlet">
        <label for="refArticle">Référence de l'article :</label>
        <input type="number" id="refArticle" name="refArticle"><br><br>
        <label for="numF">Numéro facture:</label>
        <input type="number" id="numF" name="numF"><br><br>
        <label for="quantiteVendue">Quantité vendue :</label>
        <input type="number" id="quantiteVendue" name="quantiteVendue"><br><br>
        <input type="submit" value="Ajouter la ligne de facture">
    </form>
</body>
</html>
