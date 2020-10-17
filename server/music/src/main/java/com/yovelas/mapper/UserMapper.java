package com.yovelas.mapper;

import com.yovelas.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
	@Select("SELECT * FROM music_user where username=#{username}")
	@Results({
			@Result(property = "createTime",column = "create_time"),
			@Result(property = "updateTime",column = "update_time")
	})
	User getUserByName(@Param("username") String username);

	@Select("SELECT * FROM music_user where username=#{username} and password=#{password}")
	@Results({
			@Result(property = "createTime",column = "create_time"),
			@Result(property = "updateTime",column = "update_time")
	})
	User getUserByUP(@Param("username") String username, @Param("password") String password);

	@Insert("INSERT INTO blog_user VALUES(default,#{username},#{password},#{email},#{phone},#{image},#{question},#{answer},#{role},#{create_time},#{update_time})")
	int InsertUser(
            @Param("username") String username,
            @Param("password") String password,
            @Param("email") String eamil,
            @Param("phone") String phone,
            @Param("image") String image,
            @Param("question") String question,
            @Param("answer") String answer,
            @Param("role") String role,
            @Param("create_time") String create_time,
            @Param("update_time") String update_time);
	
	@Update("UPDATE blog_user SET password=#{password} where username=#{username}")
	int setpawd(@Param("password") String password, @Param("username") String username);
}
