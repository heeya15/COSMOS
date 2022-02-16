package com.nsnt.cosmos.db.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "userhistory_week")
public class UserHistoryWeek {
	@EmbeddedId
	UserHistoryWeekId userHistoryWeekId;
	
	@Column(name = "total_time")
	long totalTime;
}
