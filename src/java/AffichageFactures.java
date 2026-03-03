package src.java;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(urlPatterns = "/AffichageFactures")
public class AffichageFactures extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/projetcawa?useSSL=false", "root", "")) {
                String sql = "SELECT * FROM facture";
                try (PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
                    out.print("<html><body><table border='3'>");
                    out.print("<tr><th>Numero Facture</th><th>Id Client</th><th>Date</th><th>Mode paiement</th><th>Supprimer</th></tr>");
                    while (rs.next()) {
                        out.print("<tr>");
                        out.print("<td>" + rs.getString("numF") + "</td>");
                        out.print("<td>" + rs.getString("idClient") + "</td>");
                        out.print("<td>" + rs.getString("date") + "</td>");
                        out.print("<td>" + rs.getString("modePaiement") + "</td>");
                        out.print("<td><a href='SupprimerFactureServlet?numF=" + rs.getInt("numF") + "'>Supprimer</a></td>");
                        out.print("</tr>");
                    }
                    out.print("</table></body></html>");
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}