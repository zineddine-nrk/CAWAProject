package src.java;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/projetcawa", "root", "");

            // Préparation de la requête SQL pour ajouter une nouvelle ligne de facture
            String sql = "INSERT INTO ligne_facture (refArticle, numF, quantiteVendue) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, refArticle);
            statement.setInt(2, numF);
            statement.setInt(3, quantiteVendue);

            // Exécution de la requête SQL
            statement.executeUpdate();

            // Fermeture des ressources
            statement.close();
            conn.close();
            
            // Redirection vers une page de succès ou autre
            request.getSession().setAttribute("messageL", "Ligne facture ajouté avec succès");
             response.sendRedirect("home.jsp");
           
        } catch (ClassNotFoundException | SQLException e) {
            // Gestion des exceptions
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Erreur lors de la connexion à la base de données");
        }
    }
}
