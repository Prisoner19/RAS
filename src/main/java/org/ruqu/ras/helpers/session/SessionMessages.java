package org.ruqu.ras.helpers.session;

import javax.faces.context.FacesContext;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: czegarram
 * Date: 23/11/13
 * Time: 02:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class SessionMessages {
    public static final int NUEVO = 0;
    public static final int EDITAR = 1;

    public static void passData(String name, Object o) {
        Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        session.put(name, o);
    }

    public static Object getData(String name) {
        Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        return session.get(name);
    }

    public static void removeData(String name) {
        Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        session.remove(name);
    }
}
