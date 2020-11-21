package ar.edu.ucc.arqSoft.taskManagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.ucc.arqSoft.common.dto.ModelDtoConverter;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;
import ar.edu.ucc.arqSoft.taskManagement.dao.TaskDao;
import ar.edu.ucc.arqSoft.taskManagement.dto.TaskRequestDto;
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
		
        TaskResponseDto response = (TaskResponseDto) new ModelDtoConverter().convertToDto(task, new TaskResponseDto());
       /* 
        response.setName(task.getName());
        response.setDescription(task.getDescription());
        response.setProject(task.getProject());
        response.setState(task.getState());
        response.setUser(task.getUser());*/
     
        
        return response;
	}
	
	public List<TaskResponseDto> getAllTasks() {
        List<Task> tasks = taskDao.getAll();

        List<TaskResponseDto> response = new ArrayList<TaskResponseDto>();

        for (Task task : tasks) {
            response.add((TaskResponseDto) new ModelDtoConverter().convertToDto(task, new TaskResponseDto()));
        }

        return response;
    }
	

	public TaskResponseDto registerTask (TaskRequestDto dto) {
		
		Task task = new Task();
		
		taskDao.insert(task);
		

		TaskResponseDto response = new TaskResponseDto();
		
		response.setDescription(task.getDescription());
		response.setProject(task.getProject());
		response.setUser(task.getUser());
		response.setState(task.getState());
		
		return response;
		
	}
	
	
	
}
