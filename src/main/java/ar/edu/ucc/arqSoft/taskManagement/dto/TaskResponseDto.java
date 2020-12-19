package ar.edu.ucc.arqSoft.taskManagement.dto;

import java.util.List;
import java.util.Set;

import ar.edu.ucc.arqSoft.common.dto.DtoEntity;
import ar.edu.ucc.arqSoft.taskManagement.model.Comment;
import ar.edu.ucc.arqSoft.taskManagement.model.Project;
import ar.edu.ucc.arqSoft.taskManagement.model.User;

public class TaskResponseDto implements DtoEntity {

	private String name;

	private String description;

	private StateResponseDto state;
	
	private List<CommentResponseDto> comments;

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public StateResponseDto getState() {
		return state;
	}

	public void setState(StateResponseDto state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CommentResponseDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentResponseDto> comments) {
		this.comments = comments;
	}
}
