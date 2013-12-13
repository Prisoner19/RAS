package org.ruqu.ras.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DualListModel;
import org.ruqu.ras.domain.Categoria;
import org.ruqu.ras.domain.Compra;
import org.ruqu.ras.domain.Detallecompra;
import org.ruqu.ras.domain.DetallecompraId;
import org.ruqu.ras.domain.Equipo;
import org.ruqu.ras.domain.EquipoasignadoId;
import org.ruqu.ras.domain.Proveedor;
import org.ruqu.ras.helpers.utils.FacesMessageHelper;
import org.ruqu.ras.service.ICategoriaService;
import org.ruqu.ras.service.ICompraService;
import org.ruqu.ras.service.IDetalleCompraService;
import org.ruqu.ras.service.IEquipoService;
import org.ruqu.ras.service.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;

@ManagedBean(name = "CompraBean")
@ViewScoped
public class CompraBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String growlPath ="form:growl";
	
	@ManagedProperty(value="#{EquipoService}")
	private IEquipoService equipoService;
	
	@ManagedProperty(value = "#{ProveedorService}")
	private IProveedorService proveedorService; 
	
	@ManagedProperty(value = "#{CategoriaService}")
	private ICategoriaService categoriaService;  

	@ManagedProperty(value = "#{CompraService}")
	private ICompraService compraService;
	
	@ManagedProperty(value = "#{DetalleCompraService}")
	private IDetalleCompraService detalleCompraService;
	
	private List<Equipo> equipos;
	private List<Categoria> categorias;
	private List<Proveedor> proveedores;
	private List<Compra> compras;
	private List<Detallecompra> detalleCompras;
	
	Compra compra = new Compra();
	Compra compraNuevo = new Compra();
	Detallecompra detCompra = new Detallecompra();
	Proveedor provCompra = new Proveedor();
	Detallecompra detalleSelected = new Detallecompra();
	
	private boolean accionEditar = true;

	public IEquipoService getEquipoService() {
		return equipoService;
	}

	public void setEquipoService(IEquipoService equipoService) {
		this.equipoService = equipoService;
	}

	public IProveedorService getProveedorService() {
		return proveedorService;
	}

	public void setProveedorService(IProveedorService proveedorService) {
		this.proveedorService = proveedorService;
	}

	public ICategoriaService getCategoriaService() {
		return categoriaService;
	}

	public void setCategoriaService(ICategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	public ICompraService getCompraService() {
		return compraService;
	}

	public void setCompraService(ICompraService compraService) {
		this.compraService = compraService;
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Proveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Compra getCompraNuevo() {
		return compraNuevo;
	}

	public void setCompraNuevo(Compra compraNuevo) {
		this.compraNuevo = compraNuevo;
	}

	public boolean isAccionEditar() {
		return accionEditar;
	}

	public void setAccionEditar(boolean accionEditar) {
		this.accionEditar = accionEditar;
	}
	

	public List<Detallecompra> getDetalleCompras() {
		return detalleCompras;
	}

	public void setDetalleCompras(List<Detallecompra> detalleCompras) {
		this.detalleCompras = detalleCompras;
	}
	
	public Detallecompra getDetCompra() {
		return detCompra;
	}

	public void setDetCompra(Detallecompra detCompra) {
		this.detCompra = detCompra;
	}
	
	public IDetalleCompraService getDetalleCompraService() {
		return detalleCompraService;
	}

	public void setDetalleCompraService(IDetalleCompraService detalleCompraService) {
		this.detalleCompraService = detalleCompraService;
	}
	
	public Proveedor getProvCompra() {
		return provCompra;
	}

	public void setProvCompra(Proveedor provCompra) {
		this.provCompra = provCompra;
	}
	

	public Detallecompra getDetalleSelected() {
		return detalleSelected;
	}

	public void setDetalleSelected(Detallecompra detalleSelected) {
		this.detalleSelected = detalleSelected;
	}
	
	public String etiBotonDialog(){
		if(accionEditar){
			return "EDITAR";
		}else{
			return "NUEVO";
		}
	}

	public CompraBean(){
		equipos = new ArrayList<Equipo>();
		categorias = new ArrayList<Categoria>();
		proveedores = new ArrayList<Proveedor>();
		compras = new ArrayList<Compra>();
		detalleCompras = new ArrayList<Detallecompra>();
	}
	
	@PostConstruct
	public void init(){
		equipos=getEquipoService().getEquipos();
		categorias=getCategoriaService().getCategorias();
		proveedores=getProveedorService().getProveedors();
		compras=getCompraService().getCompras();
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
			if(getCompraNuevo()!=null){
				setCompra(getCompraService().getCompraById(getCompraNuevo().getIdCompra()));
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
		System.out.println("llegue");
		if(getCompraNuevo()!=null){
			RequestContext.getCurrentInstance().execute("confirmation.show()");
		}
		else{
			FacesMessage msg = null;  
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro");  
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	

	public void onEdit(RowEditEvent event) {
		Compra c=(Compra)event.getObject();
		setCompra(compraService.getCompraById(c.getIdCompra()));
		
		getCompra().setIdCompra(c.getIdCompra());
		getCompra().setFecha(c.getFecha());
		getCompra().setTotal(c.getTotal());
		getCompra().setProveedor(proveedorService.getProveedorById(c.getProveedor().getIdProveedor()));
		getCompra().setDetallecompras(c.getDetallecompras());
		
		validarEditar();
		
        FacesMessage msg = new FacesMessage("Compra Editada", ((Compra) event.getObject()).getIdCompra().toString());  
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }  
    
	public void onCancel(RowEditEvent event) {  
		FacesMessage msg = new FacesMessage("Edición Cancelada", ((Compra) event.getObject()).getIdCompra().toString());  
	
	    FacesContext.getCurrentInstance().addMessage(null, msg);  
	}
	
	public void onRowToggle(ToggleEvent event) {
		
		if(event.getVisibility().name().compareToIgnoreCase("hidden") != 0){
			
			Compra v=(Compra)event.getData();
			setCompra(compraService.getCompraById(v.getIdCompra()));
			
			setDetalleCompras(new ArrayList<Detallecompra>(getCompra().getDetallecompras()));
		}
		RequestContext.getCurrentInstance().execute("test();");

    }
	
	//BOTON PROCESAR-DIALOG
	public void procesarDialog(){
		if(accionEditar){
			validarEditar();
		}else{
			validarNuevo();
		}
		RequestContext.getCurrentInstance().execute("dialog.hide();");
		refrescarCompras();
	}
	

	/* ACCIONES CRUD
	*  =============
	*/
	
	public void insertar(){
		getCompraService().addCompra(getCompra());
		refrescarCompras();
		limpiarCampos();
	}
	
	public void editar(){
		getCompraService().updateCompra(getCompra());
		refrescarCompras();
		limpiarCampos();
	}
	
	public void eliminar(){
		getCompraService().deleteCompraLogico(getCompraNuevo());
		refrescarCompras();
	}
	
	public void validarNuevo(){
		RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean registrado = false;  

        if(getCompra().getProveedor()!=null && getCompra().getDetallecompras()!=null && detalleCompras.size()>0){
        	registrado=true;
            registrarCompra();
        	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", getCompra().getIdCompra().toString());
        } 
		else if(detalleCompras.size() == 0){
			registrado = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de registro", "Nada que ingresar.");  
		}
		else{  
        	registrado = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de registro", "Campo(s) inválido(s)");  
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("registrado", registrado);
	}
	

	public void validarEditar(){
		RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean editado = false;  
        
        if(getCompra().getProveedor()!=null && getCompra().getDetallecompras()!=null){
        	editado=true;
        	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado", getCompra().getIdCompra().toString());
            editar();
        }else if(getCompraNuevo()==null){
        	editado = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro");  
        }else{
        	editado = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Nombre inválido");  
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("editado", editado);  
	}
	
	public void validarEliminar(){
		RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean eliminado = false;  

        if(getCompraNuevo()!=null){
        	eliminado = true;  
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", getCompraNuevo().getIdCompra().toString());
            eliminar();
        } else {  
			System.out.println("NOOO");
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
		compra = new Compra();		
		compraNuevo = new Compra();
	}

	public void refrescarCompras()
	{
		setCompras(getCompraService().getCompras());
	}

	
	public void comprarEquipo(){
		
		if(detCompra.getEquipo().getStock() == 0){
			FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Ingrese cantidad válida", growlPath);
		}
		else{
			//detCompra.getEquipo().setStock(detCompra.getEquipo().getStock() + detCompra.getCantidad());
			//detCompra.setId(new DetallecompraId(getCompra().getIdCompra(),detCompra.getEquipo().getIdEquipo()));
			detCompra.setTotalDetalle(BigDecimal.valueOf(detCompra.getEquipo().getCosto().intValue() * detCompra.getCantidad()));
			detCompra.setCompra(compra);
			/*
			if(!detalleCompras.contains(detCompra)){
				
				FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Equipo descargado", growlPath);
			}
			else{
				FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No puede comprar dos veces el mismo producto", growlPath);
			}*/
			detalleCompras.add(detCompra);
			detCompra = new Detallecompra();
			
			for(Detallecompra dt:detalleCompras){
				System.out.print("\n\n"+dt.getEquipo().getCodigo());
				System.out.print("\\"+dt.getCantidad());
				System.out.print("\\"+dt.getEquipo().getStock()+"\n\n");
			}
		}
	}
	
	public void registrarCompra(){
		
		double costoTotal = 0;
		
		for(Detallecompra dt:detalleCompras){
			dt.getEquipo().getCodigo();
			costoTotal = costoTotal + dt.getEquipo().getCosto().doubleValue() * dt.getCantidad();
		}
		
		getCompra().setFecha(new Date());
	//	System.out.print("\n\n\n"+getCompra().getProveedor() +"\n\n\n");
		getCompra().setProveedor(getProveedorService().getProveedorById(getCompra().getProveedor().getIdProveedor()));
		getCompra().setTotal(BigDecimal.valueOf(costoTotal));
		getCompra().setDetallecompras(new HashSet<Detallecompra>(detalleCompras));

		getCompraService().addCompra(getCompra());

		System.out.print("\n\n"+getCompra().getFecha().toString());
		System.out.print("\n\n"+getCompra().getIdCompra());
		System.out.print("\n\n"+getCompra().getTotal());
		System.out.print("\n\n"+getCompra().getProveedor().getNombre());
		
		for(Detallecompra dt:getCompra().getDetallecompras()){
			dt.setCompra(getCompra());
			dt.setId(new DetallecompraId(getCompra().getIdCompra(),dt.getEquipo().getIdEquipo()));
			System.out.print("\n\n"+dt.getCompra().getIdCompra());
			dt.getEquipo().setStock(dt.getEquipo().getStock() + dt.getCantidad());
			getEquipoService().updateEquipo(dt.getEquipo());
		}
		
		getCompraService().updateCompra(getCompra());
		
		for(Detallecompra dt:getCompra().getDetallecompras()){
			getDetalleCompraService().addDetallecompra(dt);
		}
		
		detalleCompras = new ArrayList<Detallecompra>();
	}

	public void prueba(){
		System.out.print("\n\nasddddd\n\n\n");
	}

	public void timeZone(){
		TimeZone.getDefault();
	}


	
	
	
	
	
	
	
	
/*

	private List<Categoria> categorias;
	private List<Proveedor> proveedores;
	private List<Equipo> equipos;
	private List<Equipo> equipoSelected;

	private Categoria cat = new Categoria();
	private Proveedor proveedor = new Proveedor();

	private BigDecimal totalGeneralSinIgv =new BigDecimal(0);
	private BigDecimal totalGeneralConIgv =new BigDecimal(0);

	
	public CompraBean(){
		totalGeneralSinIgv.setScale(2, RoundingMode.HALF_UP);
		totalGeneralConIgv.setScale(2, RoundingMode.HALF_UP);
	}
	
	public List<Proveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public IProveedorService getProveedorService() {
		return proveedorService;
	}

	public void setProveedorService(IProveedorService proveedorService) {
		this.proveedorService = proveedorService;
	}

	public ICompraService getCompraService() {
		return compraService;
	}

	public void setCompraService(ICompraService compraService) {
		this.compraService = compraService;
	}

	public BigDecimal getTotalGeneralSinIgv() {
		return totalGeneralSinIgv;
	}

	public void setTotalGeneralSinIgv(BigDecimal totalGeneralSinIgv) {
		this.totalGeneralSinIgv = totalGeneralSinIgv;
	}

	public BigDecimal getTotalGeneralConIgv() {
		return totalGeneralConIgv;
	}

	public void setTotalGeneralConIgv(BigDecimal totalGeneralConIgv) {
		this.totalGeneralConIgv = totalGeneralConIgv;
	}

	public List<Producto> getProdSelected() {
		return prodSelected;
	}

	public void setProdSelected(List<Producto> prodSelected) {
		this.prodSelected = prodSelected;
	}

	public DualListModel<Motor> getProductsDual() {
		return productsDual;
	}

	public void setProductsDual(DualListModel<Motor> productsDual) {
		this.productsDual = productsDual;
	}

	public IProductoService getProductoService() {
		return productoService;
	}

	public void setProductoService(IProductoService productoService) {
		this.productoService = productoService;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Categoria getCat() {
		return cat;
	}

	public void setCat(Categoria cat) {
		this.cat = cat;
	}

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
	
	@PostConstruct
	public void init(){
		FacesContext cont = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();  
		try{
		if(cont.getExternalContext().getSessionMap().get("logeado")==null){
			System.out.println("null");	
			FacesContext.getCurrentInstance().getExternalContext().redirect("/salt/pages/login.xhtml");
			return;		
		}
		}catch(Exception e){
			return;
		}
		
		categorias = categoriaService.getCategorias();
		productos = productoService.getProductos();
		proveedores = proveedorService.getProveedores();
		prodSelected = new ArrayList<Producto>();
		prodCheckOut = new ArrayList<ProductoCompraHelper>();
		motors = new ArrayList<Motor>();
	}
	
	public void updateProducts(AjaxBehaviorEvent e){
		DataTable ui = (DataTable)e.getSource();
		Categoria c = (Categoria)ui.getSelection();
		System.out.println(c);
		
	}
	public void onCellEdit(CellEditEvent e){
		boolean flag=false;
		
		try{
			
			if(e.getColumn().getHeaderText().equals("PU.")){
				
				flag=true;
			}
			
		}catch(Exception a){
			return;
		}
		
		if(!flag){
		
			try{	
							
				int old = (int)e.getOldValue();  
				int nuevo = (int) e.getNewValue();
		
				FacesContext context = FacesContext.getCurrentInstance(); 
				ProductoCompraHelper temp = prodCheckOut.get(e.getRowIndex());
				BigDecimal t = temp.getPrecioUnit().multiply(new BigDecimal(100.0f / 119.0f));
				
				if(nuevo==0){
					prodCheckOut.remove(e.getRowIndex());
					totalGeneralSinIgv=totalGeneralSinIgv.subtract(t.multiply(new BigDecimal(old-nuevo))).setScale(2, RoundingMode.HALF_UP); ;
					totalGeneralConIgv=totalGeneralConIgv.subtract(temp.getPrecioUnit().multiply(new BigDecimal(old-nuevo))).setScale(2, RoundingMode.HALF_UP);;
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso", "¡Producto eliminado del carrito de compras!"));
					return;
				}
				
				
				
				 if(old!=nuevo){
					
					temp.setTotal( temp.getPrecioUnit().multiply(new BigDecimal(nuevo))	);	
					temp.setTotalsin(t.multiply(new BigDecimal(nuevo)));
					if(old<nuevo){
						totalGeneralSinIgv=totalGeneralSinIgv.add(t.multiply(new BigDecimal(nuevo-old))).setScale(2, RoundingMode.HALF_UP); ;
						totalGeneralConIgv=totalGeneralConIgv.add(temp.getPrecioUnit().multiply(new BigDecimal(nuevo-old))).setScale(2, RoundingMode.HALF_UP);;
					}else{
						totalGeneralSinIgv=totalGeneralSinIgv.subtract(t.multiply(new BigDecimal(old-nuevo))).setScale(2, RoundingMode.HALF_UP); ;
						totalGeneralConIgv=totalGeneralConIgv.subtract(temp.getPrecioUnit().multiply(new BigDecimal(old-nuevo))).setScale(2, RoundingMode.HALF_UP);;
						
					}
				 }
				 
				 if(prodCheckOut.isEmpty()){
					 totalGeneralSinIgv= new BigDecimal(0);
				 	 totalGeneralConIgv= new BigDecimal(0);
				 }
					
				 
				
			}catch(Exception ex){
				ex.getMessage();
			}
		}else{
			
			try{
				System.out.println("PU.");
				System.out.println(e.getOldValue());
				System.out.println(e.getNewValue());
				
				
			}catch(Exception exc){
				System.out.println("Error: OnCellEdit");
			}
		}
	
	}
	
	
	public void enviarProd (){
		try{
			System.out.println(proveedor);
			FacesContext context = FacesContext.getCurrentInstance();  
	          
			if(prodSelected.isEmpty()) { 
	        	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia", "¡No ha seleccionado ningun producto!"));   
	        	return;
	        }
			
			ProductoCompraHelper temp = new ProductoCompraHelper(new Producto(), 0,
					new BigDecimal(0),new BigDecimal(0),new BigDecimal(0));
			for(Producto p:prodSelected){
				temp.setP(p);
				if(!prodCheckOut.contains(temp)){
					BigDecimal t = new BigDecimal(temp.getP().getPcSuge()).multiply(new BigDecimal(100.0f / 119.0f));					
					prodCheckOut.add(new ProductoCompraHelper(p,1,
							new BigDecimal(Float.toString(temp.getP().getPcSuge()
									)),
									t,new BigDecimal(Float.toString(temp.getP().getPcSuge()	)))
					);
					
					
										
					totalGeneralSinIgv=totalGeneralSinIgv.add(t).setScale(2, RoundingMode.HALF_UP);
					
					totalGeneralConIgv=totalGeneralConIgv.add(new BigDecimal(Float.toString(temp.getP().getPcSuge()))).setScale(2, RoundingMode.HALF_UP);;
					
				}
			}
			prodSelected=new ArrayList<Producto>();
			
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
	}
	
	public void onFilter(AjaxBehaviorEvent event){
		//DataTable dt = (DataTable) event.getSource();
		//System.out.println(dt.getFilters().get("marcaProducto.marca"));
	}
	
	public void procesarCompra ()
	{
		Proveedor prov;
		try{
			System.out.println(proveedor);
			prov = proveedorService.getProveedorById(proveedor.getIdProveedor());
		}catch(Exception y){
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error", "¡No ha seleccionado proveedor!"));   
        	return;
		}
		try{
			FacesContext context = FacesContext.getCurrentInstance();  
	          
			if(prodCheckOut.isEmpty()) { 
	        	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia", "¡Carrito de Compras Vacio!"));   
	        	return;
	        }
			
			
			
			Date fecha = new Date();
			PuntoVenta pv = puntoVentaService.getPuntoVentaById(1);
			
						
			
				
		
			productos = null;
			
			Set<ProductoCompra> setProdCompra = new HashSet<ProductoCompra>();
			ProductoCompra pvent = null;
			float total;
			
			total=totalGeneralConIgv.floatValue();
		
			
			
			Compra v= new Compra(prov,pv,total,fecha,true,setProdCompra);
			v.setComprobante(comprobante);
			
			compraService.addCompra(v);
			
		
			for(ProductoCompraHelper p:prodCheckOut){
				pvent=new ProductoCompra();
				pvent.setId(new ProductoCompraId(v.getIdCompra(),p.getP().getIdProducto()));
				pvent.setCantidad(p.getCant());
				pvent.setCostoTotal(p.getTotal().floatValue());
				pvent.setCostoUnit(p.getPrecioUnit().floatValue());
				setProdCompra.add(pvent);
				p.getP().setStock(p.getP().getStock()+pvent.getCantidad());
				//System.out.println(p.getP());
				productoService.updateProducto(p.getP());
				p=null;				
			}

			
			prodPreview = null;
			prodCheckOut=new ArrayList<ProductoCompraHelper>();
			compraService.updateCompra(v);
			totalGeneralConIgv=new BigDecimal(0);
			totalGeneralSinIgv=new BigDecimal(0);
			proveedor = new Proveedor();
			
			
			
			//prueba
			refrescarProductos();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Resultado", "¡Compra Realizada con exito!"));
		}catch(Exception e){
			
			FacesContext context = FacesContext.getCurrentInstance(); 
			System.out.println(e.getMessage());
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error", "¡Compra no ha sido realizada con exito!"));
		}
	}

	private void refrescarProductos() {
		setProductos(productoService.getProductos());		
	}
	
	public void limpiarCompra(){
		FacesContext context = FacesContext.getCurrentInstance();  
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info", "¡Se ha limpiado el carrito de compras!")); 
		
		prodPreview = null;
		prodCheckOut=new ArrayList<ProductoCompraHelper>();
		totalGeneralConIgv=new BigDecimal(0);
		totalGeneralSinIgv=new BigDecimal(0);
		
		refrescarProductos();
		
	}
*/


}
