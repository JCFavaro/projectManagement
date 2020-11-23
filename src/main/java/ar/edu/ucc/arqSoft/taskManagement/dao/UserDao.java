package ar.edu.ucc.arqSoft.taskManagement.dao;

import java.util.List;

import ar.edu.ucc.arqSoft.common.dao.GenericDao;
import ar.edu.ucc.arqSoft.taskManagement.model.User;

public interface UserDao extends GenericDao<User, Long>{

	public List<User> findByName(String name);
	
	public User findByID(Long id);
	
}
