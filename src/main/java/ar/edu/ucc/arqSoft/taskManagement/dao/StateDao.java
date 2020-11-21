package ar.edu.ucc.arqSoft.taskManagement.dao;

import java.util.List;

import ar.edu.ucc.arqSoft.common.dao.GenericDao;
import ar.edu.ucc.arqSoft.taskManagement.model.State;

public interface StateDao extends GenericDao<State, Long>{

	public List<State> findByName(String name);
	
}
