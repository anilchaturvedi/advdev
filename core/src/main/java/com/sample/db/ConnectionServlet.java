package com.sample.db;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import com.sample.db.DbConnector;

@SlingServlet(paths="/call/demoservlet",methods="get")
public class ConnectionServlet extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 1L;
	@Reference
	DbConnector service;
	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
		response.getWriter().print(service.toString());
		
		
	}

}
