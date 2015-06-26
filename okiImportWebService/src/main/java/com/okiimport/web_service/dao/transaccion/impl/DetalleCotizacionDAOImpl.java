package com.okiimport.web_service.dao.transaccion.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;

import com.okiimport.web_service.dao.AbstractJpaDao;
import com.okiimport.web_service.modelo.Cotizacion;
import com.okiimport.web_service.modelo.DetalleCotizacion;
import com.okiimport.web_service.modelo.Proveedor;
import com.okiimport.web_service.dao.transaccion.DetalleCotizacionDAO;

public class DetalleCotizacionDAOImpl extends AbstractJpaDao<DetalleCotizacion, Integer> implements DetalleCotizacionDAO {

	public DetalleCotizacionDAOImpl() {
		super(DetalleCotizacion.class);
		// TODO Auto-generated constructor stub
	}

	public List<DetalleCotizacion> ConsultarDetalleCotizacion(int idCotizacion,
			int page, int limit) {
		// TODO Auto-generated method stub
		// 1. Creamos el Criterio de busqueda
		this.crearCriteria();

		// 2. Generamos los Joins
		Map<String, JoinType> entidades = new HashMap<String, JoinType>();
		entidades.put("cotizacion", JoinType.INNER);
		Map<String, Join> joins = this.crearJoins(entidades);

		List<Predicate> restricciones = new ArrayList<Predicate>();

		restricciones.add(criteriaBuilder.equal(
				joins.get("cotizacion").get("idCotizacion"), 
				idCotizacion));
		// 4. Creamos los campos de ordenamiento y ejecutamos
		List<Order> orders = new ArrayList<Order>();
		orders.add(criteriaBuilder.asc(entity.get("idDetalleCotizacion")));

		return ejecutarCriteria(concatenaArrayPredicate(restricciones), orders, page, limit);
	}
	
	public List<DetalleCotizacion> consultarDetallesCotizacion(DetalleCotizacion detalleF, Integer idCotizacion, Integer idRequerimiento,
			boolean distinct,  boolean cantExacta, String fieldSort, Boolean sortDirection, int start, int limit) {
		// TODO Auto-generated method stub
		// 1. Creamos el Criterio de busqueda
		this.crearCriteria();
		
		// 2. Generamos los Joins
		Map<String, JoinType> entidades = new HashMap<String, JoinType>();
		entidades.put("cotizacion", JoinType.INNER);
		entidades.put("detalleRequerimiento", JoinType.INNER);
		Map<String, Join> joins = this.crearJoins(entidades);
		
		if(distinct){
			this.distinct = distinct;
			this.selected = new Selection[]{
					this.entity.get("idDetalleCotizacion"),
					this.entity.get("marcaRepuesto"),
					this.entity.get("precioVenta"),
					this.entity.get("precioFlete"),
					this.entity.get("cantidad"),
					joins.get("cotizacion"),
					joins.get("detalleRequerimiento"),
			};
		}
		
		List<Predicate> restricciones = new ArrayList<Predicate>();

		if(idCotizacion!=null)
			restricciones.add(criteriaBuilder.equal(
					joins.get("cotizacion").get("idCotizacion"), 
					idCotizacion));
		
		if(idRequerimiento!=null)
			restricciones.add(criteriaBuilder.equal(
					joins.get("detalleRequerimiento").join("requerimiento").get("idRequerimiento"), 
					idRequerimiento));
		
		agregarRestricciones(detalleF, restricciones, joins, cantExacta);
		
		// 4. Creamos los campos de ordenamiento y ejecutamos
		List<Order> orders = new ArrayList<Order>();

		if (fieldSort != null && sortDirection != null)
				orders.add((sortDirection) ? this.criteriaBuilder.asc(this.entity.get(fieldSort)) : 
					this.criteriaBuilder.desc(this.entity.get(fieldSort)));
		
		List<Expression<?>> groupBy = null;
		
		if(distinct){
			groupBy = new ArrayList<Expression<?>>();
			groupBy.add(this.entity.get("idDetalleCotizacion"));
			groupBy.add(joins.get("cotizacion"));
			groupBy.add(this.entity.get("precioVenta"));
			groupBy.add(this.entity.get("precioFlete"));
			groupBy.add(this.entity.get("cantidad"));
			groupBy.add(joins.get("detalleRequerimiento"));
			groupBy.add(this.entity.get("marcaRepuesto"));
		}
		
		return ejecutarCriteriaOrder(concatenaArrayPredicate(restricciones), null, groupBy, orders, start, limit);
	}
	
	private void agregarRestricciones(DetalleCotizacion detalleF, List<Predicate> restricciones, Map<String, Join> joins, boolean cantExacta){
		if(detalleF!=null){
			if(detalleF.getMarcaRepuesto()!=null)
				restricciones.add(criteriaBuilder.like(
						criteriaBuilder.lower(this.entity.get("marcaRepuesto").as(String.class)),
						"%"+String.valueOf(detalleF.getMarcaRepuesto()).toLowerCase()+"%"));
			
			if(detalleF.getCantidad()!=null){
				if(cantExacta)
					restricciones.add(criteriaBuilder.like(
							criteriaBuilder.lower(this.entity.get("cantidad").as(String.class)),
							"%"+String.valueOf(detalleF.getCantidad()).toLowerCase()+"%"));
				else
					restricciones.add(criteriaBuilder.greaterThanOrEqualTo(this.entity.get("cantidad").as(Long.class), detalleF.getCantidad()));
			}
			
			if(detalleF.getPrecioVenta()!=null)
				restricciones.add(criteriaBuilder.like(
						criteriaBuilder.lower(this.entity.get("precioVenta").as(String.class)),
						"%"+String.valueOf(detalleF.getPrecioVenta()).replaceAll(".?0*$", "").toLowerCase()+"%"));
			
			if(detalleF.getPrecioFlete()!=null)
				restricciones.add(criteriaBuilder.like(
						criteriaBuilder.lower(this.entity.get("precioFlete").as(String.class)),
						"%"+String.valueOf(detalleF.getPrecioFlete()).replaceAll(".?0*$", "").toLowerCase()+"%"));
			
			//Cotizacion
			Cotizacion cotizacion = detalleF.getCotizacion();
			if(cotizacion!=null){
				if(cotizacion.getIdCotizacion()!=null)
					restricciones.add(criteriaBuilder.like(
							criteriaBuilder.lower(joins.get("cotizacion").get("idCotizacion").as(String.class)),
							"%"+String.valueOf(cotizacion.getIdCotizacion()).toLowerCase()+"%"));
				
				//Proveedor
				Proveedor proveedor = cotizacion.getProveedor();
				if(proveedor!=null){
					if(proveedor.getNombre()!=null)
						restricciones.add(criteriaBuilder.like(
								criteriaBuilder.lower(joins.get("cotizacion").join("proveedor").get("nombre").as(String.class)),
								"%"+String.valueOf(proveedor.getNombre()).toLowerCase()+"%"));
				}
			}
		}
	}
}
