package ar.edu.ucc.arqSoft.taskManagement.dto;

import ar.edu.ucc.arqSoft.common.dto.DtoEntity;
import ar.edu.ucc.arqSoft.taskManagement.model.Project;
import ar.edu.ucc.arqSoft.taskManagement.model.Task;

public class CommentRequestDto implements DtoEntity{

	private String title;
	
	private String description;
	
	private Task task;

	private Project project;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

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

}
