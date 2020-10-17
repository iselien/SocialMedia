package com.yovelas.mapper;

import com.yovelas.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
	@Select("SELECT * FROM user where username=#{username}")
	@Results({
			@Result(property = "createTime",column = "create_time"),
	})
	User getUserByName(@Param("username") String username);

	@Select("SELECT * FROM user where username=#{username} and password=#{password}")
	@Results({
			@Result(property = "createTime",column = "create_time")
	})
	User getUserByUP(@Param("username") String username, @Param("password") String password);



	@Insert("INSERT INTO user VALUES(default,#{username},#{password},#{nickname},#{email},#{phone},#{image},now())")
	int insertUser(
			@Param("username") String username,
			@Param("password") String password,
			@Param("nickname") String nickname,
			@Param("email") String eamil,
			@Param("phone") String phone,
			@Param("image") String image);
}