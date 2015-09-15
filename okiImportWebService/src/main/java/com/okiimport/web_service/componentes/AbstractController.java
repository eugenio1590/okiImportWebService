package com.okiimport.web_service.componentes;

import org.springframework.beans.factory.annotation.Autowired;

import com.okiimport.app.service.mail.MailService;

public abstract class AbstractController {
	
	@Autowired
	protected MailService mailService;
	
	protected int defaultPage(Integer value){
		return (value == null) ? 0 : value;
	}
	
	protected int defaultLimit(Integer value){
		return (value==null) ? -1 : value;
	}

}
