package com.my.sample.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.my.sample.data.UserData;
import com.my.sample.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u from User u where u.userName = :username AND u.status = 'y'")
	User findUserByUsername(@Param("username") String username);
	
	@Query("SELECT New com.my.sample.data.UserData(u.id, u.userName, u.name, u.contact, u.email, u.role) from User u where u.status = 'y'")
	List<UserData> getALL();

}
