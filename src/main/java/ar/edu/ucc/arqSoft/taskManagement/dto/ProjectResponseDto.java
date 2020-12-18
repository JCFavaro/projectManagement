package ar.edu.ucc.arqSoft.taskManagement.dto;

import java.util.Set;

import ar.edu.ucc.arqSoft.common.dto.DtoEntity;
import ar.edu.ucc.arqSoft.taskManagement.model.Comment;
import ar.edu.ucc.arqSoft.taskManagement.model.Task;
import ar.edu.ucc.arqSoft.taskManagement.model.User;

public class ProjectResponseDto implements DtoEntity{

	private String name;
	
	private String description;
	
	private StateResponseDto state;
	
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

	public StateResponseDto getState() {
		return state;
	}

	public void setState(StateResponseDto state) {
		this.state = state;
	}
	
}
