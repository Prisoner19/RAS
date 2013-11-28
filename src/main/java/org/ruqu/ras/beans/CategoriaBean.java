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
import org.ruqu.ras.service.ICategoriaService;

@ManagedBean(name="CategoriaBean")
@ViewScoped
public class CategoriaBean implements Serializable{

	/**
	 * Beans' attributes
	 * =================
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{CategoriaService}")
	ICategoriaService categoriaService;
	
	List<Categoria> categorias;
	
	Categoria categoria=new Categoria();
	Categoria categoriaNuevo=new Categoria();
	
	private boolean accionEditar = true;


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
			return "Editar";
		}else{
			return "Nuevo";
		}
	}


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
		if(getCategoriaNuevo()!=null){
			RequestContext.getCurrentInstance().execute("confirmation.show()");
		}
		else{
			FacesMessage msg = null;  
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro");  
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void onEdit(RowEditEvent event) {
		Categoria c=(Categoria)event.getObject();
		setCategoria(categoriaService.getCategoriaById(c.getIdCategoria()));
		
		getCategoria().setNombre(c.getNombre());
		getCategoria().setDescripcion(c.getDescripcion());
		
		System.out.println(c.getNombre());
		validarEditar();
		
        FacesMessage msg = new FacesMessage("Categoria Editado", ((Categoria) event.getObject()).getNombre());  
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }  
      
    public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Categoria Cancelado", ((Categoria) event.getObject()).getNombre());  
  
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
		getCategoriaService().addCategoria(getCategoria());
		refrescarCategorias();
		limpiarCampos();
	}
	
	public void editar(){
		//getCategoria().setIdCategoria(getCategoria().getIdCategoria());
		getCategoriaService().updateCategoria(getCategoria());
		refrescarCategorias();
		limpiarCampos();
	}
	
	public void eliminar(){
		getCategoriaService().deleteCategoriaLogico(getCategoriaNuevo());
		refrescarCategorias();
	}
	
	public void validarNuevo(){
		RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean registrado = false;  
          
        if(getCategoria().getNombre()!=null && getCategoria().getNombre().length()>=1){
        	registrado=true;
        	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", getCategoria().getNombre());
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
        
        if(getCategoria().getNombre()!=null && getCategoria().getNombre().length()>=1){
        	editado=true;
        	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado", getCategoria().getNombre());
            editar();
        }else if(getCategoriaNuevo()==null){
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
        
        if(getCategoriaNuevo()!=null){
        	eliminado = true;  
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", getCategoriaNuevo().getNombre());
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
		categoria= new Categoria();		
		categoriaNuevo = new Categoria();
	}

	private void refrescarCategorias()
	{
		setCategorias(getCategoriaService().getCategorias());
		
	}

}
