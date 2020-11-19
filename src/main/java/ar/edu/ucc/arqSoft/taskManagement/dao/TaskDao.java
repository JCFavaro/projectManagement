package ar.edu.ucc.arqSoft.taskManagement.dao;

import java.util.List;

import ar.edu.ucc.arqSoft.common.dao.GenericDao;
import ar.edu.ucc.arqSoft.taskManagement.model.Task;

public interface TaskDao extends GenericDao<Task, Long>{

	public List<Task> findByName(String name);
	
}
