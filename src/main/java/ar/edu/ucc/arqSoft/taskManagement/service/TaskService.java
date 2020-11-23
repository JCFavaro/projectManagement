package ar.edu.ucc.arqSoft.taskManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.ucc.arqSoft.common.dto.ModelDtoConverter;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;
import ar.edu.ucc.arqSoft.taskManagement.dao.ProjectDao;
import ar.edu.ucc.arqSoft.taskManagement.dao.TaskDao;
import ar.edu.ucc.arqSoft.taskManagement.dto.TaskRequestDto;
import ar.edu.ucc.arqSoft.taskManagement.dto.TaskResponseDto;
import ar.edu.ucc.arqSoft.taskManagement.model.State;
import ar.edu.ucc.arqSoft.taskManagement.model.Task;


@Service
@Transactional
public class TaskService {

	@Autowired
	private TaskDao taskDao;
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

	public TaskResponseDto registerTask (TaskRequestDto dto) {
		
		Task task = new Task();
		
		task.setProject(projectDao.load(dto.getProjectId()));
		task.setState(State.CREADO);
		task.setUser(null);
		
		taskDao.insert(task);

		TaskResponseDto response = new TaskResponseDto();
		
		response.setName(task.getName());
		response.setDescription(task.getDescription());
		
		return response;
		
	}	
}
