package ar.edu.ucc.arqSoft.taskManagement.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.ucc.arqSoft.common.dto.ModelDtoConverter;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;
import ar.edu.ucc.arqSoft.taskManagement.dao.CommentDao;
import ar.edu.ucc.arqSoft.taskManagement.dto.CommentRequestDto;
import ar.edu.ucc.arqSoft.taskManagement.dto.CommentResponseDto;
import ar.edu.ucc.arqSoft.taskManagement.model.Comment;

@Service
@Transactional
public class CommentService {

	@Autowired
	private CommentDao commentDao;

	public CommentResponseDto getCommentById(Long id) throws EntityNotFoundException, BadRequestException {
		if (id <= 0) {
			throw new BadRequestException();
		}

		Comment comment = commentDao.load(id);

		CommentResponseDto response = (CommentResponseDto) new ModelDtoConverter().convertToDto(comment,
				new CommentResponseDto());
		return response;
	}

	public CommentResponseDto registerCommentToProject(CommentRequestDto dto) {

		Comment comment = new Comment();

		commentDao.insert(comment);

		CommentResponseDto response = new CommentResponseDto();

		response.setDescription(comment.getDescription());
		response.setTitle(comment.getTitle());

		return response;

	}

	public CommentResponseDto registerCommentToTask(CommentRequestDto dto) {

		Comment comment = new Comment();

		commentDao.insert(comment);

		CommentResponseDto response = new CommentResponseDto();

		response.setDescription(comment.getDescription());
		response.setTitle(comment.getTitle());

		return response;

	}

}
