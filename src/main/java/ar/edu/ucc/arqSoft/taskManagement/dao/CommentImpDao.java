package ar.edu.ucc.arqSoft.taskManagement.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import ar.edu.ucc.arqSoft.common.dao.GenericImpDao;
import ar.edu.ucc.arqSoft.taskManagement.model.Comment;

@Repository
public class CommentImpDao extends GenericImpDao<Comment, Long> implements CommentDao{

	@Override
	public List<Comment> findByTitle(String title) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Comment> criteria = builder.createQuery(Comment.class);
		Root<Comment> entity = criteria.from(Comment.class);
		
		criteria.select(entity).where(builder.equal(entity.get("title"), title));
		
		return em.createQuery(criteria).getResultList();
	}

}
