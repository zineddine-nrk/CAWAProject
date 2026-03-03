

<%@page import=" com.mysql.jdbc.Driver"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajouter une facture</title>
<style>
    h3{
        padding-left: 135px;
    }
    .containerF{
        padding-left: 400px ;
        padding-top: 20px; 
    }
    
</style>
<link rel="stylesheet" type="text/css" href="facture.css">
</head>
<body>
    <div class="containerF">
    <h3>Ajouter une nouvelle facture :</h3>
    <form method="get" action="AjouterFactureServlet">
        
        
        <label for="client">Client :</label>
      <select id="client" name="client" required>
            <option value="" disabled selected>Sélectionnez un client</option>
            <%
          try {
                // Connexion à la base de données
                 Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/projetcawa?useSSL=false", "root", "");
                Statement stmt = conn.createStatement();

                // Requête SQL pour récupérer tous les clients
                String sql = "SELECT  idClient , nomClient,email FROM client";
                ResultSet rs = stmt.executeQuery(sql);
                
                // Affichage des clients dans la liste déroulante
                
                    
                    
                    while (rs.next()) {
                        int clientId = rs.getInt("idClient");
                        
                        String clientNom = rs.getString("nomClient");
                      
                       
                        out.println("<option value='" + clientId + "'>" + clientNom + "</option>");
               
               
                }
                
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } %>
        </select>
        <br>
      
        
        <label for="date">Date :</label>
        <input type="date" id="date" name="date" required>
        <br>   
        <label for="modePaiement">Mode de paiement :</label>
        <select id="modePaiement" name="modePaiement" required>
         <option value="" disabled selected>Sélectionnez un mode de paiement</option>
           <option value="Carte">Carte</option>
           <option value="Cash">Cash</option>
       </select>

        
        
        
        <input type="submit" value="Enregistrer">
    </form>
    </div>
</body>
</html>

