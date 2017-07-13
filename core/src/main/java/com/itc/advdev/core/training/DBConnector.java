package com.itc.advdev.core.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(metatype=true,label="Db connector",immediate=true)
@Service(DBConnector.class)
@Properties({ @Property(name = "connector.driver", value = "com.mysql.jdbc.Driver"),
    @Property(name = "connection.uri", value = "jdbc:mysql://localhost:3306/ford"),
    @Property(name = "connection.user", value = "root"),
    @Property(name = "connection.password", value = "Admin123")})
public class DBConnector {
	
	static final Logger LOGGER = LoggerFactory.getLogger(DBConnector.class);

	String driver;
	String connectionURI;
	String username;
	String password;
	@Activate
	protected void activate(ComponentContext context){
		 driver= context.getProperties().get("connector.driver").toString();
		 LOGGER.info("");
		connectionURI= context.getProperties().get("connection.uri").toString();
		username= context.getProperties().get("connection.user").toString();
		password= context.getProperties().get("connection.password").toString();
		
		LOGGER.info("DB Connector Properties {}",driver+" :: "+connectionURI+" :: "+username+" :: "+password);
	}
	public ResultSet getConnection()
	{
		ResultSet rs=null;
		
		try{  
			Connection con=DriverManager.getConnection(connectionURI,username,password);
					
			if(con!=null){
				LOGGER.info("Connection created for {}",con.getSchema());
			}else{
				LOGGER.error("Connection is NULL");
			
			}
			
			Statement stmt=con.createStatement();  
			rs=stmt.executeQuery("select * from cars"); 
			if(rs == null){
				LOGGER.info("NO RESULT FOUND");
			}
			
			
			}catch(Exception e){
				LOGGER.error("Exception connecting to db",e);
				}
		
		
		
		return rs;
		
	}
}
