package ar.edu.ucc.arqSoft.taskManagement.dto;

import java.util.Set;

import ar.edu.ucc.arqSoft.common.dto.DtoEntity;
import ar.edu.ucc.arqSoft.taskManagement.model.State;
import ar.edu.ucc.arqSoft.taskManagement.model.Task;
import ar.edu.ucc.arqSoft.taskManagement.model.User;

public class ProjectRequestDto implements DtoEntity {

	private String name;

	private String description;
	
	private State state;

	private Set<User> users;

	private Set<Task> tasks;

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
