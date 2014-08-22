package org.ruqu.ras.helpers.utils;

import com.google.common.base.Splitter;

import org.apache.commons.io.FilenameUtils;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class HttpRequestUtil {


    /**
     * Extrae la url completa de la vista con la cual se comunica el Bean
     *
     * @return url
     */
    public static String getRequestURL() {
        Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request instanceof HttpServletRequest) {
            return FilenameUtils.getBaseName(((HttpServletRequest) request).getRequestURL().toString().replaceAll(".*/", ""));
        } else {
            return "";
        }
    }

    /**
     * Extrae el nombre de la vista de todo la url de peticion
     *
     * @param url : url entrante
     * @return Nombre de la vista
     */
    public static String getOutComeFroUrl(String url) {
        return FilenameUtils.getBaseName(url.replaceAll(".*/", ""));
    }

    /**
     * Separa los nombres de folders de una url
     *
     * @param s : url
     * @return : Lista de Strings con los folders en cada elemento de esta.
     */
    public static List<String> splitFolders(String s){
		List<String> temp = Arrays.asList(FilenameUtils.removeExtension(s).split("/"));
		
		return temp;
	}

    public static void goToUrl(String s) throws ServletException, IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
                .getRequestDispatcher(s);

        dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());

        FacesContext.getCurrentInstance().responseComplete();
    }
}

