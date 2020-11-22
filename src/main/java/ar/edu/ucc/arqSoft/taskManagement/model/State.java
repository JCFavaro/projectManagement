package ar.edu.ucc.arqSoft.taskManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ar.edu.ucc.arqSoft.common.model.GenericObject;

@Entity
@Table (name = "STATE")
public class State extends GenericObject{

	@JsonIgnore
	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "NAME")
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
