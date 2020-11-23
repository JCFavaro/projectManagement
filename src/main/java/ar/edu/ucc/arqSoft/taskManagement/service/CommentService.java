package ar.edu.ucc.arqSoft.taskManagement.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.ucc.arqSoft.common.dto.ModelDtoConverter;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;
import ar.edu.ucc.arqSoft.taskManagement.dao.CommentDao;
import ar.edu.ucc.arqSoft.taskManagement.dao.ProjectDao;
import ar.edu.ucc.arqSoft.taskManagement.dao.TaskDao;
import ar.edu.ucc.arqSoft.taskManagement.dto.CommentRequestDto;
import ar.edu.ucc.arqSoft.taskManagement.dto.CommentResponseDto;
import ar.edu.ucc.arqSoft.taskManagement.model.Comment;
import ar.edu.ucc.arqSoft.taskManagement.model.Project;
import ar.edu.ucc.arqSoft.taskManagement.model.Task;

@Service
@Transactional
public class CommentService {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private TaskDao taskDao;

	public CommentResponseDto getCommentById(Long id) throws EntityNotFoundException, BadRequestException {
		if (id <= 0) {
			throw new BadRequestException();
		}

		Comment comment = commentDao.load(id);

		CommentResponseDto response = (CommentResponseDto) new ModelDtoConverter().convertToDto(comment,
				new CommentResponseDto());
		return response;
	}

	public CommentResponseDto addComment(CommentRequestDto dto) throws BadRequestException {
		Comment comment = new Comment(null, null);

		Task task = taskDao.load(dto.getTaskID());

		Project project = projectDao.load(dto.getProjectID());

		// Si se pasa el id del proyecto y la tarea, valido que esa tarea pertenezca al
		// proyecto.
		if (dto.getProjectID() != null && dto.getTaskID() != null && task.getProject().getId() == dto.getProjectID()) {
			
			//Si el estado del proyecto y la tarea no es Cerrado ni Terminado
			if (project.getState().getName() == "Cerrado" || project.getState().getName() == "Terminado"
					|| task.getState().getName() == "Cerrado" || task.getState().getName() == "Terminado") {
				
				comment.setProject(project);
				comment.setTask(task);
				
			}
			//Si solo pasan el proyecto y el estado no es cerrado ni terminado
		} else if (dto.getProjectID() != null && dto.getTaskID() == null && project.getState().getName() != "Cerrado"
				|| project.getState().getName() != "Terminado") {
			
			comment.setProject(project);
			//Si solo pasan la tarea y el estado no es cerrado ni terminado
		} else if (dto.getProjectID() == null && dto.getTaskID() != null && task.getState().getName() != "Cerrado"
				|| task.getState().getName() != "Terminado") {
			
			comment.setTask(task);
			
		} else {
			throw new BadRequestException();
		}

		CommentResponseDto response = new CommentResponseDto();

		response.setTitle(comment.getTitle());
		response.setDescription(comment.getDescription());

		return response;
	}
}
