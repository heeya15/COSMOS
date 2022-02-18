package com.nsnt.cosmos.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nsnt.cosmos.api.response.UserDtoRes;
import com.nsnt.cosmos.api.response.UserLeaderDtoRes;
import com.nsnt.cosmos.db.entity.User;

import java.util.List;
import java.util.Optional;
// Optional<T>는 null이 올수 있는 값을 감싸는 Wrapper클래스로, 참조하더라도 NPE가 발생하지 않도록 도와준다
// Repository의 정확한 사용은 DAO를 위해 사용하는 어노테이션인데 JpaRepository는 JPA의 구현체라고 할 수 있다.
// 기본적인 CRUD가 정의되어 있는 JpaRepository를 구현하였고 사용자들은 그 구현체를 상속하여 사용하면 되는 것이다.
/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> { // 제네릭 안에 해당 엔티티, 엔티티 PK 자료형을 적어줌
	// 아래와 같이, Query Method 인터페이스(반환값, 메소드명, 인자) 정의를 하면 자동으로 Query Method 구현됨.
    Optional<User> findByUserId(String userId);
    boolean findByUserIdEquals(String userId);
//    boolean findByUserEmailEquals(String userEmail);
    
 // 아래와 같이, Query Method 인터페이스(반환값, 메소드명, 인자) 정의를 하면 자동으로 Query Method 구현됨.
 	@Query(value="select leader, authority\r\n" + 
 			"from study_member\r\n" + 
 			"where user_id = :user_id and study_no = :study_no"        
             ,nativeQuery = true)
    UserLeaderDtoRes isLeader(@Param("user_id") String user_id, @Param("study_no") String study_no);

 	@Query(value="select count(*)\r\n" + 
 			"from user\r\n" + 
 			"where user_email = :user_email"
 			, nativeQuery = true)
 	int findByUserEmail(@Param("user_email") String user_email);
 	
 	@Query(value="select * from user\r\n" + 
 			"where user_id <> \"admin\"\r\n" + 
 			"order by join_date desc;"
 			, nativeQuery = true)
 	List<User> findAllUserJoinDateDesc();
}