
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/loginServlet"})
public class loginServlet extends HttpServlet {

   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Récupération des données du formulaire
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Vérification des données de connexion
        if (username.equals("admin") && password.equals("admin")) {
            // Si les données sont correctes, redirection vers la page d'accueil
            HttpSession ss = request.getSession (); 
            Object user = null;
            ss.setAttribute("user", username);
            Object pwd = null;
            ss.setAttribute("pwd", pwd);
            response.sendRedirect("home.jsp");
            
        } else {
            // Sinon, affichage d'un message d'erreur
            request.setAttribute("msg", "Invalid username or password");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }
    
}
