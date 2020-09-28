package io.github.yovelas.mapper;

import io.github.yovelas.entity.Photo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PhotosMapper {

    @Select("select * from photos;")
    @Results({
            @Result(property = "id", column = "id"),
			@Result(property = "fileName", column = "file_name"),
			@Result(property = "fileOriginName", column = "file_origin_name"),
			@Result(property = "thumbnail1920x1080", column = "thumbnail_1920x1080"),
			@Result(property = "thumbnail1024x768", column = "thumbnail_1024x768"),
			@Result(property = "thumbnail800x600", column = "thumbnail_800x600"),
			@Result(property = "thumbnail500x375", column = "thumbnail_500x375"),
			@Result(property = "thumbnail400x300", column = "thumbnail_400x300"),
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

	@Select("select * from photos where id=#{id};")
	@Results({
			@Result(property = "id", column = "id"),
			@Result(property = "fileName", column = "file_name"),
			@Result(property = "fileOriginName", column = "file_origin_name"),
			@Result(property = "thumbnail1920x1080", column = "thumbnail_1920x1080"),
			@Result(property = "thumbnail1024x768", column = "thumbnail_1024x768"),
			@Result(property = "thumbnail800x600", column = "thumbnail_800x600"),
			@Result(property = "thumbnail500x375", column = "thumbnail_500x375"),
			@Result(property = "thumbnail400x300", column = "thumbnail_400x300"),
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
	Photo selectPhotoById(@Param("id") int id);

	@Select("select * from photos where file_name=#{fileName};")
	@Results({
			@Result(property = "id", column = "id"),
			@Result(property = "fileName", column = "file_name"),
			@Result(property = "fileOriginName", column = "file_origin_name"),
			@Result(property = "thumbnail1920x1080", column = "thumbnail_1920x1080"),
			@Result(property = "thumbnail1024x768", column = "thumbnail_1024x768"),
			@Result(property = "thumbnail800x600", column = "thumbnail_800x600"),
			@Result(property = "thumbnail500x375", column = "thumbnail_500x375"),
			@Result(property = "thumbnail400x300", column = "thumbnail_400x300"),
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
	Photo selectPhotoByFileName(@Param("fileName") String fileName);


    @Insert("INSERT INTO photos VALUES(" +
			"null," +
			"#{fileName}," +
			"#{fileOriginName}," +
			"#{thumbnail1920x1080}," +
			"#{thumbnail1024x768}," +
			"#{thumbnail800x600}," +
			"#{thumbnail500x375}," +
			"#{thumbnail400x300}," +
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
			@Param("fileOriginName") String fileOriginName,
			@Param("thumbnail1920x1080") String thumbnail1920x1080,
			@Param("thumbnail1024x768") String thumbnail1024x768,
			@Param("thumbnail800x600") String thumbnail800x600,
			@Param("thumbnail500x375") String thumbnail500x375,
			@Param("thumbnail400x300") String thumbnail400x300,
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

    @Update("update photos set " +
			"file_origin_name=#{fileOriginName}," +
			"thumbnail_1920x1080=#{thumbnail1920x1080}," +
			"thumbnail_1024x768=#{thumbnail1024x768}," +
			"thumbnail_800x600=#{thumbnail800x600}," +
			"thumbnail_500x375=#{thumbnail500x375}," +
			"thumbnail_400x300=#{thumbnail400x300}," +
			"file_size=#{fileSize}," +
			"image_width=#{imageWidth}," +
			"image_height=#{imageHeight}," +
			"date_time=#{dateTime}," +
			"artist=#{artist}," +
			"image_size=#{imageSize}," +
			"user_comment=#{userComment}," +
			"exposure_time=#{exposureTime}," +
			"f_number=#{fNumber}," +
			"iso_speed_ratings=#{isoSpeedRatings}," +
			"focal_length=#{focalLength}," +
			"metering_mode=#{meteringMode}," +
			"exposure_mode=#{exposureMode}," +
			"exposure_program=#{exposureProgram}," +
			"flash=#{flash}," +
			"model=#{make}," +
			"model=#{model}," +
			"long_focal_length=#{longFocalLength}," +
			"short_focal_length=#{shortFocalLength}," +
			"aperture_value=#{apertureValue}," +
			"software =#{software} " +
			"where file_name=#{fileName}")
	int updatePhoto( @Param("fileName") String fileName,
			@Param("fileOriginName") String fileOriginName,
			@Param("thumbnail1920x1080") String thumbnail1920x1080,
			@Param("thumbnail1024x768") String thumbnail1024x768,
			@Param("thumbnail800x600") String thumbnail800x600,
			@Param("thumbnail500x375") String thumbnail500x375,
			@Param("thumbnail400x300") String thumbnail400x300,
			@Param("fileSize") String fileSize,
			@Param("imageWidth") String imageWidth,
			@Param("imageHeight") String imageHeight,
			@Param("dateTime") String dateTime,
			@Param("artist") String artist,
			@Param("imageSize") String imageSize,
			@Param("userComment") String userComment,
			@Param("exposureTime") String exposureTime,
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
			@Param("apertureValue") String apertureValue);

}
