
/**
 * Evento predeterminado para ENTER de row edit
 */

function initEnterKeyDefault(){
	$(document).on("keydown", ".ui-cell-editor-input input", function(event) {
	    if (event.keyCode == 13) {
	        $(this).closest("tr").find(".ui-row-editor .ui-icon-check").click();
	    }
	});

	$(document).keypress(function(event){   		 
		var keycode = (event.keyCode ? event.keyCode : event.which);
		if(keycode == '13'){
			event.preventDefault();	
		}  			
	 
	});
}

function limpiarString(value){
	return value.toUpperCase().trim().replace(/\s+/g, " ");
}