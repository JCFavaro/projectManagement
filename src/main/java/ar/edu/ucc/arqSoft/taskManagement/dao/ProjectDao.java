package ar.edu.ucc.arqSoft.taskManagement.dao;

import java.util.List;

import ar.edu.ucc.arqSoft.common.dao.GenericDao;
import ar.edu.ucc.arqSoft.taskManagement.model.Project;

public interface ProjectDao extends GenericDao<Project, Long>{

	public List<Project> findByName(String name);
	
}
