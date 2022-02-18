package com.nsnt.cosmos.db.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

/**
 * 유저 모델 정의.
 */

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@Column(name = "user_id", length= 20,  nullable = false)
	String userId;
	
	@Column(name = "user_name", length= 20, nullable = false)
	String userName;
	
	@Column(name = "user_password", length= 200, nullable = false)
	String userPassword;
	@Email
	@Column(length = 200, nullable = false)
	String userEmail;
	
	@ColumnDefault("0")
	int totalTime;
  
	@ColumnDefault("0")
	int totalAttendance;

	@Column(columnDefinition = "TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
	@CreationTimestamp
	private LocalDateTime joinDate;
	
//    @Column(name = "userId", nullable = false, insertable = true, updatable = true)
    public String getUserId() {
        return userId;
    }
    @Builder
	public void updateUser(String user_name, String user_password) {
		this.userName = user_name;
		this.userPassword =user_password;	
	}

//    @JsonIgnore
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    String password;
}
