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
				FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro",growlPath);
			}
		}catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			accionEditar=false;
		}
	}
	
	public void eliminarEvent(){
		if(getPersonalNuevo()!=null){
			eliminar();
			FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Registro Eliminado "+personalNuevo.getNombre(),growlPath);
		}
		else{
			FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro",growlPath);
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
		editar();
		FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO , "Edicion Personal Realizada", getPersonal().getNombre(),growlPath);
    }  
      
    public void onCancel(RowEditEvent event) {    
        FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO , "Edicion Personal Cancelada", ((Personal) event.getObject()).getNombre(),growlPath);
        
    }
	//BOTON PROCESAR-DIALOG
	public void procesarDialog(){
		if(accionEditar){			
			editar();
			FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Registro Editado "+personal.getNombre(),growlPath);
		}else{
			insertar();
			FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Registro Nuevo "+personal.getNombre(),growlPath);
		}
		RequestContext.getCurrentInstance().execute("dialog.hide();");
		limpiarCampos();
		refrescarPersonals();
	}
	
	/* ACCIONES CRUD
	*  =============
	*/
	
	public void insertar(){
		getPersonalService().addPersonal(getPersonal());	
	}
	
	public void editar(){
		getPersonalService().updatePersonal(getPersonal());
	}
	
	public void eliminar(){
		getPersonalService().deletePersonalLogico(getPersonalNuevo());
		refrescarPersonals();
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

}
