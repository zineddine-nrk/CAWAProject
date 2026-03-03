import java.io.*;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/AjouterClient")

/* une servlet pour ajouter un clinet*/
public class add extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Récupérer les paramètres de la requête
            String userInputtedNom = request.getParameter("nom");
            if (!isValidUsername(userInputtedNom)) {
                throw new ServletException("Invalid name.");  // Vérifier la validité du nom
            }

            String userInputtedTelephone = request.getParameter("telephone");
            if (!isValidUsername(userInputtedTelephone)) {
                throw new ServletException("Invalid telephone.");  // Vérifier la validité du numéro de téléphone
            }
            
            String userInputtedEmail = request.getParameter("email");
            if (!isValidUsername(userInputtedEmail)) {
                throw new ServletException("Invalid Email.");  // Vérifier la validité de l'e-mail
            }

            Class.forName("com.mysql.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/projetcawa", "root", "")) {
                // Préparer la requête SQL pour insérer un nouveau client dans la base de données
                String sql = "INSERT INTO client (nomClient, telephone, email) VALUES (?, ?, ?)";
                PreparedStatement pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

                // Définir les valeurs des paramètres de la requête
                pst.setString(1, userInputtedNom);
                pst.setString(2, userInputtedTelephone);
                pst.setString(3, userInputtedEmail);

                // Exécuter la requête d'insertion
                pst.executeUpdate();

                ResultSet rs = pst.getGeneratedKeys();
                int idClient = 0;
                if (rs.next()) {
                    idClient = rs.getInt(1);  // Récupérer l'ID généré pour le nouveau client
                }
            }

            request.getSession().setAttribute("message", "Client ajouté avec succès");
            response.sendRedirect("home.jsp");  // Rediriger vers la page d'accueil après l'ajout du client
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Une erreur s'est produite lors de l'ajout du client.");
        }
    }

    private boolean isValidUsername(String username) {
        return username != null && !username.matches("[^a-zA-Z0-9]");  // Vérifier si le nom est composé uniquement de lettres et de chiffres
    }
}

