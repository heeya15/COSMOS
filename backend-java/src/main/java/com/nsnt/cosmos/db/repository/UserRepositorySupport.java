package com.nsnt.cosmos.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nsnt.cosmos.api.response.UserDtoRes;
import com.nsnt.cosmos.db.entity.QUser;
import com.nsnt.cosmos.db.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class UserRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    private UserRepository userRepository;
    QUser qUser = QUser.user;

    public Optional<User> findUserByUserId(String userId) {
        User user = jpaQueryFactory.select(qUser).from(qUser)
                .where(qUser.userId.eq(userId)).fetchOne();
        if(user == null) return Optional.empty();
        return Optional.ofNullable(user);
    }
    public boolean findByUserIdEquals(String userId) {
    	 User user = jpaQueryFactory.select(qUser).from(qUser)
                 .where(qUser.userId.eq(userId)).fetchOne();
    	 if(user == null) return true; 
    	 return false;
    }
}
