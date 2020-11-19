package ar.edu.ucc.arqSoft.taskManagement.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.taskManagement.dao.UserDao;
import ar.edu.ucc.arqSoft.taskManagement.dto.UserRequestDto;
import ar.edu.ucc.arqSoft.taskManagement.dto.UserResponseDto;
import ar.edu.ucc.arqSoft.taskManagement.model.User;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public UserResponseDto registerMember(UserRequestDto dto) throws BadRequestException {

		User user = new User();

		userDao.insert(user);

		UserResponseDto response = new UserResponseDto();

		response.setName(user.getName());
		response.setLastName(user.getLastName());
		response.setDni(user.getDni());
		response.setEmail(user.getEmail());
		//Devolver los proyectos en los que pertence
		// Y las tareas a las que esta asignado
		
		return response;

	}
	
}
