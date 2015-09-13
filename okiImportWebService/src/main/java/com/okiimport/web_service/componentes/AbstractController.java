package com.okiimport.web_service.componentes;

public abstract class AbstractController {
	
	protected int defaultPage(Integer value){
		return (value == null) ? 0 : value;
	}
	
	protected int defaultLimit(Integer value){
		return (value==null) ? -1 : value;
	}

}
