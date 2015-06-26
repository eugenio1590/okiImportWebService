package com.okiimport.web_service.lib;

import java.util.List;

public interface ModelNavbar {
	public String getLabel();
	public String getIcon();
	public String getUriLocation();
	public List<ModelNavbar> getChilds();
}
