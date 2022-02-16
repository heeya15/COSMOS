package com.nsnt.cosmos.db.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	@Column(name = "day_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
	@CreationTimestamp
	private Date day_date;

}
