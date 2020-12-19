package ar.edu.ucc.arqSoft.taskManagement.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ar.edu.ucc.arqSoft.common.model.GenericObject;

@Entity
@Table(name = "PROJECT")
public class Project extends GenericObject {

	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "NAME")
	private String name;

	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "DESCRIPTION")
	private String description;
	
	@ManyToMany(mappedBy = "projects", fetch = FetchType.LAZY)
	private Set<User> users;

	@OneToOne(fetch = FetchType.LAZY)
	private State state;

	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
	private Set<Task> tasks;

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

	public Set<User> getUsers() {
		return users;
	}

	public void addUser(User user) {
		this.users.add(user);
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public void addTask(Task task) {
		this.tasks.add(task);
	}

}
