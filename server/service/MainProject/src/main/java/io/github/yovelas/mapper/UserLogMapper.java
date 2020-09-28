package io.github.yovelas.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface UserLogMapper {

	@Insert("INSERT INTO user_log VALUES( null, #{userId}, now(), #{descript}, " +
			"#{serverName}, #{serverPort}, #{clientHost}, " +
			"#{clientAddress}, #{clientPort}, #{localName}, " +
			"#{localPort}, #{requestUrl}, #{requestQuery}, " +
			"#{requestMethod}, #{requestProtocol}, #{requestScheme}, " +
			"#{requestOrigin}, #{requestReferer}, #{requestCookie}, " +
			"#{requestConnection}, #{requestContent_length}, " +
			"#{requestContent_type}, #{requestAccept}, #{requestAccept_encoding}, " +
			"#{requestAccept_language}, #{userAgent}) ")
	int insertUserLog( @Param("userId") String userId,
			@Param("descript") String descript,
			@Param("serverName") String serverName,
			@Param("serverPort") String serverPort,
			@Param("clientHost") String clientHost,
			@Param("clientAddress") String clientAddress,
			@Param("clientPort") String clientPort,
			@Param("localName") String localName,
			@Param("localPort") String localPort,
			@Param("requestUrl") String requestUrl,
			@Param("requestQuery") String requestQuery,
			@Param("requestMethod") String requestMethod,
			@Param("requestProtocol") String requestProtocol,
			@Param("requestScheme") String requestScheme,
			@Param("requestOrigin") String requestOrigin,
			@Param("requestReferer") String requestReferer,
			@Param("requestCookie") String requestCookie,
			@Param("requestConnection") String requestConnection,
			@Param("requestContent_length") String requestContent_length,
			@Param("requestContent_type") String requestContent_type,
			@Param("requestAccept") String requestAccept,
			@Param("requestAccept_encoding") String requestAccept_encoding,
			@Param("requestAccept_language") String requestAccept_language,
			@Param("userAgent") String userAgent);

}


