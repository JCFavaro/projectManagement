package ar.edu.ucc.arqSoft.taskManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ar.edu.ucc.arqSoft.common.dto.GenericExceptionDto;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;
import ar.edu.ucc.arqSoft.taskManagement.dto.ProjectRequestDto;
import ar.edu.ucc.arqSoft.taskManagement.dto.ProjectResponseDto;
import ar.edu.ucc.arqSoft.taskManagement.service.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> lookupProjectById(@PathVariable("id") Long id) {
		try {

			ProjectResponseDto dto = projectService.getProjectById(id);
			return new ResponseEntity<Object>(dto, HttpStatus.OK);

		} catch (EntityNotFoundException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("1001", "Project not found");
			return new ResponseEntity<Object>(exDto, HttpStatus.NOT_FOUND);
		} catch (BadRequestException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("1002", "No se entendió que buscar");
			return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ProjectResponseDto> getAllProjects() {
		return projectService.getAllProyects();
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public @ResponseBody ProjectResponseDto register(@RequestBody ProjectRequestDto request)
			throws EntityNotFoundException, BadRequestException {
		return projectService.registerProject(request);
	}

	@RequestMapping(value = "/{projectID}/assignuser", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> assignUser(@PathVariable("projectID") Long projectID, @RequestBody Long userID)
			throws EntityNotFoundException, BadRequestException {
		ProjectResponseDto dto = projectService.assignUser(projectID, userID);
		return new ResponseEntity<Object>(dto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{projectID}/changestate", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> changeState(@PathVariable("projectID") Long projectID,
			@RequestBody Long stateID) throws EntityNotFoundException {
		try {
		ProjectResponseDto dto = projectService.changeState(projectID, stateID);
		return new ResponseEntity<Object>(dto, HttpStatus.OK);
		} catch (BadRequestException e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST); 
		}
	}
}
