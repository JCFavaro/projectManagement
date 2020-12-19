package ar.edu.ucc.arqSoft.taskManagement.dto;

import ar.edu.ucc.arqSoft.common.dto.DtoEntity;

public class TaskRequestDto implements DtoEntity{

	private String name;
	
	private String description;
	
	private Long projectID;
	
	public Long getProjectID() {
		return projectID;
	}

	public void setProjectID(Long projectID) {
		this.projectID = projectID;
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
}
