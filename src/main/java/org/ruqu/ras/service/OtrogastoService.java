package org.ruqu.ras.service;

import java.util.List;

import org.ruqu.ras.dao.IOtrogastoDao;
import org.ruqu.ras.domain.Otrogasto;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class OtrogastoService implements IOtrogastoService{

	IOtrogastoDao OtrogastoDAO;

	@Transactional(readOnly = false)
	public void addOtrogasto(Otrogasto Otrogasto) {
		getOtrogastoDAO().addOtrogasto(Otrogasto);
	}

	@Transactional(readOnly = false)
	public void deleteOtrogasto(Otrogasto Otrogasto) {
		getOtrogastoDAO().deleteOtrogasto(Otrogasto);
	}
	@Transactional(readOnly = false)
	public void deleteOtrogastoLogico(Otrogasto Otrogasto){
		Otrogasto.setVigencia(false);
		getOtrogastoDAO().updateOtrogasto(Otrogasto);
	}

	@Transactional(readOnly = false)
	public void updateOtrogasto(Otrogasto Otrogasto) {
		getOtrogastoDAO().updateOtrogasto(Otrogasto);
	}

	public Otrogasto getOtrogastoById(int id) {
		return getOtrogastoDAO().getOtrogastoById(id);
	}

	public List<Otrogasto> getOtrogastos() {
		return getOtrogastoDAO().getOtrogastos();
	}

	public IOtrogastoDao getOtrogastoDAO() {
		return OtrogastoDAO;
	}

	public void setOtrogastoDAO(IOtrogastoDao OtrogastoDAO) {
		this.OtrogastoDAO = OtrogastoDAO;
	}
}
