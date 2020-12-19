package ar.edu.ucc.arqSoft.taskManagement.dto;

import ar.edu.ucc.arqSoft.common.dto.DtoEntity;

public class CommentRequestDto implements DtoEntity{

	private String title;
	
	private String description;
	
	private Long taskID;
	
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

	public Long getTaskID() {
		return taskID;
	}

	public void setTaskID(Long taskID) {
		this.taskID = taskID;
	}
}
