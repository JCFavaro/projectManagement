package ar.edu.ucc.arqSoft.taskManagement.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import ar.edu.ucc.arqSoft.common.dao.GenericImpDao;
import ar.edu.ucc.arqSoft.taskManagement.model.Task;

@Repository
public class TaskImpDao  extends GenericImpDao<Task, Long> implements TaskDao{

	@Override
	public List<Task> findByName(String name) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Task> criteria = builder.createQuery(Task.class);
		Root<Task> entity = criteria.from(Task.class);
		
		criteria.select(entity).where(builder.equal(entity.get("name"), name));
		
		return em.createQuery(criteria).getResultList();
	}

}
