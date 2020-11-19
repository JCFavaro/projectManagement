package ar.edu.ucc.arqSoft.taskManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ar.edu.ucc.arqSoft.common.model.GenericObject;

@Entity
@Table (name = "COMMENT")
public class Comment extends GenericObject{

	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "DESCRIPTION")
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="TASK_ID")
	private Task task;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PROJECT_ID")
	private Project project;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}	
	
}