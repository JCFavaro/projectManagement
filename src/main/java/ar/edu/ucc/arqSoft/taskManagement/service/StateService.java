package ar.edu.ucc.arqSoft.taskManagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.ucc.arqSoft.common.dto.ModelDtoConverter;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;
import ar.edu.ucc.arqSoft.taskManagement.dao.StateDao;
import ar.edu.ucc.arqSoft.taskManagement.dto.StateResponseDto;
import ar.edu.ucc.arqSoft.taskManagement.model.State;

@Service
@Transactional
public class StateService {

	@Autowired
	private StateDao stateDao;

	public StateResponseDto getStateById(Long id) throws EntityNotFoundException, BadRequestException {
		if (id <= 0) {
			throw new BadRequestException();
		}

		State state = stateDao.load(id);

		StateResponseDto response = (StateResponseDto) new ModelDtoConverter().convertToDto(state,
				new StateResponseDto());
		return response;
	}

	public List<StateResponseDto> getAllStates() {
		List<State> states = stateDao.getAll();

		List<StateResponseDto> response = new ArrayList<StateResponseDto>();

		for (State state : states) {
			response.add((StateResponseDto) new ModelDtoConverter().convertToDto(state, new StateResponseDto()));
		}

		return response;
	}

}
