package ar.edu.ucc.arqSoft.taskManagement.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import ar.edu.ucc.arqSoft.common.dao.GenericImpDao;
import ar.edu.ucc.arqSoft.taskManagement.model.State;

@Repository
public class StateImpDao extends GenericImpDao<State, Long> implements StateDao{

	@Override
	public State findByName(String name) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<State> criteria = builder.createQuery(State.class);
		Root<State> entity = criteria.from(State.class);
		
		criteria.select(entity).where(builder.equal(entity.get("name"), name));
		
		return em.createQuery(criteria).getSingleResult();
	}
	
}
