package src.java;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/SupprimerArticleServlet")
public class SupprimerArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupération du paramètre contenant  à supprimer
     int refArticle = Integer.parseInt(request.getParameter("refArticle"));

        

        try {
           // Connexion à la base de données
               Class.forName("com.mysql.jdbc.Driver");
               Connection c = DriverManager.getConnection("jdbc:mysql://localhost/projetcawa?useSSL=false","root",
"");

            // Préparation de la requête SQL pour supprimer le client
            String sql = "DELETE FROM article WHERE refArticle = ?";
            PreparedStatement statement = c.prepareStatement(sql);
           
            statement.setInt(1, refArticle);

            // Exécution de la requête SQL
            int rowsDeleted = statement.executeUpdate();
          
            if (rowsDeleted > 0) {
                // Suppression réussie
                String messageS = "Article supprimé avec succès.";
                 request.getSession().setAttribute("messageS", messageS);
                 response.sendRedirect("home.jsp");
                
            } else {
                // Aucun client supprimé (ID invalide ou client inexistant)
                String message = "Article n'est été pas supprimé avec succès.";
                response.sendRedirect("home.jsp?message=" + message);
                response.setContentType("text/html");
                PrintWriter p = response.getWriter();
                p.println("<html><body><h1>" + message + "</h1></body></html>") ;
            }

            // Fermeture des ressources
            statement.close();
            c.close();
        } catch (SQLException e) {
            // Gestion des exceptions SQL
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SupprimerClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
}
