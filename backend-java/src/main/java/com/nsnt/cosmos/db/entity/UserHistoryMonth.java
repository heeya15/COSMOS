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
@Table(name = "userhistory_month")
public class UserHistoryMonth {
	@EmbeddedId
	UserHistoryMonthId userhistoryMonthId;
	
	@Column(name = "total_time")
	long totalTime;

}
