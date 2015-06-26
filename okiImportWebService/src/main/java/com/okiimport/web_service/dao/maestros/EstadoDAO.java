package com.okiimport.web_service.dao.maestros;

import java.util.List;

import com.okiimport.web_service.dao.IGenericDao;
import com.okiimport.web_service.modelo.Estado;

public interface EstadoDAO extends IGenericDao<Estado, Integer> {
	
	public List<Estado> listaEstadosActivos(Integer start, Integer limit);

}
