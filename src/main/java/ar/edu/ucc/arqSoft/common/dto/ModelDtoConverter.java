package ar.edu.ucc.arqSoft.common.dto;

import org.modelmapper.ModelMapper;

public class ModelDtoConverter {
	public DtoEntity convertToDto(Object obj, DtoEntity mapper) {
		return new ModelMapper().map(obj, mapper.getClass());
	}

	public Object convertToEntity(Object obj, DtoEntity mapper) {
		return new ModelMapper().map(mapper, obj.getClass());
	}
}
