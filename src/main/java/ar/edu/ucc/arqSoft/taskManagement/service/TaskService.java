package ar.edu.ucc.arqSoft.taskManagement.service;

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
import ar.edu.ucc.arqSoft.taskManagement.dto.TaskRequestDto;
import ar.edu.ucc.arqSoft.taskManagement.dto.TaskResponseDto;
import ar.edu.ucc.arqSoft.taskManagement.model.Comment;
import ar.edu.ucc.arqSoft.taskManagement.model.Project;
import ar.edu.ucc.arqSoft.taskManagement.model.Task;
import ar.edu.ucc.arqSoft.taskManagement.model.User;

@Service
@Transactional
public class TaskService {

	@Autowired
	private TaskDao taskDao;

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private StateDao stateDao;

	public TaskResponseDto getTaskById(Long id) throws EntityNotFoundException, BadRequestException {

		if (id <= 0) {
			throw new BadRequestException();
		}
		Task task = taskDao.load(id);

		TaskResponseDto response = (TaskResponseDto) new ModelDtoConverter().convertToDto(task, new TaskResponseDto());

		return response;
	}

	public TaskResponseDto registerTask(TaskRequestDto dto) {

		Task task = new Task();

		if (dto.getStateID() != null) {
			task.setState(stateDao.load(dto.getStateID()));
		} else {
			task.setState(stateDao.load((long) 1)); // 1 = 'Creado'
		}

		if (dto.getUserId() != null) {
			task.setUser(userDao.load(dto.getUserId()));
			task.setState(stateDao.load((long) 2)); // 2 = 'Asignado'
		} else {
			task.setUser(null);
		}

		task.setProject(projectDao.load(dto.getProjectId()));

		taskDao.insert(task);

		TaskResponseDto response = new TaskResponseDto();

		Comment comment = new Comment("Creada", "Se creo una nueva tarea");

		response.setName(task.getName());
		response.setDescription(task.getDescription());
		response.setComments(comment);

		return response;
	}

	public TaskResponseDto changeUser(Long taskID, Long userID) throws EntityNotFoundException {
		Task task = taskDao.load(taskID);

		Project project = task.getProject();

		User user = userDao.load(userID);

		Comment comment = new Comment("Usuario asignado", "Se ha cambiado el usuario para esta tarea");

		if (!project.getUsers().contains(user)) { // Si el usuario no pertenece al proyecto de la tarea
			throw new EntityNotFoundException();
		}

		task.setUser(user);
		task.setState(stateDao.load((long) 2)); // 2 = 'Asignado'

		taskDao.update(task);

		TaskResponseDto response = new TaskResponseDto();

		response.setComments(comment);

		return response;
	}

	public TaskResponseDto changeState(Long taskID, Long stateID) throws BadRequestException {
		if(stateID <= 0 || stateID > 6) {
			throw new BadRequestException();
		}
		
		Task task = taskDao.load(taskID);
		
		Comment comment = new Comment("Estado actualizado", "Se ha actualizado el estado de la tarea.");

		task.setState(stateDao.load(stateID));

		taskDao.update(task);

		TaskResponseDto response = new TaskResponseDto();

		response.setComments(comment);
		
		return response;
	}
}
