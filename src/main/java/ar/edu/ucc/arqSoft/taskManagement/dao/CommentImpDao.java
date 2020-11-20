package ar.edu.ucc.arqSoft.taskManagement.dao;

import org.springframework.stereotype.Repository;

import ar.edu.ucc.arqSoft.common.dao.GenericImpDao;
import ar.edu.ucc.arqSoft.taskManagement.model.Comment;

@Repository
public class CommentImpDao extends GenericImpDao<Comment, Long> implements CommentDao{


}
