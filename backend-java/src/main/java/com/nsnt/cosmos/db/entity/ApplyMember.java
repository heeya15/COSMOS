package com.nsnt.cosmos.db.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplyMember  {
	@EmbeddedId
	private ApplyMemberId applymemberId;
	@ManyToOne
	@JoinColumn(name = "user_id")
	User userId;
}
