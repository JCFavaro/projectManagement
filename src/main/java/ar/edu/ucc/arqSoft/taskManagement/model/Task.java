package ar.edu.ucc.arqSoft.taskManagement.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ar.edu.ucc.arqSoft.common.model.GenericObject;

@Entity
@Table (name = "TASK")
public class Task extends GenericObject{
	
	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "NAME")
	private String name;
	
	
	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "DESCRIPTION")
	private String description;
	
	@OneToMany(mappedBy="task", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Comment> comments;
	
	//Una tarea puede ser asignada a un solo usuario
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	@JsonIgnore
	private User user;
	
	//private Set<User> historicalUsers; // Cuando reasignamos la tarea, es bueno saber quien 
										//la tenia asignada antes.
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="STATE_ID")
	@JsonIgnore
	private State state;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PROJECT_ID")
	@JsonIgnore
	private Project project;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

/*	public Set<User> getHistoricalUsers() {
		return historicalUsers;
	}

	public void setHistoricalUsers(Set<User> historicalUsers) {
		this.historicalUsers = historicalUsers;
	}*/
}
