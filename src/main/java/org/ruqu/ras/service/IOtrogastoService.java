package org.ruqu.ras.service;

import java.util.List;

import org.ruqu.ras.domain.Otrogasto;

public interface IOtrogastoService {

	public void addOtrogasto(Otrogasto Otrogasto);

	public void updateOtrogasto(Otrogasto Otrogasto);
	
	public void deleteOtrogasto(Otrogasto Otrogasto);
	
	public void deleteOtrogastoLogico(Otrogasto Otrogasto);
	
	public Otrogasto getOtrogastoById(int id);

	public List<Otrogasto> getOtrogastos();
}
