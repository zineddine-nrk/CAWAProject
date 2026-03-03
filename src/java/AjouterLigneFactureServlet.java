package src.java;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/AjouterLigneFactureServlet")
public class AjouterLigneFactureServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupération des paramètres du formulaire
        int refArticle = Integer.parseInt(request.getParameter("refArticle"));
        int numF = Integer.parseInt(request.getParameter("numF"));
        int quantiteVendue = Integer.parseInt(request.getParameter("quantiteVendue"));

        try {
            // Chargement du driver JDBC
            Class.forName("com.mysql.jdbc.Driver");

            // Connexion à la base de données
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/projetcawa?useSSL=false", "root", "")) {
                // Vérifier que l'article existe
                try (PreparedStatement checkArticle = conn.prepareStatement("SELECT refArticle FROM article WHERE refArticle = ?")) {
                    checkArticle.setInt(1, refArticle);
                    try (java.sql.ResultSet rs = checkArticle.executeQuery()) {
                        if (!rs.next()) {
                            request.getSession().setAttribute("messageL", "Article introuvable: " + refArticle);
                            response.sendRedirect("LigneFacture.jsp");
                            return;
                        }
                    }
                }

                // Vérifier que la facture existe
                try (PreparedStatement checkFact = conn.prepareStatement("SELECT numF FROM facture WHERE numF = ?")) {
                    checkFact.setInt(1, numF);
                    try (java.sql.ResultSet rs2 = checkFact.executeQuery()) {
                        if (!rs2.next()) {
                            request.getSession().setAttribute("messageL", "Facture introuvable: " + numF);
                            response.sendRedirect("LigneFacture.jsp");
                            return;
                        }
                    }
                }

                // Préparation de la requête SQL pour ajouter une nouvelle ligne de facture
                String sql = "INSERT INTO ligne_facture (refArticle, numF, quantiteVendue) VALUES (?, ?, ?)";
                try (PreparedStatement statement = conn.prepareStatement(sql)) {
                    statement.setInt(1, refArticle);
                    statement.setInt(2, numF);
                    statement.setInt(3, quantiteVendue);
                    statement.executeUpdate();
                }
            }

            // Redirection vers une page de succès
            request.getSession().setAttribute("messageL", "Ligne facture ajouté avec succès");
            response.sendRedirect("home.jsp");

        } catch (ClassNotFoundException | SQLException e) {
            // Gestion des exceptions
            e.printStackTrace();
            request.getSession().setAttribute("messageL", "Erreur: " + e.getMessage());
            response.sendRedirect("error.jsp?message=Erreur lors de la connexion à la base de données");
        }
    }
}
