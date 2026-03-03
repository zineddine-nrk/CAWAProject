package src.java;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/AjouterClient")
public class add extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nom = request.getParameter("nom");
            String telephone = request.getParameter("telephone");
            String email = request.getParameter("email");

            if (!isValidInput(nom) || !isValidInput(telephone) || !isValidInput(email)) {
                throw new ServletException("Invalid input");
            }

            Class.forName("com.mysql.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/projetcawa?useSSL=false", "root", "")) {
                String sql = "INSERT INTO client (nomClient, telephone, email) VALUES (?, ?, ?)";
                try (PreparedStatement pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    pst.setString(1, nom);
                    pst.setString(2, telephone);
                    pst.setString(3, email);
                    pst.executeUpdate();
                    try (ResultSet rs = pst.getGeneratedKeys()) {
                        if (rs.next()) {
                            int idClient = rs.getInt(1);
                        }
                    }
                }
            }

            request.getSession().setAttribute("message", "Client ajouté avec succès");
            response.sendRedirect("home.jsp");
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException(e);
        }
    }

    private boolean isValidInput(String s) {
        return s != null && !s.trim().isEmpty();
    }
}

