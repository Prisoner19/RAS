package org.ruqu.ras.beans;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.ruqu.ras.domain.Opcion;
import org.ruqu.ras.security.CustomUserDetails;
import org.ruqu.ras.service.IOpcionService;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "menuBean")
@SessionScoped
public class MenuBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private MenuModel modeloprimario;
    private MenuModel modelosecundario;

    @ManagedProperty(value = "#{OpcionService}")
    IOpcionService opcionService;


    public MenuBean() {

    }

    @PostConstruct
    public void init() {
        initModel();
    }


    public MenuModel getModeloprimario() {
        return modeloprimario;
    }
    
    public MenuModel getModelosecundario() {
        return modelosecundario;
    }

    public void setModel(MenuModel modeloprimario) {
        this.modeloprimario = modeloprimario;
    }
    
    public void setModel2(MenuModel modelosecundario) {
        this.modelosecundario = modelosecundario;
    }


    public void initModel() {
    	/*
        CustomUserDetails user = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		modeloprimario = new DefaultMenuModel();
		modelosecundario = new DefaultMenuModel();
		
		try{
            List<Opcion> submenus1 = opcionService.getSubOpcionesPrimarias();
            for(Opcion m:submenus1){
                DefaultSubMenu submenu1 = new DefaultSubMenu(m.getTextoOpcion());
                List<Opcion> hijos = opcionService.getSubOpcionsByPadre(user.getRoles(), m.getIdOpcion());
                for(Opcion hijo:hijos){
                    DefaultMenuItem item = new DefaultMenuItem(hijo.getTextoOpcion());
                    item.setOutcome("/"+hijo.getRuta());
                    submenu1.addElement(item);
                }
                if(hijos.size()>0)
                	modeloprimario.addElement(submenu1);

            }
            
            List<Opcion> submenus2 = opcionService.getSubOpcionesSecundarias();
            for(Opcion m:submenus2){
                DefaultSubMenu submenu2 = new DefaultSubMenu(m.getTextoOpcion());
                List<Opcion> hijos = opcionService.getSubOpcionsByPadre(user.getRoles(), m.getIdOpcion());
                for(Opcion hijo:hijos){
                    DefaultMenuItem item = new DefaultMenuItem(hijo.getTextoOpcion());
                    item.setOutcome("/"+hijo.getRuta());
                    submenu2.addElement(item);
                }
                if(hijos.size()>0)
                	modelosecundario.addElement(submenu2);

            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
		
		*/
		
		
		
    	modeloprimario = new DefaultMenuModel();
    	modelosecundario = new DefaultMenuModel();
        
        List<Opcion> submenusPrimarios = opcionService.getSubOpcionesPrimarias();
        List<Opcion> submenusSecundarios = opcionService.getSubOpcionesSecundarias();

        for (Opcion m : submenusPrimarios) {
            DefaultSubMenu submenu = new DefaultSubMenu(m.getDescripcion());
            List<Opcion> hijos = opcionService.getAllSubOpcionsByPadre(m.getIdOpcion());

            System.out.println(hijos);

            for (Opcion hijo : hijos) {
                DefaultMenuItem item = new DefaultMenuItem(hijo.getDescripcion());
                item.setOutcome("/" + hijo.getRuta());
                submenu.addElement(item);
            }
            if (hijos.size() > 0)
            	modeloprimario.addElement(submenu);

        }
        
        for (Opcion m : submenusSecundarios) {
            DefaultSubMenu submenu = new DefaultSubMenu(m.getDescripcion());
            List<Opcion> hijos = opcionService.getAllSubOpcionsByPadre(m.getIdOpcion());

            System.out.println(hijos);

            for (Opcion hijo : hijos) {
                DefaultMenuItem item = new DefaultMenuItem(hijo.getDescripcion());
                item.setOutcome("/" + hijo.getRuta());
                submenu.addElement(item);
            }
            if (hijos.size() > 0)
            	modelosecundario.addElement(submenu);

        }
    	 
    }


    public IOpcionService getOpcionService() {
        return opcionService;
    }

    public void setOpcionService(IOpcionService opcionService) {
        this.opcionService = opcionService;
    }
}
