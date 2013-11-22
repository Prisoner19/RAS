package org.ruqu.ras.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import org.ruqu.ras.domain.Opcion;
import org.ruqu.ras.security.CustomUserDetails;
import org.ruqu.ras.service.IOpcionService;

@ManagedBean(name="menuBean")
@SessionScoped
public class MenuBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MenuModel model;

    @ManagedProperty(value="#{OpcionService}")
	IOpcionService opcionService;
	
	

	public MenuBean(){
		
	}
	
	@PostConstruct
	public void init(){
		initModel();
	}
	
	
	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}
	
	
	
	public void initModel(){
		
		CustomUserDetails user = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		

		model = new DefaultMenuModel();
		try{
            List<Opcion> submenus = opcionService.getSubOpcions();
            for(Opcion m:submenus){
                DefaultSubMenu submenu = new DefaultSubMenu(m.getDescripcion());
                List<Opcion> hijos = opcionService.getSubOpcionsByPadre(user.getRoles(), m.getIdOpcion());
                for(Opcion hijo:hijos){
                    DefaultMenuItem item = new DefaultMenuItem(hijo.getDescripcion());
                    item.setOutcome("/"+hijo.getRuta());
                    submenu.addElement(item);
                }
                if(hijos.size()>0)
                    model.addElement(submenu);

            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
		/*
		
		model = new DefaultMenuModel();
		List<Opcion> submenus = OpcionService.getSubOpciones();
		
		
		
		for(Opcion m:submenus){			
			DefaultSubMenu submenu = new DefaultSubMenu(m.getDescripcion());
			List<Opcion> hijos = OpcionService.getAllSubOpcionesByPadre(m.getIdOpcion());
			
			System.out.println(hijos);
			
			for(Opcion hijo:hijos){
				DefaultMenuItem item = new DefaultMenuItem(hijo.getDescripcion());
				item.setOutcome("/"+hijo.getRuta());
				submenu.addElement(item);
			}
			if(hijos.size()>0)
				model.addElement(submenu);			
			
		}*/
		    
	
	
	}


    public IOpcionService getOpcionService() {
        return opcionService;
    }

    public void setOpcionService(IOpcionService opcionService) {
        this.opcionService = opcionService;
    }
}
