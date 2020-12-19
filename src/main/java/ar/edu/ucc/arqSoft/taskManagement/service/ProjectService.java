package ar.edu.ucc.arqSoft.taskManagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.ucc.arqSoft.common.dto.ModelDtoConverter;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;
import ar.edu.ucc.arqSoft.taskManagement.dao.CommentDao;
import ar.edu.ucc.arqSoft.taskManagement.dao.ProjectDao;
import ar.edu.ucc.arqSoft.taskManagement.dao.StateDao;
import ar.edu.ucc.arqSoft.taskManagement.dao.TaskDao;
import ar.edu.ucc.arqSoft.taskManagement.dao.UserDao;
import ar.edu.ucc.arqSoft.taskManagement.dto.CommentResponseDto;
import ar.edu.ucc.arqSoft.taskManagement.dto.ProjectRequestDto;
import ar.edu.ucc.arqSoft.taskManagement.dto.ProjectResponseDto;
import ar.edu.ucc.arqSoft.taskManagement.model.Comment;
import ar.edu.ucc.arqSoft.taskManagement.model.Project;
import ar.edu.ucc.arqSoft.taskManagement.model.Task;

@Service
@Transactional
public class ProjectService {

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private TaskDao taskDao;

	@Autowired
	private StateDao stateDao;

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

		Project project = (Project) new ModelDtoConverter().convertToEntity(new Project(), dto);

		project.setState(stateDao.load((long) 1)); // 'Creado'

		projectDao.insert(project);

		ProjectResponseDto response = (ProjectResponseDto) new ModelDtoConverter().convertToDto(project,
				new ProjectResponseDto());

		return response;
	}

	public ProjectResponseDto assignUser(long projectID, long userID) throws BadRequestException {

		Project project = projectDao.load(projectID);

		if (project.getState().getId() == 3 || project.getState().getId() == 4 || project.getState().getId() == 6) {
			throw new BadRequestException();
		}

		project.addUser(userDao.load(userID));
		project.setState(stateDao.load((long) 2)); // 2 = 'Asignado'

		projectDao.update(project);

		ProjectResponseDto response = (ProjectResponseDto) new ModelDtoConverter().convertToDto(project,
				new ProjectResponseDto());

		return response;
	}

	public ProjectResponseDto changeState(Long projectID, Long stateID) throws BadRequestException {
		if (stateID <= 0 || stateID > 6) {
			throw new BadRequestException();
		}

		Project project = projectDao.load(projectID);

		project.setState(stateDao.load(stateID));

		projectDao.update(project);

		ProjectResponseDto response = new ProjectResponseDto();

		return response;
	}

}
