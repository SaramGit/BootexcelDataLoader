package com.nt.state;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity(name="USA_STATE")
@Data
public class UsaState {
	@Id
	@Column(name="STATE_CODE")
	private String stateCode;
	@Column(name="STATE_NAME")
	private String STATENAME;

}
