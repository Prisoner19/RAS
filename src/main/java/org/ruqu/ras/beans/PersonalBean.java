package org.ruqu.ras.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.ruqu.ras.domain.Personal;
import org.ruqu.ras.helpers.utils.FacesMessageHelper;
import org.ruqu.ras.service.IPersonalService;

@ManagedBean (name="PersonalBean")
@ViewScoped
public class PersonalBean implements Serializable{

	/**
	 * Beans' attributes
	 * =================
	 */
	private static final long serialVersionUID = 1L;
	private static final String growlPath ="form:growl";
	
	@ManagedProperty(value="#{PersonalService}")
	IPersonalService personalService;
	
	List<Personal> personals;
	
	Personal personal=new Personal();
	Personal personalNuevo=new Personal();
	
	private boolean accionEditar = true;


	/* SETTER AND GETTERS
	*  ==================
	*/

	
	public IPersonalService getPersonalService() {
		return personalService;
	}

	public List<Personal> getPersonals() {
		return personals;
	}

	public void setPersonals(List<Personal> personals) {
		this.personals = personals;
	}

	public void setPersonalService(IPersonalService personalService) {
		this.personalService = personalService;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public Personal getPersonalNuevo() {
		return personalNuevo;
	}

	public void setPersonalNuevo(Personal personalNuevo) {
		this.personalNuevo = personalNuevo;
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
	
	public PersonalBean(){
		personals=new ArrayList<Personal>();
	}
	
	@PostConstruct
	public void init(){
		personals=getPersonalService().getPersonals();
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
			if(getPersonalNuevo()!=null){
				setPersonal(getPersonalService().getPersonalById(getPersonalNuevo().getIdPersonal()));
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
		if(getPersonalNuevo()!=null){
			RequestContext.getCurrentInstance().execute("confirmation.show()");
		}
		else{
			FacesMessage msg = null;  
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro");  
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void onEdit(RowEditEvent event) {
		Personal c=(Personal)event.getObject();
		setPersonal(personalService.getPersonalById(c.getIdPersonal()));
		
		getPersonal().setNombre(c.getNombre());
		getPersonal().setProfesion(c.getProfesion());
		getPersonal().setCelular(c.getCelular());
		getPersonal().setEmail(c.getEmail());
		
		//System.out.println(c.getNombre());
		validarEditar();
		
        FacesMessage msg = new FacesMessage("Personal Editado", ((Personal) event.getObject()).getNombre());  
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }  
      
    public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Personal Cancelado", ((Personal) event.getObject()).getNombre());  
  
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
		RequestContext.getCurrentInstance().execute("dialog.hide();");
		refrescarPersonals();
	}
	
	/* ACCIONES CRUD
	*  =============
	*/
	
	public void insertar(){
		Calendar fecha=new GregorianCalendar();
		getPersonal().setRegistro(fecha.getTime());
		getPersonalService().addPersonal(getPersonal());
		refrescarPersonals();
		limpiarCampos();
	}
	
	public void editar(){
		//getPersonal().setIdPersonal(getPersonal().getIdPersonal());
		getPersonalService().updatePersonal(getPersonal());
		refrescarPersonals();
		limpiarCampos();
	}
	
	public void eliminar(){
		getPersonalService().deletePersonalLogico(getPersonalNuevo());
		refrescarPersonals();
	}
	
	public void validarNuevo(){
		RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean registrado = false;  
          
        if(getPersonal().getNombre()!=null && getPersonal().getNombre().length()>=1){
        	registrado=true;
        	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", getPersonal().getNombre());
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
        
        if(getPersonal().getNombre()!=null && getPersonal().getNombre().length()>=1){
        	editado=true;
        	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado", getPersonal().getNombre());
            editar();
        }else if(getPersonalNuevo()==null){
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
        
        if(getPersonalNuevo()!=null){
        	eliminado = true;  
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", getPersonalNuevo().getNombre());
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
	
	public void showError(){
		FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_WARN, "Registro","Campos resaltados invalidos", growlPath
				,"Error");		
	}

	public void limpiarCampos()
	{
		personal= new Personal();		
		personalNuevo = new Personal();
	}

	private void refrescarPersonals()
	{
		setPersonals(getPersonalService().getPersonals());
		
	}

}
