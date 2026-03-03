
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/AffichageArticle"})
public class AffichageArticle extends HttpServlet {

   
       public void doGet(HttpServletRequest r, HttpServletResponse s)
    throws IOException
    {
        try
        {           
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost/projetcawa","root","");
            PreparedStatement pst = c.prepareStatement("select * from article;");

            PrintWriter p = s.getWriter();
            p.print("<html><body>"
                 
                    + "<table border='3'>");            
            ResultSet rs = pst.executeQuery();
             p.print("<tr>");
               
               
                p.print("<th>Reference Article</th>");
                p.print("<th>Nom Article</th>");
                p.print("<th>Quantite</th>");
                p.print("<th>Prix de vente</th>");
                p.print("</tr>");
            while(rs.next())
            {
               
                for(int i=1; i<=4; i++)
                {
                    p.print("<td>"+rs.getString(i)+"</td>");
                }
                p.print( "<td><a href= 'SupprimerArticleServlet?refArticle=" + rs.getInt("refArticle") + "'> Supprimer</a></td>");
                p.print("</tr>");
            }
            p.print("</table></body></html>");
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
        
    }

}
