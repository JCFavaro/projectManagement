package ar.edu.ucc.arqSoft.taskManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.edu.ucc.arqSoft.common.dto.GenericExceptionDto;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;
import ar.edu.ucc.arqSoft.taskManagement.dto.CommentResponseDto;
import ar.edu.ucc.arqSoft.taskManagement.service.CommentService;


@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> lookupProjectById(@PathVariable("id") Long id) {
		try {

			CommentResponseDto dto = commentService.getCommentById(id);
			return new ResponseEntity<Object>(dto, HttpStatus.OK);

		} catch (EntityNotFoundException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("1001", "Project not found");
			return new ResponseEntity<Object>(exDto, HttpStatus.NOT_FOUND);
		} catch (BadRequestException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("1002", "No se entendió que buscar");
			return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)

    public @ResponseBody List<CommentResponseDto> getAllComments()
    {
        return commentService.getAllComments();
    }
	

}
