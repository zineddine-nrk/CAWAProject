package src.java;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/AjouterArticleServlet")
public class AjouterArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Récupérer les paramètres de la requête
            int refArticle = Integer.parseInt(request.getParameter("refArticle"));
            
            String userInputtedNomArticle = request.getParameter("nomArticle");
            if (!isValidUsername(userInputtedNomArticle)) {
                throw new ServletException("Invalid article name.");  // Vérifier la validité du nom
            }
            
            int quantite = Integer.parseInt(request.getParameter("quantite"));
            if (quantite <= 0) {
                throw new ServletException("Invalid quantity.");  // Vérifier la validité de la quantité (doit être supérieure à zéro)
            }
            
            int prix = Integer.parseInt(request.getParameter("prixVente"));
            if (prix <= 0) {
                throw new ServletException("Invalid selling price.");  // Vérifier la validité du prix de vente (doit être supérieur à zéro)
            }

            Class.forName("com.mysql.jdbc.Driver");
            
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/projetcawa", "root", "")) {
                // Préparation de la requête SQL pour ajouter un nouvel article
                String sql = "INSERT INTO article (refArticle, nomArticle, quantite, prixVente) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                
                // Définir les valeurs des paramètres de la requête
                statement.setInt(1, refArticle);
                statement.setString(2, userInputtedNomArticle);
                statement.setInt(3, quantite);
                statement.setInt(4, prix); 
                
                // Exécuter la requête d'insertion
                statement.executeUpdate();
            }
            
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            request.getSession().setAttribute("message", "Article ajouté avec succès");
            response.sendRedirect("home.jsp");  // Rediriger vers la page d'accueil après l'ajout de l'article
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Une erreur s'est produite lors de l'ajout de l'article.");
        }
    }

    private boolean isValidUsername(String username) {
        return username != null && !username.matches("[^a-zA-Z0-9]");  // Vérifier si le nom est composé uniquement de lettres et de chiffres
    }
}
