package io.github.yovelas.mapper;

import io.github.yovelas.entity.Album;
import io.github.yovelas.entity.PhotoTag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AlbumMapper {

	@Insert("INSERT INTO photo_album VALUES(default," +
			"#{userId}," +
			"#{albumName}," +
			"#{albumTag}," +
			"#{albumDescribe}," +
			"#{centralPhotoId}," +
			"#{cameraParameter}," +
			"now())")
	int insertAlbum(@Param("userId") int userId,
            @Param("albumTag") String albumTag,
			@Param("albumName") String albumName,
			@Param("albumDescribe") String albumDescribe,
			@Param("centralPhotoId") String centralPhotoId,
			@Param("cameraParameter") String cameraParameter);

	@Insert("INSERT INTO photo_album VALUES(default," +
			"#{tagId}," +
			"#{albumId}," +
			"now())")
	int insertAlbumTagRecord(@Param("tagId") int tagId, @Param("albumId") int albumId);

	@Insert("INSERT INTO photo_album VALUES(default," +
			"#{albumId}," +
			"#{photoId}," +
			"now())")
	int insertAlbumPhotoList(@Param("albumId") int albumId, @Param("photoId") int photoId);


	@Select("select * from photo_album")
	@Results({
			@Result(property = "id", column = "id"),
			@Result(property = "userId", column = "user_id"),
			@Result(property = "albumName", column = "album_Name"),
			@Result(property = "albumTag", column = "album_tag"),
			@Result(property = "albumDescribe", column = "album_describe"),
			@Result(property = "centralPhotoId", column = "central_photo_id"),
			@Result(property = "cameraParameter", column = "camera_parameter"),
			@Result(property = "createTime", column = "create_time")
	})
	List<Album>	 selectAllAlbums();

	@Select("select * from photo_tags where tag like '%${likelyTag}%'")
	@Results({
			@Result(property = "id", column = "id"),
			@Result(property = "tag", column = "tag"),
			@Result(property = "createTime", column = "create_time")
	})
	List<PhotoTag> selectTagsByLikelyTag(@Param("likelyTag") String likelyTag);

	@Insert("INSERT INTO photo_tags VALUES(default,#{tag},now())")
	int insertPhotoTag(@Param("tag") String tag);

	@Select("select * from photo_album where user_id=#{userId}")
	@Results({
			@Result(property = "id", column = "id"),
			@Result(property = "userId", column = "user_id"),
			@Result(property = "albumName", column = "album_Name"),
			@Result(property = "albumTag", column = "album_tag"),
			@Result(property = "albumDescribe", column = "album_describe"),
			@Result(property = "centralPhotoId", column = "central_photo_id"),
			@Result(property = "cameraParameter", column = "camera_parameter"),
			@Result(property = "createTime", column = "create_time")
	})
	List<Album>	 selectAlbumsByUserId(@Param("userId") int userId);
}


