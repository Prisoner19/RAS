package org.ruqu.ras.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.ruqu.ras.domain.Opcion;
import org.ruqu.ras.service.IOpcionService;

@ManagedBean(name="OpcionBean")
@ViewScoped
public class OpcionBean implements Serializable{

	/**
	 * Beans' attributes
	 * =================
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{OpcionService}")
	IOpcionService OpcionService;
	
	List<Opcion> Opcions;
	
	Opcion Opcion=new Opcion();
	Opcion OpcionNuevo=new Opcion();
	
	private boolean accionEditar = true;


	/* SETTER AND GETTERS
	*  ==================
	*/
	
	public IOpcionService getOpcionService() {
		return OpcionService;
	}

	public void setOpcionService(IOpcionService OpcionService) {
		this.OpcionService = OpcionService;
	}

	public List<Opcion> getOpcions() {
		return Opcions;
	}

	public void setOpcions(List<Opcion> Opcions) {
		this.Opcions = Opcions;
	}

	public Opcion getOpcion() {
		return Opcion;
	}

	public void setOpcion(Opcion Opcion) {
		this.Opcion = Opcion;
	}

	public Opcion getOpcionNuevo() {
		return OpcionNuevo;
	}

	public void setOpcionNuevo(Opcion OpcionNuevo) {
		this.OpcionNuevo = OpcionNuevo;
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
			return "Editar";
		}else{
			return "Nuevo";
		}
	}


 	/* CONSTRUCTOR AND POSTCONSTRUCT 
	*  ============================
 	*/
	
	public OpcionBean(){
		Opcions=new ArrayList<Opcion>();
	}
	
	@PostConstruct
	public void init(){
		Opcions=getOpcionService().getOpcions();
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
			if(getOpcionNuevo()!=null){
				setOpcion(getOpcionService().getOpcionById(getOpcionNuevo().getIdOpcion()));
				RequestContext.getCurrentInstance().execute("dialog.show()");
			}
			else{
				FacesMessage msg = null;  
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro");  
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			accionEditar=false;
		}
	}
	
	public void eliminarEvent(){
		if(getOpcionNuevo()!=null){
			RequestContext.getCurrentInstance().execute("confirmation.show()");
		}
		else{
			FacesMessage msg = null;  
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro");  
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	//BOTON PROCESAR-DIALOG
	public void procesarDialog(){
		if(accionEditar){
			System.out.println("llegue");
			validarEditar();
		}else{
			validarNuevo();
		}
	}
	
	/* ACCIONES CRUD
	*  =============
	*/
	
	public void insertar(){
		getOpcionService().addOpcion(getOpcion());
		refrescarOpcions();
		limpiarCampos();
	}
	
	public void editar(){
		//getOpcion().setIdOpcion(getOpcion().getIdOpcion());
		getOpcionService().updateOpcion(getOpcion());
		refrescarOpcions();
		limpiarCampos();
	}
	
	public void eliminar(){
		getOpcionService().deleteOpcionLogico(getOpcionNuevo());
		refrescarOpcions();
	}
	
	public void validarNuevo(){
		RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean registrado = false;  
          
        if(getOpcion().getDescripcion()!=null && getOpcion().getDescripcion().length()>=1){
        	registrado=true;
        	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", getOpcion().getDescripcion());
            insertar();
        } else {  
        	registrado = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de registro", "Campo(s) invalido(s)");  
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("registrado", registrado);
	}
	
	public void validarEditar(){
		RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean editado = false;  
        
        if(getOpcion().getDescripcion()!=null && getOpcion().getDescripcion().length()>=1){
        	editado=true;
        	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado", getOpcion().getDescripcion());
            editar();
        }else if(getOpcionNuevo()==null){
        	editado = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro");  
        }else{
        	editado = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Nombre invalido");  
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("editado", editado);  
	}
	
	public void validarEliminar(){
		RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean eliminado = false;  
        
        if(getOpcionNuevo()!=null){
        	eliminado = true;  
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", getOpcionNuevo().getDescripcion());
            eliminar();
        } else {  
        	eliminado = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro");  
        }  
          
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("eliminado", eliminado);  
	}
	/*
	*	RUTINAS ADICIONALES
	*	===================
    */


	public void limpiarCampos()
	{
		Opcion= new Opcion();		
		OpcionNuevo = new Opcion();
	}

	private void refrescarOpcions()
	{
		setOpcions(getOpcionService().getOpcions());
		
	}
}
