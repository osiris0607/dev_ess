/*******************************************************************************
 *
 * Copyright �뱬 2019 namu C&D corp. All rights reserved.
 *
 * This is a proprietary software of namu C&D corp, and you may not use this file except in
 * compliance with license agreement with namu C&D corp. Any redistribution or use of this
 * software, with or without modification shall be strictly prohibited without prior written
 * approval of namu C&D corp, and the copyright notice above does not evidence any actual or
 * intended publication of such software.
 *
 *******************************************************************************/
package com.anchordata.webframework;

import java.util.Properties;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class ServiceEnvironmentPostProcessor implements EnvironmentPostProcessor {
	
	private static final String DEFAULT_MY_FRAMEWORK_PROPERTIES 			= "NamuProperties";
	private static final String DEFAULT_MY_FRAMEWORK_PROPERTIES_LOCATION 	= "/config/properties.yml";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
      
    	String location = System.getProperty("prprty.location");
        
    	Resource resource = null;
    	if (location != null) {    	
    		resource = new PathResource(location);
    	} else { 
    		resource = new ClassPathResource(DEFAULT_MY_FRAMEWORK_PROPERTIES_LOCATION);
    	}
        String[] profiles = environment.getActiveProfiles();        
        load(environment, resource, null);
        
        if (profiles.length > 0) {
            for (String profile : profiles) {
                load(environment, resource, profile);
            }
        }
    }

    private void load(ConfigurableEnvironment environment, Resource resource, String profile) {
        String propertyName = DEFAULT_MY_FRAMEWORK_PROPERTIES;
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
    	if (profile != null) {
    		propertyName = DEFAULT_MY_FRAMEWORK_PROPERTIES.concat(propertyName).concat("_").concat(profile);
    	}       	
		yaml.setResources(resource);
		Properties properties = yaml.getObject();
		PropertiesPropertySource propertySource = new PropertiesPropertySource(propertyName, properties);
         
      
		if (propertySource != null) {
			 environment.getPropertySources().addLast(propertySource);
		}
    }
}