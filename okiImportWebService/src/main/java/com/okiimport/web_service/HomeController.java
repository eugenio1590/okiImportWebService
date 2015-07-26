package com.okiimport.web_service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.properties.EncryptableProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		//cargarPropiedades();
		
		return "home";
	}
	
	private String getPath(){
		return this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath().split("WEB-INF")[0];
	}
	
	private void cargarPropiedades(){
		EnvironmentStringPBEConfig environmentVariablesConfiguration = new EnvironmentStringPBEConfig();
		environmentVariablesConfiguration.setAlgorithm("PBEWithMD5AndDES");
		environmentVariablesConfiguration.setPasswordEnvName("APP_ENCRYPTION_PASSWORD");
		/*
		  * First, create (or ask some other component for) the adequate encryptor for
		  * decrypting the values in our .properties file.
		  */
		 StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		 //encryptor.setPassword("euge17"); // could be got from web, env variable...
		 encryptor.setConfig(environmentVariablesConfiguration);
		 
		 /*
		  * Create our EncryptableProperties object and load it the usual way.
		  */
		 Properties props = new EncryptableProperties(encryptor);
		 try {
			props.load(new FileInputStream(getPath()+"WEB-INF/classes/jdbc.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 /*
		  * To get a non-encrypted value, we just get it with getProperty...
		  */
		 String datasourceUsername = props.getProperty("datasource.username");
		 System.out.println(datasourceUsername);

		 /*
		  * ...and to get an encrypted value, we do exactly the same. Decryption will
		  * be transparently performed behind the scenes.
		  */ 
		 String datasourcePassword = props.getProperty("datasource.password");
		 System.out.println(datasourcePassword);
	}
	
}
