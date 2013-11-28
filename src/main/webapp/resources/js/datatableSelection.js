/**
 * 
 */

function selectCurrentRow_paginator(table,index){
	table.unselectAllRows();
	table.selectRow(index-(table.paginator.cfg.page*table.paginator.cfg.rows) ,false);
	
}

function selectCurrentRow(table,index){
	table.unselectAllRows();
	table.selectRow(index ,false);
}

function editLastDatatableRow(styleClass,index){

  jQuery('.'+styleClass+' tr').eq(index+1).find('span.ui-icon-pencil').each(function(){
     jQuery(this).click()
  });
 
}

function closeRowEdit(styleClass,index){
	jQuery('.'+styleClass+' tr').eq(index+1).find('span.ui-icon-close').click();
}