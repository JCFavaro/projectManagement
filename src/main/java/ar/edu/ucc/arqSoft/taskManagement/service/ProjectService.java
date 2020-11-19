package ar.edu.ucc.arqSoft.taskManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;
import ar.edu.ucc.arqSoft.taskManagement.dao.ProjectDao;
import ar.edu.ucc.arqSoft.taskManagement.dto.ProjectResponseDto;
import ar.edu.ucc.arqSoft.taskManagement.model.Project;

@Service
@Transactional
public class ProjectService {
	
	@Autowired
	private ProjectDao projectDao;
	
	public ProjectResponseDto getProjectById(Long id) throws EntityNotFoundException, BadRequestException {

		if (id <= 0) {
			throw new BadRequestException();
		}

		Project project = projectDao.load(id);

		ProjectResponseDto dto = new ProjectResponseDto();

		dto.setName(project.getName());
		dto.setDescription(project.getDescription());
		

		return dto;

	}
}
