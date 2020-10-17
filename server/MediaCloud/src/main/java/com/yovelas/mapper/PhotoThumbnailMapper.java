package com.yovelas.mapper;

import com.yovelas.entity.Photo;
import com.yovelas.entity.PhotoThumbnail;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PhotoThumbnailMapper {

	@Results({
			@Result(property = "id", column = "id"),
			@Result(property = "photoId", column = "photo_id"),
			@Result(property = "thumbnail_1920x1080", column = "thumbnail_1920x1080"),
			@Result(property = "thumbnail_1024x768", column = "thumbnail_1024x768"),
			@Result(property = "thumbnail_800x600", column = "thumbnail_800x600"),
			@Result(property = "thumbnail_500x375", column = "thumbnail_500x375"),
			@Result(property = "thumbnail_400x300", column = "thumbnail_400x300")})
   	@Select("select * from photo_thumbnail where photo_id = (select id from photos where id = #{id});")
    PhotoThumbnail selectPhotoThumbnailByPhotoId(@Param("id") int photoid );


	@Insert("INSERT INTO photo_thumbnail VALUES(" +
			"null," +
			"#{photoId}," +
			"#{thumbnail_1920x1080}," +
			"#{thumbnail_1024x768}," +
			"#{thumbnail_800x600}," +
			"#{thumbnail_500x375}," +
			"#{thumbnail_400x300})")
    int insertPhotoThumbnail(@Param("fileName") String fileName,
                    @Param("fileSize") String fileSize,
                    @Param("fileWidth") String imageWidth,
                    @Param("fileHeight") String imageHeight,
                    @Param("Time") String dataTime,
                    @Param("artist") String artist,
                    @Param("imageSize") String imageSize,
                    @Param("userComment") String userComment,
                    @Param("fNumber") String fNumber,
                    @Param("isoSpeedRatings") String isoSpeedRatings,
                    @Param("focalLength") String focalLength,
                    @Param("meteringMode") String meteringMode,
                    @Param("exposureMode") String exposureMode,
                    @Param("exposureProgram") String exposureProgram,
                    @Param("flash") String flash,
                    @Param("make") String make,
                    @Param("model") String model,
                    @Param("longFocalLength") String longFocalLength,
                    @Param("shortFocalLength") String shortFocalLength,
                    @Param("apertureValue") String apertureValue,
                    @Param("software") String software);

}
