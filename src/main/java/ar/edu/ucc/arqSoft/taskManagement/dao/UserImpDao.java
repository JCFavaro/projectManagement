package ar.edu.ucc.arqSoft.taskManagement.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import ar.edu.ucc.arqSoft.common.dao.GenericImpDao;
import ar.edu.ucc.arqSoft.taskManagement.model.User;

@Repository
public class UserImpDao extends GenericImpDao<User, Long> implements UserDao{
	
	@Override
	public List<User> findByName(String name) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> entity = criteria.from(User.class);
		
		criteria.select(entity).where(builder.equal(entity.get("name"), name));
		
		return em.createQuery(criteria).getResultList();
	}
	
}
