package src.java;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/SupprimerClientServlet")
public class SupprimerClientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupération du paramètre contenant l'ID du client à supprimer
        int idClient = Integer.parseInt(request.getParameter("idClient"));

        try {
            // Connexion à la base de données
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost/projetcawa", "root", "");

            // Suppression des factures associées au client
            String sqlDeleteFactures = "DELETE FROM facture WHERE idClient = ?";
            PreparedStatement statementDeleteFactures = c.prepareStatement(sqlDeleteFactures);
            statementDeleteFactures.setInt(1, idClient);
            int rowsDeletedFactures = statementDeleteFactures.executeUpdate();
            statementDeleteFactures.close();

            // Suppression du client
            String sqlDeleteClient = "DELETE FROM client WHERE idClient = ?";
            PreparedStatement statementDeleteClient = c.prepareStatement(sqlDeleteClient);
            statementDeleteClient.setInt(1, idClient);
            int rowsDeletedClient = statementDeleteClient.executeUpdate();
            statementDeleteClient.close();

            if (rowsDeletedClient > 0) {
                // Suppression réussie
                String messageS = "Client supprimé avec succès.";
                 request.getSession().setAttribute("messageS", messageS);
                 response.sendRedirect("home.jsp");

            } else {
                // Aucun client supprimé (ID invalide ou client inexistant)
                String message = "Impossible de supprimer le client.";
                response.sendRedirect("home.jsp?message=" + message);
            }

            // Fermeture de la connexion
            c.close();
        } catch (SQLException e) {
            // Gestion des exceptions SQL
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SupprimerClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
