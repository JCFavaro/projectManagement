package ar.edu.ucc.arqSoft.taskManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;
import ar.edu.ucc.arqSoft.taskManagement.dao.TaskDao;
import ar.edu.ucc.arqSoft.taskManagement.dto.TaskResponseDto;
import ar.edu.ucc.arqSoft.taskManagement.model.Task;

@Service
@Transactional
public class TaskService {

	@Autowired
	private TaskDao taskDao;
	
	public TaskResponseDto getMovieByID(Long id) throws EntityNotFoundException, BadRequestException {

		if (id <= 0) {
			throw new BadRequestException();
		}

		Task task = taskDao.load(id);

		TaskResponseDto dto = new TaskResponseDto();

		//Poner a que proyecto pertenece
		dto.setDescription(task.getDescription());
		// Que usuario esta haciendo que tarea


		return dto;

	}
	
}
