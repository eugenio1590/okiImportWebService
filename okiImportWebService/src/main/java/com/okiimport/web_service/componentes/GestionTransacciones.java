package com.okiimport.web_service.componentes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okiimport.app.model.DetalleRequerimiento;
import com.okiimport.app.model.Requerimiento;
import com.okiimport.app.service.maestros.SMaestros;
import com.okiimport.app.service.mail.MailCliente;
import com.okiimport.app.service.transaccion.STransaccion;

@Controller
public class GestionTransacciones extends AbstractController{
	
	@Autowired
	protected MailCliente mailCliente;
	
	@Autowired
	private SMaestros sMaestros;

	@Autowired
	private STransaccion sTransaccion;
	
	//Requerimientos
	@RequestMapping(value = "/rest/gestionTransacciones/requerimientos/{idRequerimiento}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> consultarRequerimiento(
			@PathVariable("idRequerimiento") Integer idRequerimiento){
		Map<String, Object> parametros = new HashMap<String, Object>();
		Requerimiento requerimiento = consultarRequrimiento(idRequerimiento);
		if(requerimiento!=null)
			parametros.put("requerimiento", requerimiento);
		else
			parametros.put("requerimiento", null);
		return parametros;
	}
	
	@RequestMapping(value = "/rest/gestionTransacciones/requerimientos/{idRequerimiento}/detallesRequerimientos", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> consultarDetallesRequerimientos(
			@PathVariable("idRequerimiento") Integer idRequerimiento){
		Map<String, Object> parametros = new HashMap<String, Object>();
		Requerimiento requerimiento = consultarRequrimiento(idRequerimiento);
		if(requerimiento!=null)
			parametros.put("detallesRequerimientos", requerimiento.getDetalleRequerimientos());
		else
			parametros.put("detallesRequerimientos", new ArrayList<DetalleRequerimiento>());
		return parametros;
	}
	
	@RequestMapping(value = "/rest/gestionTransacciones/requerimientos/clientes/{cedula}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> consultarRequerimientos(
			@PathVariable("cedula") String cedula,
			@DefaultValue("0") @QueryParam("pagina") Integer pagina,
			@QueryParam("limite") Integer limite){
		return sTransaccion.ConsultarRequerimientosCliente(null, null, null, cedula, 
				defaultPage(pagina), defaultLimit(limite));
	}
	
	@RequestMapping(value = "/rest/gestionTransacciones/requerimientos", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> registrarRequerimiento(
			@RequestBody Requerimiento requerimiento){
		Map<String, Object> parametros = new HashMap<String, Object>();
		requerimiento=sTransaccion.registrarRequerimiento(requerimiento, sMaestros);
		parametros.put("requerimiento", requerimiento);
		if(requerimiento!=null){
			try {
				mailCliente.registrarRequerimiento(requerimiento, mailService);
			}
			catch(Exception e){
				return parametros;
			}
		}
		return parametros;
	}
	
	@RequestMapping(value = "/rest/gestionTransacciones/requerimientos/{idRequerimiento}/detalleRequerimiento", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> registrarDetalleRequerimiento(
			@PathVariable("idRequerimiento") int idRequerimiento,
			@RequestBody DetalleRequerimiento detalleRequerimiento){
		Map<String, Object> parametros = new HashMap<String, Object>();
		detalleRequerimiento=sTransaccion.registrarDetalleRequerimiento(idRequerimiento, detalleRequerimiento);
		parametros.put("detalleRequerimiento", detalleRequerimiento);
		return parametros;
	}
	
	/**METODOS PROPIOS DE LA CLASE*/
	@SuppressWarnings("unchecked")
	private Requerimiento consultarRequrimiento(int idRequerimiento){
		Map<String, Object> mapRequermientos = sTransaccion.consultarRequerimientosGeneral(new Requerimiento(idRequerimiento), null, null, 0, 1);
		List<Requerimiento> requerimientos = (List<Requerimiento>) mapRequermientos.get("requerimientos");
		if(requerimientos!=null && requerimientos.size()>0)
			return requerimientos.get(0);
		else
			return null;
	}

}
