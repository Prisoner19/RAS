package org.ruqu.ras.service;

import java.util.List;

import org.ruqu.ras.dao.IPartidaDao;
import org.ruqu.ras.domain.Partida;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class PartidaService implements IPartidaService {

	IPartidaDao PartidaDAO;
	
	@Transactional(readOnly = false)
	@Override
	public void add(Partida partida) {
		PartidaDAO.add(partida);

	}

	@Transactional(readOnly = false)
	@Override
	public void update(Partida partida) {
		PartidaDAO.update(partida);

	}

	@Transactional(readOnly = false)
	@Override
	public void delete(Partida partida) {
		PartidaDAO.delete(partida);

	}

	@Override
	public Partida getById(int id) {
		return PartidaDAO.getById(id);
	}

	@Override
	public List<Partida> getAll() {
		return PartidaDAO.getAll();
	}

	public IPartidaDao getPartidaDAO() {
		return PartidaDAO;
	}

	public void setPartidaDAO(IPartidaDao partidaDAO) {
		this.PartidaDAO = partidaDAO;
	}
	
	

}
