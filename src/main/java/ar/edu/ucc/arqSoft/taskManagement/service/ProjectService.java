package ar.edu.ucc.arqSoft.taskManagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.ucc.arqSoft.common.dto.ModelDtoConverter;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;
import ar.edu.ucc.arqSoft.taskManagement.dao.ProjectDao;
import ar.edu.ucc.arqSoft.taskManagement.dto.ProjectRequestDto;
import ar.edu.ucc.arqSoft.taskManagement.dto.ProjectResponseDto;
import ar.edu.ucc.arqSoft.taskManagement.model.Project;
import ar.edu.ucc.arqSoft.taskManagement.model.State;

@Service
@Transactional
public class ProjectService {

	@Autowired
	private ProjectDao projectDao;

	public ProjectResponseDto getProjectById(Long id) throws EntityNotFoundException, BadRequestException {
		if (id <= 0) {
			throw new BadRequestException();
		}

		Project project = projectDao.load(id);

		ProjectResponseDto response = (ProjectResponseDto) new ModelDtoConverter().convertToDto(project,
				new ProjectResponseDto());
		return response;
	}

	public List<ProjectResponseDto> getAllProyects() {
		List<Project> projects = projectDao.getAll();

		List<ProjectResponseDto> response = new ArrayList<ProjectResponseDto>();

		for (Project project : projects) {
			response.add((ProjectResponseDto) new ModelDtoConverter().convertToDto(project, new ProjectResponseDto()));
		}

		return response;
	}

	public ProjectResponseDto registerProject(ProjectRequestDto dto) {

		Project project = new Project();

		project.setComments(null);
		project.setUsers(null);
		project.setTasks(null);
		project.setState(State.CREADO);

		projectDao.insert(project);

		ProjectResponseDto response = new ProjectResponseDto();

		response.setName(project.getName());
		response.setDescription(project.getDescription());

		return response;
	}
	
	public ProjectResponseDto assignUser(ProjectResponseDto dto) {
		
		Project project = new Project();
		
		dto.setUsers(project.getUsers());
		
		return dto;
		
	}
	
}
