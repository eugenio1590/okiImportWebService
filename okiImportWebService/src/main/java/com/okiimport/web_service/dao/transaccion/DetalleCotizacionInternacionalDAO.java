package com.okiimport.web_service.dao.transaccion;

import java.util.List;

import com.okiimport.web_service.dao.IGenericDao;
import com.okiimport.web_service.modelo.DetalleCotizacionInternacional;

public interface DetalleCotizacionInternacionalDAO extends IGenericDao<DetalleCotizacionInternacional, Integer> {
	public List<DetalleCotizacionInternacional> consultarDetallesCotizacion(DetalleCotizacionInternacional detalleF, Integer idCotizacion, Integer idRequerimiento,
			boolean distinct,  boolean cantExacta, String fieldSort, Boolean sortDirection, int start, int limit);
}
