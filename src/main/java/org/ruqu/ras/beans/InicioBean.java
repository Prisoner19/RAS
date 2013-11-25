package org.ruqu.ras.beans;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: czegarram
 * Date: 23/11/13
 * Time: 03:04 AM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "inicioBean")
@ViewScoped
public class InicioBean {

    String mensajeInicio;

    public InicioBean() {
        mensajeInicio = "Bienvenido al sistema";
    }

    @PostConstruct
    public void initMyBean() {
        Map<String, String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String get_id = requestParams.get("id");
        if (get_id != null) {
            int i = Integer.parseInt(get_id);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "");
            switch (i) {
                case 1:
                    message.setDetail("No tiene privelegios necesarios para acceder a esa pagina");
                    break;
            }
            FacesContext.getCurrentInstance().addMessage(null, message);
        }


    }

    public String getMensajeInicio() {
        return mensajeInicio;
    }

    public void setMensajeInicio(String mensajeInicio) {
        this.mensajeInicio = mensajeInicio;
    }
}
