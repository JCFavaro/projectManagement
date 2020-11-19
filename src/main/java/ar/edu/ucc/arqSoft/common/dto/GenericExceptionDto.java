package ar.edu.ucc.arqSoft.common.dto;

public class GenericExceptionDto {

	private String code;

	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public GenericExceptionDto(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public GenericExceptionDto() {
		super();
	}
}