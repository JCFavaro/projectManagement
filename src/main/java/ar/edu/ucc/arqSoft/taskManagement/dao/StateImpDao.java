package ar.edu.ucc.arqSoft.taskManagement.dao;

import org.springframework.stereotype.Repository;

import ar.edu.ucc.arqSoft.common.dao.GenericImpDao;
import ar.edu.ucc.arqSoft.taskManagement.model.State;

@Repository
public class StateImpDao extends GenericImpDao<State, Long> implements StateDao{

}
