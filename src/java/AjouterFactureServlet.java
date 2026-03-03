package src.java;

import java.io.*;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/AjouterFactureServlet")
public class AjouterFactureServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // Récupérer les paramètres de la requête
            int idClient = Integer.parseInt(request.getParameter("client"));
            LocalDate date = LocalDate.parse(request.getParameter("date"));
            String modePaiement = request.getParameter("modePaiement");

            Class.forName("com.mysql.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/projetcawa?useSSL=false", "root", "")) {
                // Vérifier la validité de l'ID client
                PreparedStatement clientStmt = conn.prepareStatement("SELECT idClient FROM client WHERE idClient = ?");
                clientStmt.setInt(1, idClient);
                ResultSet clientRs = clientStmt.executeQuery();

                if (clientRs.next()) {
                    // Préparation de la requête SQL pour ajouter une nouvelle facture
                    PreparedStatement pst = conn.prepareStatement("INSERT INTO facture (idClient, date, modePaiement) VALUES (?, ?, ?)");

                    // Définir les valeurs des paramètres de la requête
                    pst.setInt(1, idClient);
                    pst.setDate(2, java.sql.Date.valueOf(date));
                    pst.setString(3, modePaiement);

                    // Exécuter la requête d'insertion
                    pst.executeUpdate();
                } 
                
                clientRs.close();
                clientStmt.close();
            }

            
              
            response.sendRedirect("LigneFacture.jsp");  // Rediriger vers la page LigneFacture.jsp après l'ajout de la facture
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Une erreur s'est produite lors de l'ajout de la facture.");
        }
    }
}



        
        