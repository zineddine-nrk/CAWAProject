import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
