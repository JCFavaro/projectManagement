package ar.edu.ucc.arqSoft.taskManagement.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.ucc.arqSoft.common.dto.ModelDtoConverter;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;
import ar.edu.ucc.arqSoft.taskManagement.dao.CommentDao;
import ar.edu.ucc.arqSoft.taskManagement.dao.ProjectDao;
import ar.edu.ucc.arqSoft.taskManagement.dao.TaskDao;
import ar.edu.ucc.arqSoft.taskManagement.dto.CommentRequestDto;
import ar.edu.ucc.arqSoft.taskManagement.dto.CommentResponseDto;
import ar.edu.ucc.arqSoft.taskManagement.dto.ProjectResponseDto;
import ar.edu.ucc.arqSoft.taskManagement.model.Comment;
import ar.edu.ucc.arqSoft.taskManagement.model.Project;
import ar.edu.ucc.arqSoft.taskManagement.model.Task;

@Service
@Transactional
public class CommentService {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private TaskDao taskDao;

	public CommentResponseDto getCommentById(Long id) throws EntityNotFoundException, BadRequestException {
		if (id <= 0) {
			throw new BadRequestException();
		}

		Comment comment = commentDao.load(id);

		CommentResponseDto response = (CommentResponseDto) new ModelDtoConverter().convertToDto(comment,
				new CommentResponseDto());
		return response;
	}

	public CommentResponseDto addComment(CommentRequestDto dto) throws BadRequestException {
		Comment comment = new Comment();
		
		comment.setTitle(dto.getTitle());
		comment.setDescription(dto.getDescription());
		comment.setTask(taskDao.load(dto.getTaskID()));

		commentDao.insert(comment);

		CommentResponseDto response = (CommentResponseDto) new ModelDtoConverter().convertToDto(comment,
				new CommentResponseDto());

		return response;
	}
}
