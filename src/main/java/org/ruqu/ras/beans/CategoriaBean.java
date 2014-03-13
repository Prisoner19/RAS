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
import org.primefaces.event.RowEditEvent;
import org.ruqu.ras.domain.Categoria;
import org.ruqu.ras.helpers.utils.FacesMessageHelper;
import org.ruqu.ras.service.ICategoriaService;

@ManagedBean(name="CategoriaBean")
@ViewScoped
public class CategoriaBean implements Serializable{

	/**
	 * Beans' attributes
	 * =================
	 */
	private static final long serialVersionUID = 1L;
	private static final String growlPath ="form:growl";
	
	@ManagedProperty(value="#{CategoriaService}")
	ICategoriaService categoriaService;
	
	List<Categoria> categorias;
	
	Categoria categoria=new Categoria();
	Categoria categoriaNuevo=new Categoria();
	
	private boolean accionEditar = true;


	

 	/* CONSTRUCTOR AND POSTCONSTRUCT 
	*  ============================
 	*/
	
	public CategoriaBean(){
		categorias=new ArrayList<Categoria>();
	}
	
	@PostConstruct
	public void init(){
		categorias=getCategoriaService().getCategorias();
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
			if(getCategoriaNuevo()!=null){
				setCategoria(getCategoriaService().getCategoriaById(getCategoriaNuevo().getIdCategoria()));
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
		if(getCategoriaNuevo()!=null){
			validarEliminar();
		}
		else{
			FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro", growlPath);
		}
	}
	
	public void onEdit(RowEditEvent event) {
		Categoria c=(Categoria)event.getObject();				
		setCategoria(c);		
		editar();
		FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO, "Edicion Exitosa", ((Categoria) event.getObject()).getNombre(), growlPath);
    }  
      
    public void onCancel(RowEditEvent event) {        
        FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO, "Edicion Cancelada", ((Categoria) event.getObject()).getNombre(), growlPath);
    }
    
	//BOTON PROCESAR-DIALOG
	public void procesarDialog(){
		if(accionEditar){			
			editar();
		}else{
			insertar();
		}
		limpiarCampos();
		refrescarCategorias();
		RequestContext.getCurrentInstance().execute("dialog.hide();");
	}
	
	/* ACCIONES CRUD
	*  =============
	*/
	
	public void insertar(){
		getCategoriaService().addCategoria(getCategoria());		
	}
	
	public void editar(){		
		getCategoriaService().updateCategoria(getCategoria());		
	}
	
	public void eliminar(){
		getCategoriaService().deleteCategoriaLogico(getCategoriaNuevo());		
	}
	
	
	
	public void validarEliminar(){
		 
        
        if(getCategoriaNuevo()!=null){
        	FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO, "Eliminado", getCategoriaNuevo().getNombre(), growlPath);
            eliminar();
            limpiarCampos();
            refrescarCategorias();
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
		categoria= new Categoria();		
		categoriaNuevo = new Categoria();
	}

	private void refrescarCategorias()
	{
		setCategorias(getCategoriaService().getCategorias());
		
	}
	
	/* SETTER AND GETTERS
	*  ==================
	*/
	
	public ICategoriaService getCategoriaService() {
		return categoriaService;
	}

	public void setCategoriaService(ICategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Categoria getCategoriaNuevo() {
		return categoriaNuevo;
	}

	public void setCategoriaNuevo(Categoria categoriaNuevo) {
		this.categoriaNuevo = categoriaNuevo;
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
