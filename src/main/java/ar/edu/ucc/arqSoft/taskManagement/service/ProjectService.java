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
import ar.edu.ucc.arqSoft.taskManagement.dao.StateDao;
import ar.edu.ucc.arqSoft.taskManagement.dao.TaskDao;
import ar.edu.ucc.arqSoft.taskManagement.dao.UserDao;
import ar.edu.ucc.arqSoft.taskManagement.dto.ProjectRequestDto;
import ar.edu.ucc.arqSoft.taskManagement.dto.ProjectResponseDto;
import ar.edu.ucc.arqSoft.taskManagement.model.Comment;
import ar.edu.ucc.arqSoft.taskManagement.model.Project;

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

		Project project = new Project();

		Comment comment = new Comment();
		
		comment.setTitle("Creado");
		comment.setDescription("Proyecto Creado");

		if (dto.getUserID() != null) {
			project.addUser(userDao.load(dto.getUserID()));
			project.setState(stateDao.load((long) 2)); // 1 = 'Asignado'
		} else {
			project.setUsers(null);
			project.setState(stateDao.load((long) 1)); // 1 = 'Creado'
		}

		if (dto.getTaskID() != null) {
			project.addTask(taskDao.load(dto.getTaskID()));
		} else {
			project.setTasks(null);
		}

		project.addComment(comment);

		projectDao.insert(project);

		ProjectResponseDto response = new ProjectResponseDto();

		response.setName(project.getName());
		response.setDescription(project.getDescription());

		return response;
	}

	public ProjectResponseDto assignUser(Long projectID, long userID) {
		Project project = projectDao.load(projectID);

		Comment comment = new Comment();
		
		comment.setTitle("Usuario asignado");
		comment.setDescription("Se ha asignado el usuario " + userDao.findByID(userID));
		
		project.addUser(userDao.load(userID));
		project.setState(stateDao.load((long) 2)); // 2 = 'Asignado'
		project.addComment(comment);

		projectDao.insert(project);

		ProjectResponseDto response = (ProjectResponseDto) new ModelDtoConverter().convertToDto(project,
				new ProjectResponseDto());

		response.setName(project.getName());
		response.setDescription(project.getDescription());

		return response;
	}

	public ProjectResponseDto assignTask(Long projectID, long taskID) {
		Project project = projectDao.load(projectID);

		Comment comment = new Comment();

		comment.setTitle("Tarea Asignada");
		comment.setDescription("Se ha asignado la tarea " + taskDao.findByID(taskID));
		
		project.addTask(taskDao.load(taskID));
		project.addComment(comment);

		projectDao.insert(project);

		ProjectResponseDto response = (ProjectResponseDto) new ModelDtoConverter().convertToDto(project,
				new ProjectResponseDto());
		
		response.setName(project.getName());
		response.setDescription(project.getDescription());


		return response;
	}
	
	public ProjectResponseDto changeState(Long projectID, Long stateID) throws BadRequestException {
		if(stateID <= 0 || stateID > 6) {
			throw new BadRequestException();
		}
		
		Project project = projectDao.load(projectID);
		
		Comment comment = new Comment();

		comment.setTitle("Estado actualizado");
		comment.setDescription("Se ha actualizado el estado del proyecto");
		
		project.setState(stateDao.load(stateID));

		projectDao.update(project);

		ProjectResponseDto response = new ProjectResponseDto();

		response.setComments(comment);
		
		return response;
	}

}
