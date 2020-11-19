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
	
	public TaskResponseDto getTaskById(Long id) throws EntityNotFoundException, BadRequestException {

		if (id <= 0) {
			throw new BadRequestException();
		}

		Task task = taskDao.load(id);

		TaskResponseDto dto = new TaskResponseDto();

		dto.setDescription(task.getDescription());
		dto.setProject(task.getProject());
		dto.setUser(task.getUser());
		dto.setState(task.getState());

		return dto;

	}
	
}
