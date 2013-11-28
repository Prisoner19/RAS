package org.ruqu.ras.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.ruqu.ras.domain.Otrogasto;
import org.ruqu.ras.service.IOtrogastoService;

@ManagedBean (name="OtrogastoBean")
@ViewScoped
public class OtrogastoBean {

	/**
	 * Beans' attributes
	 * =================
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{OtrogastoService}")
	IOtrogastoService OtrogastoService;
	
	List<Otrogasto> Otrogastos;
	
	Otrogasto otrogasto=new Otrogasto();
	Otrogasto otrogastoNuevo=new Otrogasto();
	
	private boolean accionEditar = true;


	/* SETTER AND GETTERS
	*  ==================
	*/
	
	public IOtrogastoService getOtrogastoService() {
		return OtrogastoService;
	}

	public void setOtrogastoService(IOtrogastoService OtrogastoService) {
		this.OtrogastoService = OtrogastoService;
	}

	public List<Otrogasto> getOtrogastos() {
		return Otrogastos;
	}

	public void setOtrogastos(List<Otrogasto> Otrogastos) {
		this.Otrogastos = Otrogastos;
	}

	public Otrogasto getOtrogasto() {
		return otrogasto;
	}

	public void setOtrogasto(Otrogasto otrogasto) {
		this.otrogasto = otrogasto;
	}

	public Otrogasto getOtrogastoNuevo() {
		return otrogastoNuevo;
	}

	public void setOtrogastoNuevo(Otrogasto otrogastoNuevo) {
		this.otrogastoNuevo = otrogastoNuevo;
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
	
	public OtrogastoBean(){
		Otrogastos=new ArrayList<Otrogasto>();
	}
	
	@PostConstruct
	public void init(){
		Otrogastos=getOtrogastoService().getOtrogastos();
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
			if(getOtrogastoNuevo()!=null){
				setOtrogasto(getOtrogastoService().getOtrogastoById(getOtrogastoNuevo().getIdOtroGasto()));
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
		if(getOtrogastoNuevo()!=null){
			RequestContext.getCurrentInstance().execute("confirmation.show()");
		}
		else{
			FacesMessage msg = null;  
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro");  
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void onEdit(RowEditEvent event) {
		Otrogasto c=(Otrogasto)event.getObject();
		setOtrogasto(OtrogastoService.getOtrogastoById(c.getIdOtroGasto()));
		
		getOtrogasto().setPartida(c.getPartida());
		getOtrogasto().setDescripcion(c.getDescripcion());
		getOtrogasto().setMonto(c.getMonto());
		
		validarEditar();
		
        FacesMessage msg = new FacesMessage("Otro Gasto Editado", ((Otrogasto) event.getObject()).getPartida());  
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }  
      
    public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Otrogasto Cancelado", ((Otrogasto) event.getObject()).getPartida());  
  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
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
		getOtrogastoService().addOtrogasto(getOtrogasto());
		refrescarOtrogastos();
		limpiarCampos();
	}
	
	public void editar(){
		//getOtrogasto().setIdOtrogasto(getOtrogasto().getIdOtrogasto());
		getOtrogastoService().updateOtrogasto(getOtrogasto());
		refrescarOtrogastos();
		limpiarCampos();
	}
	
	public void eliminar(){
		getOtrogastoService().deleteOtrogastoLogico(getOtrogastoNuevo());
		refrescarOtrogastos();
	}
	
	public void validarNuevo(){
		RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean registrado = false;  
          
        if(getOtrogasto().getPartida()!=null && getOtrogasto().getPartida().length()>=1){
        	registrado=true;
        	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", getOtrogasto().getPartida());
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
        
        if(getOtrogasto().getPartida()!=null && getOtrogasto().getPartida().length()>=1){
        	editado=true;
        	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado", getOtrogasto().getPartida());
            editar();
        }else if(getOtrogastoNuevo()==null){
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
        
        if(getOtrogastoNuevo()!=null){
        	eliminado = true;  
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", getOtrogastoNuevo().getPartida());
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
		otrogasto= new Otrogasto();		
		otrogastoNuevo = new Otrogasto();
	}

	private void refrescarOtrogastos()
	{
		setOtrogastos(getOtrogastoService().getOtrogastos());
		
	}
}
