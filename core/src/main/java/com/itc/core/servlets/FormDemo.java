package com.itc.core.servlets;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
@SlingServlet(paths="/form",methods="get")
public class FormDemo extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String age=request.getParameter("age");
		String gender=request.getParameter("gender");
		String address=request.getParameter("address");
		
		response.getWriter().println("name:"+name);
		response.getWriter().println("age:"+age);
		response.getWriter().println("gender:"+gender);
		response.getWriter().println("address:"+address);
		
		//response.getWriter().print(service.checkbox());
	}
}
