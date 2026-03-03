import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/DeconnexionServlet")
public class DeconnexionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer la session
        HttpSession session = request.getSession(false);

        if (session != null) {
            // Détruire la session (se déconnecter)
            session.invalidate();
        }

        // Rediriger vers la page de connexion (ou une autre page de votre choix)
        response.sendRedirect("login.jsp");
    }
}
