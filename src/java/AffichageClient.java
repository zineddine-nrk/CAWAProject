package src.java;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.annotation.WebServlet;



@WebServlet(urlPatterns="/afficherClient")
public class AffichageClient extends HttpServlet
{
    public void doGet(HttpServletRequest r, HttpServletResponse s)
    throws IOException
    {
        try
        {           
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost/projetcawa","root","");
            PreparedStatement pst = c.prepareStatement("select * from client;");

            PrintWriter p = s.getWriter();
            p.print("<html><body>"
                   
                    + "<table border='3'>");            
            ResultSet rs = pst.executeQuery();
            p.print("<tr>");
               
                p.print("<th>Id Client</th>");
                p.print("<th>Nom</th>");
                p.print("<th>Telephone</th>");
                p.print("<th>Email</th>");
                p.print("<th>Supprimer</th>");
                
                p.print("</tr>");
            while(rs.next())
            {
                
                for(int i=1; i<=4; i++)
                {  
                    
                    p.print(" <td> "+rs.getString(i)+"  </td>");
                    
                    
                    
                 
                }
                
                 p.print( "<td><a href= 'SupprimerClientServlet?idClient=" + rs.getInt("idClient") + "'> Supprimer</a></td>");
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