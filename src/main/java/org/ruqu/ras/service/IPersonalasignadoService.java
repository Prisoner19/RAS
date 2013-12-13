package org.ruqu.ras.service;

import java.math.BigDecimal;
import java.util.List;

import org.ruqu.ras.domain.Personalasignado;
import org.ruqu.ras.domain.PersonalasignadoId;

public interface IPersonalasignadoService {

	public void addPersonalasignado(Personalasignado Personalasignado);

	public void updatePersonalasignado(Personalasignado Personalasignado);
	
	public void deletePersonalasignado(Personalasignado Personalasignado);
	
	public Personalasignado getPersonalasignadoById(PersonalasignadoId id);

	public List<Personalasignado> getPersonalasignados();
	
	public BigDecimal getCostoRealPersonalasignados(int idProyecto);
}
