package ar.edu.ucc.arqSoft.taskManagement.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ar.edu.ucc.arqSoft.common.model.GenericObject;

@Entity
@Table (name = "USER")
public class User extends GenericObject{

	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "NAME")
	private String name;
	
	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@NotNull
	@Size(min = 1, max = 400)
	@Column(name = "DNI")
	private String dni;

	@NotNull
	@Size(min = 1, max = 400)
	@Column(name = "EMAIL")
	private String email;
	
	//Many to Many Un proyecto puede tener muchos usuarios y un usuario puede tener muchos proyectos
	@ManyToMany(targetEntity=User.class)
	private Set<Project> projects;
	
	//Un usuario puede tener muchas tareas.
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private Set<Task> tasks;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
	
}
