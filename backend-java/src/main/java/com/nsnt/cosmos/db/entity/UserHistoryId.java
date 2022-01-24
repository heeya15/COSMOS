package com.nsnt.cosmos.db.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserHistoryId implements Serializable{
	
	
//	private long userhistoryNo;
//	private User user;
//	private LocalDateTime date; 
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userhistory_no")
	int userhistoryNo;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name = "date", columnDefinition = "TIMESTAMP")
	@CreationTimestamp
	private LocalDateTime date;
	
	
}
