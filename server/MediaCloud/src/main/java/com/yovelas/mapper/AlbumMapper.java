package com.yovelas.mapper;

import com.yovelas.entity.Album;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AlbumMapper {

	@Insert("INSERT INTO photo_albums VALUES(default," +
			"#{userId}," +
			"#{albumTag}," +
			"#{albumDescribe}," +
			"#{viewUserIdList}," +
			"#{likeUserIdList}," +
			"#{commentIdList}," +
			"#{centralPhotoId}," +
			"#{photoList}," +
			"#{cameraParameter}," +
			"now())")
	int insertAlbum(@Param("userId") int userId,
            @Param("albumTag") String albumTag,
			@Param("albumDescribe") String albumDescribe,
			@Param("viewUserIdList") String viewUserIdList,
			@Param("likeUserIdList") String likeUserIdList,
			@Param("commentIdList") String commentIdList,
			@Param("centralPhotoId") String centralPhotoId,
			@Param("photoList") String photoList,
			@Param("cameraParameter") String cameraParameter);

	@Select("select * from photo_albums")
	@Results({
			@Result(property = "photoList", column = "photo_list"),
			@Result(property = "albumsDescribe", column = "albums_describe")
	})
	List<Album>	 selectAllAlbums();
}


