package org.ruqu.ras.dao;

import java.math.BigDecimal;
import java.util.List;

import org.ruqu.ras.domain.Otrogasto;

public interface IOtrogastoDao {

	public void addOtrogasto(Otrogasto Otrogasto);

	public void updateOtrogasto(Otrogasto Otrogasto);

	public void deleteOtrogasto(Otrogasto Otrogasto);

	public Otrogasto getOtrogastoById(int id);

	public List<Otrogasto> getOtrogastos();
	
	public BigDecimal getCostoRealOtroGasto(int idProyecto);
}
