package com.sample.db;



import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 




import javax.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
 
 
@Component(immediate = true, metatype = true, name = "com.sample.db.DbConnector", label = "Db Connector")
@Properties({ @Property(name = "JDBC driver class", value = "com.mysql.jdbc.Driver"),
        @Property(name = "JDBC connection URI", value = "jdbc:mysql://localhost:3306/mydb"),
        @Property(name = "Username", value = "root"),
        @Property(name = "Password", value = "root")})

public class DbConnector extends SlingAllMethodsServlet{
  @Override
    protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse res) throws ServletException, IOException
    
    {
     
        
    	 PrintWriter out = res.getWriter();
         res.setContentType("text/html");
         out.println("<html><body>");
         try {
             Class.forName("com.mysql.jdbc.Driver");
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root", "root");
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from cars");
             out.println("<table border=1 width=50% height=50%>");
             out.println("<tr><th>Car Id</th><th>Car Name</th><th>Car Type</th><tr>");
             while (rs.next()) {
                 String t = rs.getString("car_type");
                 String name = rs.getString("car_name");
                 int id = rs.getInt("car_id"); 
                 out.println("<tr><td>" + id + "</td><td>" + name + "</td><td>" + t + "</td></tr>"); 
             }
             out.println("</table>");
             out.println("</html></body>");
             con.close();
            }
             catch (Exception e) {
             out.println("error");
             }
    	}
     
 
     private Object getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	protected void activate(ComponentContext ctx) {
    	 
        
     }
  
     protected void deactivate(ComponentContext ctx) {
        
     }
    
 
    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
 
    }  
 
}