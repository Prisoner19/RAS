package org.ruqu.ras.service;

import java.util.List;

import org.ruqu.ras.domain.Partida;

public interface IPartidaService {
	public void add(Partida partida);

	public void update(Partida partida);

	public void delete(Partida partida);

	public Partida getById(int id);

	public List<Partida> getAll();
}
