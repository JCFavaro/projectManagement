package ar.edu.ucc.arqSoft.taskManagement.dto;

import ar.edu.ucc.arqSoft.common.dto.DtoEntity;
import ar.edu.ucc.arqSoft.taskManagement.model.Project;
import ar.edu.ucc.arqSoft.taskManagement.model.State;
import ar.edu.ucc.arqSoft.taskManagement.model.User;

public class TaskRequestDto implements DtoEntity{

	private String name;
	
	private String description;
	
	private User user;
	
	private Project project;
	
	private State state;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
