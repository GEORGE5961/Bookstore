package com.zhengyu.bookstore.repository;

import com.zhengyu.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


	@Query(value = "update users set username=?1 where id=?2",nativeQuery = true)   //占位符传值形式
	@Modifying
	int updateById(String name, int id);

	User findById(int userId);

	List<User> findAll();

}