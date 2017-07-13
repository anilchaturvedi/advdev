package com.itc.core.BundleDemo;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;

@Component(immediate=true,metatype=true,label="Application Form Demo",description="Application Form")
@Service(ApplicationFormDemo.class)

public class ApplicationFormDemoImp implements ApplicationFormDemo {

	@Override
	public String display() {
		// TODO Auto-generated method stub
		return null;
	}

}
