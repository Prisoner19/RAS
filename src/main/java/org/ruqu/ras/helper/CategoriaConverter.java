package org.ruqu.ras.helper;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.ruqu.ras.domain.Categoria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesConverter("categoriaConverter")
public class CategoriaConverter implements Converter{

	private static final Logger LOG = LoggerFactory.getLogger(CategoriaConverter.class);
	 
	@Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       
		
	   LOG.trace("String value: {}", value);
       
       if(value=="0"){
    	   
    	   return new Categoria(false);
       }
        
        return getObjectFromUIPickListComponent(component,value);
    }
 
    
	@Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        String string="";
        LOG.trace("Object value: {}", object);
        
        
               
        if(object == null){
            string="";
        }else{
            try{
               
            	Categoria c= (Categoria)object;
            	
            	if(c.getNombre()!=null)
            		string = c.getIdCategoria().toString();
            }catch(ClassCastException cce){
            	            	
            	return "";
            }
        }
        return string;
    }
 
    @SuppressWarnings("unchecked")
    private Categoria getObjectFromUIPickListComponent(UIComponent component, String value) {
        final List<Categoria> list;
        try{
            list = (List<Categoria>)((UISelectItems)component.getChildren().get(1)).getValue();
            Categoria motor = getObjectFromList(list,Integer.valueOf(value));
            
             
            return motor;
        }catch(ClassCastException cce){
        	System.out.println(cce.getMessage());
        	throw new ConverterException();
            
        }catch(NumberFormatException nfe){
        	System.out.println(nfe.getMessage());
        	throw new ConverterException();
           
        }
    }
 
    private Categoria getObjectFromList(final List<?> list, final Integer identifier) {
        for(final Object object:list){
            final Categoria categoria = (Categoria) object;
            if(categoria.getIdCategoria().equals(identifier)){
                return categoria;
            }
        }
        return null;
    }
}
