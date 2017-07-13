package com.itc.core.BundleDemo;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.osgi.service.component.ComponentContext;

@Component(immediate=true, metatype=true , label="My Osgi Component" ,description="simple demo")
@Service(MyOsgiComponent.class)
@Property(label="tick yes or no",name="osgiproperty",boolValue=false,description="this is the first property")

public class MyOsgiComponentImpl implements MyOsgiComponent {
	ComponentContext context;
	

	@Override
	public String checkbox() {
		// TODO Auto-generated method stub
		if(context.getProperties().get("osgiproperty").toString().equalsIgnoreCase("true"))
			return "Flag is checked";
		else

			return "Flag is not checked";
	}

	public void activate(ComponentContext context1) {
		context = context1;

	}
}
