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
public class UserHistoryDayId implements Serializable {
	@Column(name = "day")
	int day;
	
	@Column(name = "user_id")
	String user_id;
}
