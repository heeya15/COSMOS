package com.nsnt.cosmos.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserHistoryYearId implements Serializable {
	@Column(name = "year")
	int year;
	
	@Column(name = "user_id")
	String user_id;
}
