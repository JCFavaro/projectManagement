package ar.edu.ucc.arqSoft.taskManagement.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;
import ar.edu.ucc.arqSoft.taskManagement.dao.UserDao;
import ar.edu.ucc.arqSoft.taskManagement.dto.UserResponseDto;
import ar.edu.ucc.arqSoft.taskManagement.model.User;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public UserResponseDto getUserById(Long id) throws EntityNotFoundException, BadRequestException {

		if (id <= 0) {
			throw new BadRequestException();
		}
		
		User user = userDao.load(id);
		
		UserResponseDto dto = new UserResponseDto();

		dto.setName(user.getName());
		dto.setLastName(user.getLastName());
		dto.setDni(user.getDni());
		dto.setEmail(user.getEmail());
		dto.setProjects(user.getProjects());
		dto.setTasks(user.getTasks());
		
		return dto;

	}
	
}
