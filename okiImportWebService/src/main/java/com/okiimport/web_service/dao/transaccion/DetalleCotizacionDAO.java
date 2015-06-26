package com.okiimport.web_service.dao.transaccion;

import java.util.List;

import com.okiimport.web_service.dao.IGenericDao;
import com.okiimport.web_service.modelo.DetalleCotizacion;

public interface DetalleCotizacionDAO extends IGenericDao<DetalleCotizacion, Integer> {
	public List<DetalleCotizacion> consultarDetallesCotizacion(DetalleCotizacion detalleF, Integer idCotizacion, Integer idRequerimiento,
			boolean distinct,  boolean cantExacta, String fieldSort, Boolean sortDirection, int start, int limit);
	public List<DetalleCotizacion> ConsultarDetalleCotizacion(int idCotizacion, int page, int limit);
	
}
