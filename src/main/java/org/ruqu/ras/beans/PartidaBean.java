package org.ruqu.ras.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.ruqu.ras.domain.Partida;
import org.ruqu.ras.helpers.utils.FacesMessageHelper;
import org.ruqu.ras.service.IPartidaService;

@ManagedBean(name="PartidaBean")
@ViewScoped
public class PartidaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String growlPath ="form:growl";
	
	@ManagedProperty(value="#{PartidaService}")
	IPartidaService partidaService;
	
	List<Partida> partidas;
	
	Partida partida=new Partida();
	Partida partidaNuevo=new Partida();
	
	private boolean accionEditar = true;	

 	/* CONSTRUCTOR AND POSTCONSTRUCT 
	*  ============================
 	*/
	
	public PartidaBean(){
		partidas=new ArrayList<Partida>();
	}
	
	@PostConstruct
	public void init(){
		partidas=getPartidaService().getAll();
	}
	
	/* AJAX BUTTON EVENTS  
	*  ==================
	*/

	
	public void nuevoEvent(){
		accionEditar=false;
		limpiarCampos();
	}
	
	public void editarEvent(){
		accionEditar=true;
		try{
			if(getPartidaNuevo()!=null){
				setPartida(getPartidaService().getById(getPartidaNuevo().getIdPartida()));
				RequestContext.getCurrentInstance().execute("dialog.show()");
			}
			else{
				FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro", growlPath);
			}
		}catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			accionEditar=false;
		}
	}
	
	public void eliminarEvent(){
		if(getPartidaNuevo()!=null){
			validarEliminar();
		}
		else{
			FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro", growlPath);
		}
	}
	
	public void onEdit(RowEditEvent event) {
		Partida c=(Partida)event.getObject();				
		setPartida(c);		
		editar();
		FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO, "Edicion Exitosa", ((Partida) event.getObject()).getDescripcion(), growlPath);
    }  
      
    public void onCancel(RowEditEvent event) {        
        FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO, "Edicion Cancelada", ((Partida) event.getObject()).getDescripcion(), growlPath);
    }
    
	//BOTON PROCESAR-DIALOG
	public void procesarDialog(){
		if(accionEditar){			
			editar();
		}else{
			insertar();
		}
		limpiarCampos();
		refrescarPartidas();
		RequestContext.getCurrentInstance().execute("dialog.hide();");
	}
	
	/* ACCIONES CRUD
	*  =============
	*/
	
	public void insertar(){
		getPartidaService().add(getPartida());		
	}
	
	public void editar(){		
		getPartidaService().update(getPartida());		
	}
	
	public void eliminar(){
		getPartidaService().delete(getPartidaNuevo());		
	}
	
	
	
	public void validarEliminar(){
		 
        
        if(getPartidaNuevo()!=null){
        	FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO, "Eliminado", getPartidaNuevo().getDescripcion(), growlPath);
            eliminar();
            limpiarCampos();
            refrescarPartidas();
        } else {  
        	FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro", growlPath);
   
        }          

	}
	/*
	*	RUTINAS ADICIONALES
	*	===================
    */
	public void showError(){
		FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_WARN, "Registro","Campos resaltados invalidos", growlPath
				,"Error");		
	}

	public void limpiarCampos()
	{
		partida= new Partida();		
		partidaNuevo = new Partida();
	}

	private void refrescarPartidas()
	{
		setPartidas(getPartidaService().getAll());
		
	}
	
	/* SETTER AND GETTERS
	*  ==================
	*/
	
	public IPartidaService getPartidaService() {
		return partidaService;
	}

	public void setPartidaService(IPartidaService partidaService) {
		this.partidaService = partidaService;
	}

	public List<Partida> getPartidas() {
		return partidas;
	}

	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public Partida getPartidaNuevo() {
		return partidaNuevo;
	}

	public void setPartidaNuevo(Partida partidaNuevo) {
		this.partidaNuevo = partidaNuevo;
	}

	public boolean isAccionEditar() {
		return accionEditar;
	}

	public void setAccionEditar(boolean accionEditar) {
		this.accionEditar = accionEditar;
	}
	
    /* CUSTOM LABELS  */
	
	public String etiBotonDialog(){
		if(accionEditar){
			return "EDITAR";
		}else{
			return "NUEVO";
		}
	}


}


