//package com.nsnt.cosmos.db.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//public class UserHistoryDay {
//	
//	@Id
//	@Column(name = "week")
//	long week;
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
