package com.yovelas.mapper;

import com.yovelas.entity.Music;
import com.yovelas.entity.Photo;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;

@Mapper
public interface PhotosMapper {

    @Select("select * from photos;")
    @Results({
            @Result(property = "id", column = "id"),
			@Result(property = "fileName", column = "file_name"),
			@Result(property = "fileSize", column = "file_size"),
			@Result(property = "imageWidth", column = "image_width"),
			@Result(property = "imageHeight", column = "image_height"),
			@Result(property = "dataTime", column = "date_time"),
			@Result(property = "artist", column = "artist"),
			@Result(property = "imageSize", column = "image_size"),
			@Result(property = "userComment", column = "user_comment"),
			@Result(property = "exposureTime", column = "exposure_time"),
			@Result(property = "fNumber", column = "f_number"),
			@Result(property = "isoSpeedRatings", column = "iso_speed_ratings"),
			@Result(property = "focalLength", column = "focal_length"),
			@Result(property = "meteringMode", column = "metering_mode"),
			@Result(property = "exposureMode", column = "exposure_mode"),
			@Result(property = "exposureProgram", column = "exposure_program"),
			@Result(property = "flash", column = "flash"),
			@Result(property = "make", column = "make"),
			@Result(property = "model", column = "model"),
			@Result(property = "longFocalLength", column = "long_focal_length"),
			@Result(property = "shortFocalLength", column = "short_focal_length"),
			@Result(property = "apertureValue", column = "aperture_value"),
			@Result(property = "software", column = "software")
	})
	List<Photo> selectPhotos();

	@Select("select * from photos where file_name=#{fileName}")
	@Results({
			@Result(property = "id", column = "id"),
			@Result(property = "fileName", column = "file_name"),
			@Result(property = "fileSize", column = "file_size"),
			@Result(property = "imageWidth", column = "image_width"),
			@Result(property = "imageHeight", column = "image_height"),
			@Result(property = "dataTime", column = "date_time"),
			@Result(property = "artist", column = "artist"),
			@Result(property = "imageSize", column = "image_size"),
			@Result(property = "userComment", column = "user_comment"),
			@Result(property = "exposureTime", column = "exposure_time"),
			@Result(property = "fNumber", column = "f_number"),
			@Result(property = "isoSpeedRatings", column = "iso_speed_ratings"),
			@Result(property = "focalLength", column = "focal_length"),
			@Result(property = "meteringMode", column = "metering_mode"),
			@Result(property = "exposureMode", column = "exposure_mode"),
			@Result(property = "exposureProgram", column = "exposure_program"),
			@Result(property = "flash", column = "flash"),
			@Result(property = "make", column = "make"),
			@Result(property = "model", column = "model"),
			@Result(property = "longFocalLength", column = "long_focal_length"),
			@Result(property = "shortFocalLength", column = "short_focal_length"),
			@Result(property = "apertureValue", column = "aperture_value"),
			@Result(property = "software", column = "software")
	})
	List<Photo> selectPhotoByFileName(@Param("fileName") String fileName);

    @Insert("INSERT INTO photos VALUES(" +
			"null," +
			"#{fileName}," +
			"#{fileSize}," +
			"#{imageWidth}," +
			"#{imageHeight}," +
			"#{dateTime}," +
			"#{artist}," +
			"#{imageSize}," +
			"#{userComment}," +
			"#{exposureTime}," +
			"#{fNumber}," +
			"#{isoSpeedRatings}," +
			"#{focalLength}," +
			"#{meteringMode}," +
			"#{exposureMode}," +
			"#{exposureProgram}," +
			"#{flash}," +
			"#{make}," +
			"#{model}," +
			"#{longFocalLength}," +
			"#{shortFocalLength}," +
			"#{apertureValue}," +
			"#{software})")
    int insertPhoto( @Param("fileName") String fileName,
			@Param("fileSize") String fileSize,
			@Param("fileWidth") String imageWidth,
			@Param("fileHeight") String imageHeight,
			@Param("dateTime") String dateTime,
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
