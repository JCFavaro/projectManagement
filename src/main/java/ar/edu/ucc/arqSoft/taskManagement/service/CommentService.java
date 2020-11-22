package ar.edu.ucc.arqSoft.taskManagement.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.ucc.arqSoft.taskManagement.dao.CommentDao;
import ar.edu.ucc.arqSoft.taskManagement.dao.ProjectDao;
import ar.edu.ucc.arqSoft.taskManagement.dao.TaskDao;
import ar.edu.ucc.arqSoft.taskManagement.dto.CommentRequestDto;
import ar.edu.ucc.arqSoft.taskManagement.dto.CommentResponseDto;
import ar.edu.ucc.arqSoft.taskManagement.model.Comment;

@Service
@Transactional
public class CommentService {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private TaskDao taskDao;

	@Autowired
	private ProjectDao projectDao;
	
	/*
	 * public CommentResponseDto getCommentById(Long id) throws
	 * EntityNotFoundException, BadRequestException { if (id <= 0) { throw new
	 * BadRequestException(); }
	 * 
	 * Comment comment = commentDao.load(id);
	 * 
	 * CommentResponseDto response = (CommentResponseDto) new
	 * ModelDtoConverter().convertToDto(comment, new CommentResponseDto()); return
	 * response; }
	 * 
	 * public List<CommentResponseDto> getAllComments() { List<Comment> comments =
	 * commentDao.getAll();
	 * 
	 * List<CommentResponseDto> response = new ArrayList<CommentResponseDto>();
	 * 
	 * for (Comment comment : comments) { response.add((CommentResponseDto) new
	 * ModelDtoConverter().convertToDto(comment, new CommentResponseDto())); }
	 * 
	 * return response; }
	 */

	public CommentResponseDto registerCommentToProject(CommentRequestDto dto) {

		Comment comment = new Comment();

		comment.setProject(projectDao.load(dto.getProjectId()));
		//comment.setTask(taskDao.load(dto.getTaskId()));
		comment.setTask(null);

		commentDao.insert(comment);

		CommentResponseDto response = new CommentResponseDto();

		response.setDescription(comment.getDescription());
		response.setTitle(comment.getTitle());
		response.setProject(comment.getProject());
		response.setTask(comment.getTask());

		return response;

	}
	
	public CommentResponseDto registerCommentToTask(CommentRequestDto dto) {

		Comment comment = new Comment();

		//comment.setProject(projectDao.load(dto.getProjectId()));
		comment.setProject(null); // o comment.getProject()?
		comment.setTask(taskDao.load(dto.getTaskId()));
		

		commentDao.insert(comment);

		CommentResponseDto response = new CommentResponseDto();

		response.setDescription(comment.getDescription());
		response.setTitle(comment.getTitle());
		response.setProject(comment.getProject());
		response.setTask(comment.getTask());

		return response;

	}

}
