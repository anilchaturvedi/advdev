package com.itc.core.servlets;

import com.day.cq.commons.jcr.JcrUtil;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 


import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;
 
 
@Component(immediate = true, metatype = true, name = "com.itc.core.servlets.CreateServlet", label = "Create Servlet", description = "Test creation servlet")
@SlingServlet(methods = { "POST" }, paths = "/apps/my/servlets/path/create", generateComponent = false)
@Properties({ @Property(name = "service.description", value = "CreateServlet"),
        @Property(name = "service.vendor", value = "My Vendor") })
@SuppressWarnings({ "serial", "unused" })
public class CreateServlet extends SlingAllMethodsServlet{
 
    private static final String CREATE_PATH = "/content"; /// Set the base path here
 
    private static final Logger log = LoggerFactory.getLogger(CreateServlet.class);
    
    @Reference
    private SlingRepository repository;
 
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException
    {
        //We are not using this atm.. but you could 
    	 Session session = null;
         String name=request.getParameter("name");
 		String age=request.getParameter("age");
 		String gender=request.getParameter("gender");
 		String address=request.getParameter("address");
 		
  
         PrintWriter out = response.getWriter();
  
  
         
  
         try {
             session = repository.loginAdministrative(null);
             Node firstProduct = JcrUtil.createPath(CREATE_PATH + "/" + name , "cq:Page", session);
  
  
             Node firstSubProduct = JcrUtil.createPath(firstProduct.getPath() + "/DETAILS", "nt:unstructured", session);
            
  
             firstSubProduct.setProperty("name", name);
             firstSubProduct.setProperty("age", age);
  
             firstSubProduct.setProperty("gender", gender);
             firstSubProduct.setProperty("address", address);
           
             session.save();
             out.println("That went well...");
             out.flush();
             out.close();
         } catch (RepositoryException e) {
             out.println("That went not so well...");
             out.flush();
             out.close();
             e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
         }
  
     }
 
     protected void activate(ComponentContext ctx) {
    	 
         log.trace("Activating my small creation servlet");
         log.info("hi from servlet");
     }
  
     protected void deactivate(ComponentContext ctx) {
         log.trace("Deactivating my small creation servlet");
     }
    
 
    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
 
    }  
 
}