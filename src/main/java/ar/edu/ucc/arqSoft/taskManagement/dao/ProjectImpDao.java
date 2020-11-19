package ar.edu.ucc.arqSoft.taskManagement.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import ar.edu.ucc.arqSoft.common.dao.GenericImpDao;
import ar.edu.ucc.arqSoft.taskManagement.model.Project;

@Repository
public class ProjectImpDao extends GenericImpDao<Project, Long> implements ProjectDao{


	@Override
	public List<Project> findByName(String name) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Project> criteria = builder.createQuery(Project.class);
		Root<Project> entity = criteria.from(Project.class);
		
		criteria.select(entity).where(builder.equal(entity.get("name"), name));
		
		return em.createQuery(criteria).getResultList();
	}

}
