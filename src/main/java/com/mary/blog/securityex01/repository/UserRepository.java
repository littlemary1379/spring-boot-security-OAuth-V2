package com.mary.blog.securityex01.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mary.blog.securityex01.model.User;

//JPARepository 상속하면 자동 컴포넌트 스캔
public interface UserRepository extends JpaRepository<User, Integer>{

	//JPA naming 전략
	//SELECT * From user Where username=1?
	User findByUsername(String username);
	//User findByUsernameAndPassword(String username, String password);
	
//	@Query(value = "select * from user", nativeQuery=true)
//	User find마음대로();
	
//@Query(value="select * from user where email=1?",nativeQuery=true)
	Optional<User> findByEmail(String email);
}
