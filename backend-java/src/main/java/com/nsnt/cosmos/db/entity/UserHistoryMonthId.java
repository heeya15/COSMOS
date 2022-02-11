package com.nsnt.cosmos.db.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserHistoryMonthId implements Serializable {
	@Column(name = "month")
	int month;
	
	@Column(name = "user_id")
	String user_id;
}
