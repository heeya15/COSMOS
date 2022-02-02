package com.nsnt.cosmos.db.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 유저히스토리 정의.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name="userhistory")
//@IdClass(UserHistoryId.class)
public class UserHistory implements Serializable{

	@Id
	@Column(name = "userhistory_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int userhistoryNo;
	
//	@Id
//	@OneToOne
//	@JoinColumn(name="user_id")
//	private User user;
//	
//	@Id
//	@Column(name = "date", columnDefinition = "TIMESTAMP")
//	@CreationTimestamp
//	private LocalDateTime date;
	
	
//	@EmbeddedId
//	private UserHistoryId userHistoryID;
	
	@Column(name = "user_start_time", columnDefinition = "TIMESTAMP")
	@CreationTimestamp
	private LocalDateTime userStartTime;
	
	@Column(name = "user_finish_time", columnDefinition = "TIMESTAMP")
	@CreationTimestamp
	private LocalDateTime userFinishTime;
	
//	@OneToOne(mappedBy = "userhistory")
//	private UserHistoryDay day;
	
}

//@Entity
//@Getter
//@Setter
//class UserHistoryDay {
//
//	@Id
//	@Column(name = "day")
//	long day;
//	
//	@Column(name = "total_time")
//	int totalTime;
//	
//	@OneToOne
//	@JoinColumn(name="userhistory_no")
//	@JoinColumn(name="user_id")
//	@JoinColumn(name="date")
//	private UserHistory userHistory;
//
//}
//
//@Entity
//@Getter
//@Setter
//class Week{
//	@Id
//	@Column(name = "week")
//	long week;
//	
//	@Column(name = "total_time")
//	int totalTime;
//	
//	@OneToOne
//	@JoinColumn(name="date")
//	@JoinColumn(name="user_id")
//	private UserHistory userhistory;
//}
//
//@Entity
//@Getter
//@Setter
//class Month{
//	@Id
//	@Column(name = "month")
//	long month;
//	
//	@Column(name = "total_time")
//	int totalTime;
//	
//	@OneToOne
//	@JoinColumn(name="date")
//	@JoinColumn(name="user_id")
//	private UserHistory userhistory;
//}