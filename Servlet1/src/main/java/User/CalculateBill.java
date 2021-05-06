package User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

@WebServlet(name = "CalculateBill", urlPatterns = {"/CalculateBill"})
public class CalculateBill extends HttpServlet 
{


    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try
        {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet </title>");            
            out.println("</head>");
            out.println("<body>");
            
            String name = request.getParameter("userName");
            String item[] = request.getParameterValues("item");
            
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/grocery_store_db?useSSL=false","","");
  
            Statement stmt = con.createStatement();
            String sql;
            
            int totalprice=0;
            for(String s:item)
            {
                try
                {
                sql = "SELECT  item_price,item_name FROM grocery_store_db where item_name=s";
                ResultSet rs = stmt.executeQuery(sql);    
                    while(rs.next())
                    {
                        System.out.print(", Price: " + rs.getInt("item_price"));
                        System.out.println(", ItemName: " + rs.getString("item_name"));
                        totalprice=totalprice+rs.getInt("item_price");
                    }
                }
                catch(Exception e)
                {
                    
                }
            }
            out.println("<h1>ABC.Ltd</h1>");
            out.println("Name:"+name);
            out.println("<br>");
            out.println("Items:<li>"+item+"</li>");
            out.println("<br>");
            out.println("Amount to be Paid:Rs.:"+totalprice);
            out.println("</body>");
            out.println("</html>");
        }
    catch(Exception e)
    {
        
    }
}
}