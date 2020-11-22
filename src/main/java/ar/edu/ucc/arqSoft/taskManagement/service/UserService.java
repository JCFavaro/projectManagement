package ar.edu.ucc.arqSoft.taskManagement.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;
import ar.edu.ucc.arqSoft.taskManagement.dao.UserDao;
import ar.edu.ucc.arqSoft.taskManagement.dto.UserRequestDto;
import ar.edu.ucc.arqSoft.taskManagement.dto.UserResponseDto;
import ar.edu.ucc.arqSoft.taskManagement.model.User;
import ar.edu.ucc.arqSoft.common.dto.ModelDtoConverter;

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

		UserResponseDto response = (UserResponseDto) new ModelDtoConverter().convertToDto(user, new UserResponseDto());

		return response;
	}

	public List<UserResponseDto> getAllUsers() {
		List<User> users = userDao.getAll();

		List<UserResponseDto> response = new ArrayList<UserResponseDto>();

		for (User user : users) {
			response.add((UserResponseDto) new ModelDtoConverter().convertToDto(user, new UserResponseDto()));
		}

		return response;
	}

	public UserResponseDto registerUser(UserRequestDto dto) {

		User user = new User();

		//Para crear un post se deben inicializar todos los atributos del objeto
		user.setProjects(null);
		user.setTasks(null);

		userDao.insert(user);

		UserResponseDto response = new UserResponseDto();

		response.setName(user.getName());
		response.setLastName(user.getLastName());
		response.setDni(user.getDni());
		response.setEmail(user.getEmail());

		return response;
	}

}
