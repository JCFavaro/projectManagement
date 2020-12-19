package ar.edu.ucc.arqSoft.taskManagement.service;

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
import ar.edu.ucc.arqSoft.taskManagement.dto.ProjectResponseDto;
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
	private UserDao userDao;

	@Autowired
	private StateDao stateDao;

	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private ProjectDao projectDao;

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

		task.setName(dto.getName());
		task.setDescription(dto.getDescription());
		task.setProject(projectDao.load(dto.getProjectID()));
		task.setState(stateDao.load((long) 1)); // 'Creado'

		Comment comment = new Comment();

		comment.setTitle("Creada");
		comment.setDescription("Se creo una nueva tarea al proyecto '" + task.getProject().getName() + "'");
		comment.setTask(task);

		commentDao.insert(comment);

		taskDao.insert(task);

		TaskResponseDto response = (TaskResponseDto) new ModelDtoConverter().convertToDto(task, new TaskResponseDto());

		return response;
	}

	public TaskResponseDto changeUser(Long taskID, Long userID) throws EntityNotFoundException, BadRequestException {
		Task task = taskDao.load(taskID);

		if (task.getState().getId() == 3 || task.getState().getId() == 4 || task.getState().getId() == 6) {
			throw new BadRequestException();
		}

		Project project = task.getProject();

		User user = userDao.load(userID);
		
		if (!project.getUsers().contains(user)) { // Si el usuario no pertenece al proyecto de la tarea
			throw new EntityNotFoundException();
		}

		Comment comment = new Comment();

		comment.setTitle("Usuario " + user.getName() + " " + user.getLastName() + " asignado");
		comment.setDescription("Se ha cambiado el usuario para esta tarea");

		comment.setTask(task);

		task.setUser(user);
		task.setState(stateDao.load((long) 2)); // 2 = 'Asignado'

		taskDao.update(task);

		TaskResponseDto response = new TaskResponseDto();

		return response;
	}

	public TaskResponseDto changeState(Long taskID, Long stateID) throws BadRequestException {
		if (stateID <= 0 || stateID > 6) {
			throw new BadRequestException();
		}

		Task task = taskDao.load(taskID);

		Comment comment = new Comment();

		comment.setTitle("Estado actualizado");
		comment.setDescription("Se ha actualizado el estado de la tarea.");

		task.addComment(comment);

		task.setState(stateDao.load(stateID));

		taskDao.update(task);

		TaskResponseDto response = new TaskResponseDto();

		return response;
	}
}
