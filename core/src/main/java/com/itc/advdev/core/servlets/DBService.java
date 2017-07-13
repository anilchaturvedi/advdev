package com.itc.advdev.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import com.itc.advdev.core.training.DBConnector;

@SlingServlet(paths = "/ford",methods="get")
public class DBService extends SlingAllMethodsServlet {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Reference
DBConnector dbcon;
	
protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws ServletException, IOException
{
		PrintWriter p=resp.getWriter();
		ResultSet r=dbcon.getConnection();
		p.print(r);
		if(r != null)
		{
		try {
			while(r.next())
			{
				p.print(r.getInt(1)+" "+r.getInt(2)+" "+r.getString(3)+" "+r.getInt(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			p.print(e);
			e.printStackTrace();
		}
		}
}
}

