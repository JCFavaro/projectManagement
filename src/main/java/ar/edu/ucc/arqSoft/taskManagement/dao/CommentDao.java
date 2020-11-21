package ar.edu.ucc.arqSoft.taskManagement.dao;

import java.util.List;

import ar.edu.ucc.arqSoft.common.dao.GenericDao;
import ar.edu.ucc.arqSoft.taskManagement.model.Comment;

public interface CommentDao extends GenericDao<Comment, Long>{

	public List<Comment> findByTitle(String title);
	
}
